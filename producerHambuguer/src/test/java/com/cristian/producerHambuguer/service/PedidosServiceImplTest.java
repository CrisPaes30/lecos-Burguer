package com.cristian.producerHambuguer.service;

import com.cristian.producerHambuguer.dto.PedidoDTO;
import com.cristian.producerHambuguer.dto.ProdutoDTO;
import com.cristian.producerHambuguer.entities.Clientes;
import com.cristian.producerHambuguer.entities.Produtos;
import com.cristian.producerHambuguer.repository.PedidosRepository;
import com.cristian.producerHambuguer.repository.ProdutosRepository;
import com.cristian.producerHambuguer.repository.StatusRepository;
import com.cristian.producerHambuguer.service.util.Utils;
import com.cristian.producerHambuguer.utils.PedidosMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidosServiceImplTest {
    @InjectMocks
    PedidosServiceImpl pedidosServiceImpl;
    @Mock
    PedidosMapperImpl pedidosMapperImpl;
    @Mock
    PedidosRepository pedidosRepository;
    @Mock
    ProdutosRepository produtosRepository;
    @Mock
    StatusRepository statusRepository;

    @Test
    void testePedidosService() {

        Utils utils = new Utils();
        Clientes clientes = utils.retornaClientStubs();
        PedidoDTO produtosDto = utils.retornaProdutosDTOStubs();
        Produtos produtos = utils.retornaProdutosStubs();

        when(pedidosMapperImpl.pedidoDtoToClientes(any(), any())).thenReturn(clientes);
        when(pedidosMapperImpl.produtoDtoToProdutos(any(), any(), any())).thenReturn(produtos);

        pedidosServiceImpl.pedidosService(produtosDto);

        verify(pedidosMapperImpl,times(1)).pedidoDtoToClientes(any(),any());
        verify(pedidosMapperImpl,times(1)).produtoDtoToProdutos(any(),any(),any());

    }

}