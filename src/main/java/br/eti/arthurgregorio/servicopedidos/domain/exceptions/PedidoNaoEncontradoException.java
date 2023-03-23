package br.eti.arthurgregorio.servicopedidos.domain.exceptions;

import java.util.UUID;

public class PedidoNaoEncontradoException extends RuntimeException {

    public PedidoNaoEncontradoException(UUID id) {
        super("Pedido [%s] nao foi encontrado".formatted(id));
    }
}
