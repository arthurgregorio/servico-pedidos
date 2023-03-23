package br.eti.arthurgregorio.servicopedidos.domain.services;

import br.eti.arthurgregorio.servicopedidos.domain.exceptions.PedidoNaoEncontradoException;
import br.eti.arthurgregorio.servicopedidos.domain.model.Pedido;
import br.eti.arthurgregorio.servicopedidos.infrastructure.repositories.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MudarStatusPedidoService {

    private final PedidoRepository pedidoRepository;

    private final PublicaEventosDoPedidoService publicaEventosDoPedidoService;

    public void aguardarSeparacao(Pedido pedido) {
        pedidoRepository.updateStatus(pedido.getId(), Pedido.Status.AGUARDANDO_SEPARACAO);
    }

    public void aguardarEnvio(Pedido pedido) {

        /*
            Em uma mesma transacao temos 2 operacoes:

            1. Alterar o status do pedido
            2. Enviar um evento notificando quem possa interessar que o pedido
               esta pronto para ser enviado
        */

        final var idPedido = pedido.getId();

        // 1
        pedidoRepository.updateStatus(idPedido, Pedido.Status.AGUARDANDO_ENVIO);

        final var pedidoAtualizado = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new PedidoNaoEncontradoException(idPedido));

        // 2
        publicaEventosDoPedidoService.publicarPedidoLiberadoEnvio(pedidoAtualizado);
    }

    public void aguardarEnvioComOutbox(Pedido pedido) {

        /*
            Em uma mesma transacao temos 2 operacoes:

            1. Alterar o status do pedido
            2. Salva os dados pertinentes ao evento em uma collection auxiliar
        */

        final var outbox = new Pedido.Outbox(pedido.getId(), pedido.getNomeCliente(), pedido.getItemsPedido());

        pedido.aguardarEnvio();
        pedido.setOutbox(outbox);

        pedidoRepository.save(pedido);
    }
}
