package br.eti.arthurgregorio.servicopedidos.infrastructure.repositories;

import br.eti.arthurgregorio.servicopedidos.domain.model.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PedidoRepository extends MongoRepository<Pedido, UUID>, CustomPedidoRepository {
}
