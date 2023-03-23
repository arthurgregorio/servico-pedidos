package br.eti.arthurgregorio.servicopedidos.infrastructure.repositories;

import br.eti.arthurgregorio.servicopedidos.domain.model.Pedido;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface CustomPedidoRepository {

    Long updateStatus(UUID id, Pedido.Status novoStatus);
}
