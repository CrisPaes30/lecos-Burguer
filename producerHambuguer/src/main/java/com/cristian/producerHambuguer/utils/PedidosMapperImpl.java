package com.cristian.producerHambuguer.utils;

import com.cristian.producerHambuguer.controller.Impl.Pedidos;
import com.cristian.producerHambuguer.dto.PedidoDTO;
import com.cristian.producerHambuguer.dto.ProdutoDTO;
import com.cristian.producerHambuguer.entities.Clientes;
import com.cristian.producerHambuguer.entities.Produtos;
import org.springframework.stereotype.Component;

@Component
public class PedidosMapperImpl implements PedidosMapper {

    @Override
    public Clientes pedidoDtoToClientes(PedidoDTO pedidoDTO) {

        Clientes clientes = new Clientes();
        clientes.setNumeroPedido(pedidoDTO.getNumeroPedido());
        clientes.setEndereco(pedidoDTO.getEndereco());
        clientes.setComplemento(pedidoDTO.getComplementoEndereco());
        clientes.setNomeCliente(pedidoDTO.getCliente());

        return clientes;
    }

    @Override
    public Produtos produtoDtoToProdutos(ProdutoDTO produtoDTO, PedidoDTO pedidoDTO) {

        Produtos produtos = new Produtos();
        produtos.setNomeProduto(produtoDTO.getNomeProduto());
        produtos.setComplementoLanche(produtoDTO.getComplementoLanche());
        produtos.setObservacao(produtoDTO.getObservacao());
        produtos.setPrecoUnitario(produtoDTO.getPrecoUnitario());
        produtos.setPrecoTotal(pedidoDTO.getPrecoTotal());
        produtos.setNumeroPedido(pedidoDTO.getNumeroPedido());

        return produtos;
    }
}
