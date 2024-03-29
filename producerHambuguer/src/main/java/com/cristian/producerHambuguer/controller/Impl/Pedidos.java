package com.cristian.producerHambuguer.controller.Impl;

import com.cristian.producerHambuguer.dto.PedidoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface Pedidos {

    @PostMapping
    ResponseEntity<String> pedidos(@RequestBody PedidoDTO pedidoDTO);
}
