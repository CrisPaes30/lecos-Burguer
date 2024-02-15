package com.cristian.producerHambuguer.controller.Impl;

import com.cristian.producerHambuguer.dto.PedidoDTO;
import com.cristian.producerHambuguer.service.PedidosServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/pedidos")
public class PedidosControllerImpl implements Pedidos {

    private final PedidosServices pedidosServices;

    public ResponseEntity<PedidoDTO> pedidos(@RequestBody PedidoDTO pedidos) {
        pedidosServices.pedidosService(pedidos);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{numeroPedido}").buildAndExpand(pedidos.getNumeroPedido()).toUri();
        return ResponseEntity.created(uri).body(pedidos);
    }
}
