package com.cristian.frontCozinha.job;

import com.cristian.frontCozinha.services.PedidoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class PedidoJobScheduler {
    @Autowired
    private PedidoService pedidoService;

    @Scheduled(fixedRate = 180000) // Executa a cada 3 minutos
    public void buscarPedidosPendentes() {
        log.info("**********Aguardando Schudeled ser processado, para atualização de tela**********");
        pedidoService.processarPedidosPendentes();
        log.info("*********Scheduled executado, proximo job em 3 min*******");
    }
}
