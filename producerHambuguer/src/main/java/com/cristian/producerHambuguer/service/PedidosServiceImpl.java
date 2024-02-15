package com.cristian.producerHambuguer.service;

import com.cristian.producerHambuguer.dto.PedidoDTO;
import com.cristian.producerHambuguer.dto.ProdutoDTO;
import com.cristian.producerHambuguer.entities.Clientes;
import com.cristian.producerHambuguer.entities.Produtos;
import com.cristian.producerHambuguer.repository.PedidosRepository;
import com.cristian.producerHambuguer.repository.ProdutosRepository;
import com.cristian.producerHambuguer.utils.PedidosMapperImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Service
public class PedidosServiceImpl implements PedidosServices {


    private final KafkaTemplate<String, Serializable> kafkaTemplate;
    @Autowired
    private PedidosMapperImpl pedidosMapperImpl;

    @Autowired
    private PedidosRepository pedidosRepository;

    @Autowired
    private ProdutosRepository produtosRepository;

    @Transactional
    @Override
    public void pedidosService(PedidoDTO pedidoDTO) {

        try {
            log.info("Salvando cliente e pedido");

            Clientes clientes = pedidosMapperImpl.pedidoDtoToClientes(pedidoDTO);

            pedidosRepository.save(clientes);

            List<ProdutoDTO> produtosDTO = pedidoDTO.getProdutos();
            List<Produtos> produtosList = new ArrayList<>();

            for (ProdutoDTO produtoDTO : produtosDTO) {
                Produtos produtos = pedidosMapperImpl.produtoDtoToProdutos(produtoDTO, pedidoDTO);

                produtosList.add(produtos);
            }

            produtosRepository.saveAll(produtosList);

            log.info("Pedido com numero {}, salvo", pedidoDTO.getNumeroPedido());
        } catch (Exception e) {
            log.error("Erro ao salvar o cliente ou o pedido");
        }

        log.info("Enviando pedido...");
        kafkaTemplate.send("burguer-topic", pedidoDTO);
    }
}
