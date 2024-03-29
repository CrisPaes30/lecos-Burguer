package com.cristian.frontCozinha.utils;

import com.cristian.frontCozinha.entitites.Clientes;
import com.cristian.frontCozinha.entitites.PedidosStatus;
import com.cristian.frontCozinha.entitites.Produtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public List<PedidosStatus> retornaPedidosStatusStep() {
        List<PedidosStatus> produtosList = new ArrayList<>();

        PedidosStatus status = new PedidosStatus();

        status.setStatus("PENDENTE");
        status.setData(String.valueOf(LocalDate.now()));
        status.setNumeroPedido(1L);
        status.setTempoEmAndamento("00:00:00");
        status.setTempoNovosPedidos("00:00:00");
        status.setTempoTotal("00:00:00");
        produtosList.add(status);

        return produtosList;
    }

    public PedidosStatus retornaStatusStep() {

        PedidosStatus status = new PedidosStatus();

        status.setStatus("PENDENTE");
        status.setData(String.valueOf(LocalDate.now()));
        status.setNumeroPedido(1L);
        status.setTempoEmAndamento("00:00:00");
        status.setTempoNovosPedidos("00:00:00");
        status.setTempoTotal("00:00:00");

        return status;
    }

    public List<Produtos> retornaPedidosStep() {
        List<Produtos> produtosList = new ArrayList<>();

        Produtos produtos = new Produtos();
        produtos.setNumeroPedido(1L);
        produtos.setNomeProduto("Hamburguer");
        produtos.setTaxaEntrega(5.0);
        produtos.setPrecoTotal(30.0);
        produtos.setComplementoLanche("Sem Complemento");
        produtos.setObservacao("sem picles");
        produtosList.add(produtos);

        return produtosList;
    }

    public Clientes retornaClientesSteps() {

        Clientes clientes = new Clientes();
        clientes.setNomeCliente("Cristian");
        clientes.setEndereco("Rua teste");
        clientes.setBairro("Centro");
        clientes.setCidade("Sao Paulo");
        clientes.setEndereco("rua do teste");
        clientes.setComplemento("sem complemento");
        clientes.setReferencia("sem referencia");
        clientes.setNumeroPedido(1L);

        return clientes;
    }

    public List<PedidosStatus> retornaPedidosStatusEmAndamentoStep() {
        List<PedidosStatus> produtosList = new ArrayList<>();

        PedidosStatus status = new PedidosStatus();

        status.setStatus("EM ANDAMENTO");
        status.setData(String.valueOf(LocalDate.now()));
        status.setNumeroPedido(1L);
        status.setTempoEmAndamento("00:00:00");
        status.setTempoNovosPedidos("00:00:00");
        status.setTempoTotal("00:00:00");
        produtosList.add(status);

        return produtosList;
    }

    public List<PedidosStatus> retornaPedidosFinalizadosStep() {
        List<PedidosStatus> produtosList = new ArrayList<>();

        PedidosStatus status = new PedidosStatus();

        status.setStatus("FINALIZADOS");
        status.setData(String.valueOf(LocalDate.now()));
        status.setNumeroPedido(1L);
        status.setTempoEmAndamento("00:00:00");
        status.setTempoNovosPedidos("00:00:00");
        status.setTempoTotal("00:00:00");
        produtosList.add(status);

        return produtosList;
    }
}
