package br.eti.arthurgregorio.servicopedidos.application.controllers;

import br.eti.arthurgregorio.servicopedidos.application.dto.PedidoRequest;
import br.eti.arthurgregorio.servicopedidos.application.dto.PedidoView;
import br.eti.arthurgregorio.servicopedidos.infrastructure.repositories.PedidoRepository;
import br.eti.arthurgregorio.servicopedidos.domain.exceptions.PedidoNaoEncontradoException;
import br.eti.arthurgregorio.servicopedidos.domain.services.CriarPedidoService;
import br.eti.arthurgregorio.servicopedidos.domain.services.MudarStatusPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final CriarPedidoService criarPedidoService;
    private final MudarStatusPedidoService mudarStatusPedidoService;

    private final PedidoRepository pedidoRepository;

    @PostMapping
    public ResponseEntity<UUID> create(@RequestBody @Validated PedidoRequest pedidoRequest) {

        // no mundo real isso deveria estar em um component que sabe fazer essa conversao
        final var pedido = pedidoRequest.toDomain();

        final var id = criarPedidoService.criar(pedido);

        final var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).body(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoView> getById(@PathVariable UUID id) {

        // usualmente esta validacao poderia estar em um validador
        final var pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException(id));

        return ResponseEntity.ok(PedidoView.fromDomain(pedido));
    }

    @PatchMapping("/{id}/separacao-finalizada")
    public ResponseEntity<Void> separacaoFinalizada(@PathVariable UUID id) {

        // usualmente esta validacao poderia estar em um validador
        final var pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException(id));

        mudarStatusPedidoService.aguardarEnvio(pedido);

        return ResponseEntity.accepted().build();
    }
}
