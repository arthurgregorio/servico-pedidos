package br.eti.arthurgregorio.servicopedidos.application.dto;

import br.eti.arthurgregorio.servicopedidos.domain.model.Pedido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static br.eti.arthurgregorio.servicopedidos.domain.model.Pedido.Status.AGUARDANDO_PAGAMENTO;

public record PedidoRequest(
        @NotBlank
        String nomeCliente,
        @NotEmpty
        List<ItemPedidoRequest> itemsPedido) {

    public Pedido toDomain() {

        final var items = itemsPedido.stream()
                .map(ItemPedidoRequest::toDomain)
                .collect(Collectors.toSet());

        return new Pedido(UUID.randomUUID(), nomeCliente, null, AGUARDANDO_PAGAMENTO, items);
    }
}
