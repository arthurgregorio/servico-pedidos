package br.eti.arthurgregorio.servicopedidos.application.dto;

import br.eti.arthurgregorio.servicopedidos.domain.model.ItemPedido;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ItemPedidoRequest(
        @NotBlank
        String descricao,
        @Min(value = 1)
        int quantidade) {

    public ItemPedido toDomain() {
        return new ItemPedido(descricao, quantidade);
    }
}
