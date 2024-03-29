package com.cristian.producerHambuguer.utils;

import com.cristian.producerHambuguer.dto.PedidoDTO;
import com.cristian.producerHambuguer.dto.ProdutoDTO;
import com.cristian.producerHambuguer.entities.Clientes;
import com.cristian.producerHambuguer.entities.Produtos;
import org.springframework.stereotype.Component;

@Component
public class PedidosMapperImpl implements PedidosMapper {

    @Override
    public Clientes pedidoDtoToClientes(PedidoDTO pedidoDTO, Long numeroPedido) {

        Clientes clientes = new Clientes();
        clientes.setNumeroPedido(numeroPedido);
        clientes.setContato(pedidoDTO.getContato());
        clientes.setEndereco(pedidoDTO.getEndereco());
        clientes.setComplemento(pedidoDTO.getComplementoEndereco());
        clientes.setBairro(pedidoDTO.getBairro());
        clientes.setCidade(pedidoDTO.getCidade());
        clientes.setReferencia(pedidoDTO.getReferencia());
        clientes.setNomeCliente(pedidoDTO.getCliente());

        return clientes;
    }

    @Override
    public Produtos produtoDtoToProdutos(ProdutoDTO produtoDTO, PedidoDTO pedidoDTO, Long numeroPedido) {

        Produtos produtos = new Produtos();
        produtos.setNomeProduto(produtoDTO.getNomeProduto());
        produtos.setComplementoLanche(produtoDTO.getComplementoLanche());
        produtos.setObservacao(produtoDTO.getObservacao());
        produtos.setPrecoUnitario(produtoDTO.getPrecoUnitario());
        produtos.setPrecoTotal(pedidoDTO.getPrecoTotal());
        produtos.setNumeroPedido(numeroPedido);
        produtos.setTaxaEntrega(pedidoDTO.getTaxaEntrega());
        produtos.setFormaPagamento(pedidoDTO.getFormaPagamento());

        return produtos;
    }
}
