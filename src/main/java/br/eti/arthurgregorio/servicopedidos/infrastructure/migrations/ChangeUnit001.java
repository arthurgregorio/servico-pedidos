package br.eti.arthurgregorio.servicopedidos.infrastructure.migrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@RequiredArgsConstructor
@ChangeUnit(id = "create_collections", order = "1", author = "arthur.gregorio")
public class ChangeUnit001 {

    private final MongoTemplate mongoTemplate;

    @Execution
    public void apply() {

        final var collections = List.of("pedidos");

        collections.forEach(collection -> {
            final var exists = mongoTemplate.collectionExists(collection);
            if (!exists) {
                mongoTemplate.createCollection(collection);
            }
        });
    }

    @RollbackExecution
    public void rollback() {
        // do nothing
    }
}
