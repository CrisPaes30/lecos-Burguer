package com.cristian.producerHambuguer.service.util;

import com.cristian.producerHambuguer.dto.PedidoDTO;
import com.cristian.producerHambuguer.dto.ProdutoDTO;
import com.cristian.producerHambuguer.entities.Clientes;
import com.cristian.producerHambuguer.entities.Produtos;
import com.cristian.producerHambuguer.entities.StatusPedido;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public List<ProdutoDTO> retornaListaProdutosStubs(){
        List<ProdutoDTO> produtosList = new ArrayList<>();

        ProdutoDTO produtos = new ProdutoDTO();
        produtos.setNomeProduto("Teste");
        produtos.setComplementoLanche("Teste");
        produtos.setObservacao("Teste");
        produtos.setPrecoUnitario(10.0);

        produtosList.add(produtos);

        return produtosList;
    }

    public Produtos retornaProdutosStubs(){

        Produtos produtos = new Produtos();
        produtos.setNomeProduto("Teste");
        produtos.setComplementoLanche("Teste");
        produtos.setObservacao("Teste");
        produtos .setPrecoUnitario(10.0);
        produtos.setPrecoTotal(10.0);
        produtos.setTaxaEntrega(10.0);
        produtos.setFormaPagamento("Cartão de Credito");
        produtos.setNumeroPedido(1L);
        produtos.setId(1L);


        return produtos;
    }

    public PedidoDTO retornaProdutosDTOStubs(){

        PedidoDTO pedidoDTO = new PedidoDTO();

        pedidoDTO.setNumeroPedido(1L);
        pedidoDTO.setCliente("Teste");
        pedidoDTO.setEndereco("Rua do Teste");
        pedidoDTO.setComplementoEndereco("Não tem");
        pedidoDTO.setCidade("São Paulo");
        pedidoDTO.setBairro("Teste");
        pedidoDTO.setReferencia("Campo do teste");
        pedidoDTO.setContato("11999999999");
        pedidoDTO.setPrecoTotal(10.0);
        pedidoDTO.setTaxaEntrega(10.0);
        pedidoDTO.setFormaPagamento("Cartão de Credito");
        pedidoDTO.setProdutos(retornaListaProdutosStubs());
        pedidoDTO.setPrecoTotal(10.0);
        pedidoDTO.setTaxaEntrega(10.0);
        pedidoDTO.setFormaPagamento("Cartão de Credito");

        return pedidoDTO;
    }

    public Clientes retornaClientStubs(){
        Clientes clientes = new Clientes();
        clientes.setId(1L);
        clientes.setNumeroPedido(1L);
        clientes.setContato("11999999999");
        clientes.setEndereco("Rua do Teste");
        clientes.setComplemento("Não tem");
        clientes.setBairro("Teste");
        clientes.setCidade("São Paulo");
        clientes.setReferencia("Campo do teste");
        clientes.setNomeCliente("Teste");

        return clientes;
    }

    public StatusPedido retornaStatusStubs(){
        StatusPedido status = new StatusPedido();
        status.setId(1L);
        status.setNumeroPedido(1L);
        status.setStatus("PENDENTE");

        return status;
    }
}
