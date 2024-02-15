package com.cristian.producerHambuguer.service;

import com.cristian.producerHambuguer.dto.PedidoDTO;
import com.cristian.producerHambuguer.dto.ProdutoDTO;
import com.cristian.producerHambuguer.entities.Clientes;
import com.cristian.producerHambuguer.entities.Produtos;
import com.cristian.producerHambuguer.entities.StatusPedido;
import com.cristian.producerHambuguer.repository.PedidosRepository;
import com.cristian.producerHambuguer.repository.ProdutosRepository;
import com.cristian.producerHambuguer.repository.StatusRepository;
import com.cristian.producerHambuguer.utils.PedidosMapperImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Service
public class PedidosServiceImpl implements PedidosServices {

    private final String STATUS_PENDENTE = "PENDENTE";

    private final KafkaTemplate<String, Serializable> kafkaTemplate;
    private final PedidosMapperImpl pedidosMapperImpl;
    private final PedidosRepository pedidosRepository;
    private final ProdutosRepository produtosRepository;
    private final StatusRepository statusRepository;

    @Transactional
    @Override
    public void pedidosService(PedidoDTO pedidoDTO) {
        try {
            log.info("Salvando cliente e pedido");

            Clientes cliente = pedidosMapperImpl.pedidoDtoToClientes(pedidoDTO);
            pedidosRepository.save(cliente);

            List<Produtos> produtosList = new ArrayList<>();
            for (ProdutoDTO produtoDTO : pedidoDTO.getProdutos()) {
                Produtos produto = pedidosMapperImpl.produtoDtoToProdutos(produtoDTO, pedidoDTO);
                produtosList.add(produto);
            }
            produtosRepository.saveAll(produtosList);

            salvarStatusPedido(pedidoDTO.getNumeroPedido());

            log.info("Pedido com numero {}, salvo", pedidoDTO.getNumeroPedido());
            log.info("Enviando pedido...");
            kafkaTemplate.send("burguer-topic", pedidoDTO);
            log.info("pedido enviado...");
        } catch (Exception e) {
            log.error("Erro ao salvar o cliente ou o pedido", e);
        }
    }

    private void salvarStatusPedido(Long numeroPedido) {
        try {
            log.info("Salvando status pedido");

            StatusPedido statusPedido = new StatusPedido();
            statusPedido.setNumeroPedido(numeroPedido.toString());
            statusPedido.setStatus(STATUS_PENDENTE);
            statusRepository.save(statusPedido);
        } catch (Exception e) {
            log.error("Não foi possível salvar o status pedido!", e);
        }
    }
}
