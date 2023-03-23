package br.eti.arthurgregorio.servicopedidos.domain.services;

import br.eti.arthurgregorio.servicopedidos.domain.model.Pedido;
import br.eti.arthurgregorio.servicopedidos.infrastructure.repositories.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MudarStatusPedidoService {

    private final PedidoRepository pedidoRepository;

    public void aguardarSeparacao(Pedido pedido) {
        pedidoRepository.updateStatus(pedido.getId(), Pedido.Status.AGUARDANDO_SEPARACAO);
    }

    public void aguardarEnvio(Pedido pedido) {

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
