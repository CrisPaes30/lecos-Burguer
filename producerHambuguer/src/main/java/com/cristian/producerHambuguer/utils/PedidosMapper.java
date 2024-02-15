package com.cristian.producerHambuguer.utils;

import com.cristian.producerHambuguer.dto.PedidoDTO;
import com.cristian.producerHambuguer.dto.ProdutoDTO;
import com.cristian.producerHambuguer.entities.Clientes;
import com.cristian.producerHambuguer.entities.Produtos;
import org.mapstruct.Mapper;

@Mapper
public interface PedidosMapper {

    Clientes pedidoDtoToClientes(PedidoDTO pedidoDTO);

    Produtos produtoDtoToProdutos(ProdutoDTO produtoDTO, PedidoDTO pedidoDTO);
}
