package com.cristian.frontCozinha.job;

import com.cristian.frontCozinha.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PedidoJobScheduler {
    @Autowired
    PedidoService pedidoService;

    @Autowired
    public PedidoJobScheduler(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Scheduled(fixedRate = 180000) // Executa a cada 3 minutos
    public void buscarPedidosPendentes() {
        pedidoService.processarPedidosPendentes();
    }
}
