package br.eti.arthurgregorio.servicopedidos.application.dto;

import br.eti.arthurgregorio.servicopedidos.domain.model.ItemPedido;

public record ItemPedidoView(
        String descricao,
        int quantidade) {

    public static ItemPedidoView fromDomain(ItemPedido itemPedido) {
        return new ItemPedidoView(itemPedido.descricao(), itemPedido.quantidade());
    }
}
