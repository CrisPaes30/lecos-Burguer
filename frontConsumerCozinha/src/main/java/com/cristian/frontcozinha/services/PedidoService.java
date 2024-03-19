package com.cristian.frontcozinha.services;


import com.cristian.frontcozinha.entitites.Pedidos;
import com.cristian.frontcozinha.entitites.PedidosStatus;
import com.cristian.frontcozinha.repository.PedidoRepository;
import com.cristian.frontcozinha.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class PedidoService {

    private final StatusRepository statusRepository;
    private final PedidoRepository pedidoRepository;

    public void processarPedidosPendentes() {

        try {

            List<PedidosStatus> pedidosPendentes = statusRepository.findAll();

            // Processa os pedidos pendentes
            if(!pedidosPendentes.isEmpty()) {
                String numeroPedido = null;

                for (PedidosStatus pedidosStatus : pedidosPendentes) {
                    if (pedidosStatus.getStatus().equals("PENDENTE")) {
                        numeroPedido = pedidosStatus.getNumeroPedido();
                    }
                }

                if (numeroPedido != null) {
                    Pedidos pedidos = pedidoRepository.findByNumeroPedido(numeroPedido);
                    Pedidos novosPedidos = new Pedidos();
                    novosPedidos.setNumeroPedido(pedidos.getNumeroPedido());
                    novosPedidos.setNomeProduto(pedidos.getNomeProduto());
                    novosPedidos.setComplementoLanche(pedidos.getComplementoLanche());
                    novosPedidos.setObservacao(pedidos.getObservacao());
                    novosPedidos.setPrecoTotal(pedidos.getPrecoTotal());

                    adicionarNovoPedido(novosPedidos);
                }
            }else {
                log.info("Sem pedidos Pendentes");
            }
        }catch (Exception e){
            log.error("Não foi possivel obter os pedidos");
        }
    }

    private void adicionarNovoPedido(Pedidos pedidos) {

    }
}
