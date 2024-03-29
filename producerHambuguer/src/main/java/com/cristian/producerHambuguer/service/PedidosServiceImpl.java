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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Service
public class PedidosServiceImpl implements PedidosServices {

    private final String STATUS_PENDENTE = "PENDENTE";

    private final PedidosMapperImpl pedidosMapperImpl;
    private final PedidosRepository pedidosRepository;
    private final ProdutosRepository produtosRepository;
    private final StatusRepository statusRepository;

    @Transactional
    @Override
    public Long pedidosService(PedidoDTO pedidoDTO) {

        Long numeroPedido = null;

        try {
            log.info("Salvando cliente e pedido");

            numeroPedido = validaNumeroPedido();

            Clientes cliente = pedidosMapperImpl.pedidoDtoToClientes(pedidoDTO, numeroPedido);
            pedidosRepository.save(cliente);

            List<Produtos> produtosList = new ArrayList<>();
            for (ProdutoDTO produtoDTO : pedidoDTO.getProdutos()) {
                Produtos produto = pedidosMapperImpl.produtoDtoToProdutos(produtoDTO, pedidoDTO, numeroPedido);
                produtosList.add(produto);
            }
            produtosRepository.saveAll(produtosList);

            salvarStatusPedido(numeroPedido);

            log.info("Pedido com numero {}, salvo", numeroPedido);
        } catch (Exception e) {
            log.error("Erro ao salvar o cliente ou o pedido", e);
        }

        return numeroPedido;
    }

    private void salvarStatusPedido(Long numeroPedido) {
        try {
            log.info("Salvando status pedido");

            StatusPedido statusPedido = new StatusPedido();
            statusPedido.setNumeroPedido(numeroPedido);
            statusPedido.setStatus(STATUS_PENDENTE);

            // Obtém a data atual
            LocalDate dataAtual = LocalDate.now();

            // Define a data atual no status do pedido
            statusPedido.setDate(dataAtual);

            // Salva o status do pedido
            statusRepository.save(statusPedido);

            log.info("Status do pedido com número " + numeroPedido + " salvo com sucesso.");
        } catch (Exception e) {
            log.error("Não foi possível salvar o status pedido!", e);
        }
    }

    public Long validaNumeroPedido() {

        Long produtos = produtosRepository.findMaxNumeroPedido();

        if (produtos == null) {
            produtos = 1L ;
        } else {
            produtos ++;
        }

        return produtos;
    }


}
