package br.eti.arthurgregorio.servicopedidos.infrastructure.repositories;

import br.eti.arthurgregorio.servicopedidos.domain.model.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PedidoRepositoryImpl implements CustomPedidoRepository {

    private final MongoTemplate mongoTemplate;

    public Long updateStatus(UUID id, Pedido.Status novoStatus) {

        final var query = new Query().addCriteria(Criteria.where("id").is(id));
        final var update = Update.update("statusAtual", novoStatus);

        return mongoTemplate.updateFirst(query, update, Pedido.class)
                .getModifiedCount();
    }
}
