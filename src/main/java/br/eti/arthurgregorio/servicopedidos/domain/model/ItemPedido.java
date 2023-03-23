package br.eti.arthurgregorio.servicopedidos.domain.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("itens_pedido")
public record ItemPedido(
        String descricao,
        int quantidade) {
}
