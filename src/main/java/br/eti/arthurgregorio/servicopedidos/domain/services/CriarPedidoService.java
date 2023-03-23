package br.eti.arthurgregorio.servicopedidos.domain.services;

import br.eti.arthurgregorio.servicopedidos.domain.model.Pedido;
import br.eti.arthurgregorio.servicopedidos.infrastructure.repositories.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CriarPedidoService {

    private final PedidoRepository pedidoRepository;

    public UUID criar(Pedido pedido) {
        pedidoRepository.save(pedido);
        return pedido.getId();
    }
}
