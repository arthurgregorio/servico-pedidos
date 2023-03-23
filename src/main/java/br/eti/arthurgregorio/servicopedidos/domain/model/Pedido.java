package br.eti.arthurgregorio.servicopedidos.domain.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Set;
import java.util.UUID;

@Getter
@Document("pedidos")
public class Pedido {

    @Id
    @Field(targetType = FieldType.STRING)
    private final UUID id;
    private final String nomeCliente;
    private final UUID rastreio;
    private final Set<ItemPedido> itemsPedido;

    private Status statusAtual;

    public Pedido(UUID id, String nomeCliente, UUID rastreio, Status statusAtual, Set<ItemPedido> itemsPedido) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.rastreio = rastreio;
        this.statusAtual = statusAtual;
        this.itemsPedido = itemsPedido;
    }

    public enum Status {
        AGUARDANDO_PAGAMENTO, // ordem recebida pelo sistema, aguardando pagamento
        AGUARDANDO_ENVIO, // intermediario entre pagamento e envio
        ENVIADO, // enviada ao cliente
        AGUARDANDO_SEPARACAO, // aguardando a separacao no stoque
        FINALIZADO // apos confirmacao de recebimento do cliente
    }
}
