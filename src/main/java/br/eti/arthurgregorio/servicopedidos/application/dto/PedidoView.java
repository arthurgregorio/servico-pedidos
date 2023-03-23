package br.eti.arthurgregorio.servicopedidos.application.dto;

import br.eti.arthurgregorio.servicopedidos.domain.model.Pedido;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public record PedidoView(
        UUID id,
        String nomeCliente,
        UUID rastreio,
        String statusAtual,
        Set<ItemPedidoView> items) {

    public static PedidoView fromDomain(Pedido pedido) {

        final var itemsPedido = pedido.getItemsPedido().stream()
                .map(ItemPedidoView::fromDomain)
                .collect(Collectors.toSet());

        return new PedidoView(pedido.getId(), pedido.getNomeCliente(), pedido.getRastreio(),
                pedido.getStatusAtual().name(), itemsPedido);
    }
}
