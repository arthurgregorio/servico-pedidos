package br.eti.arthurgregorio.servicopedidos.domain.services;

import br.eti.arthurgregorio.servicopedidos.domain.model.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublicaEventosDoPedidoService {

    public void publicarPedidoLiberadoEnvio(Pedido pedido) {
        log.info("Convertendo pedido [{}] para o evento", pedido.getId());
        log.info("Pedido [{}] mudou para o status [{}]", pedido.getId(), pedido.getStatusAtual());
    }
}
