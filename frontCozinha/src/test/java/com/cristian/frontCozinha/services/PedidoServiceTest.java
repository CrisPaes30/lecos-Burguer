package com.cristian.frontCozinha.services;

import com.cristian.frontCozinha.entitites.Clientes;
import com.cristian.frontCozinha.entitites.PedidosStatus;
import com.cristian.frontCozinha.entitites.Produtos;
import com.cristian.frontCozinha.repository.ClienteRepository;
import com.cristian.frontCozinha.repository.PedidoRepository;
import com.cristian.frontCozinha.repository.StatusRepository;
import com.cristian.frontCozinha.utils.Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @InjectMocks
    PedidoService pedidoService;
    @Mock
    StatusRepository statusRepository;
    @Mock
    PedidoRepository pedidoRepository;
    @Mock
    ClienteRepository clienteRepository;

    @Test
    void testProcessarPedidosPendentes() {
        Utils utils = new Utils();
        List<PedidosStatus> statusPendente = utils.retornaPedidosStatusStep();
        List<Produtos> produtos = utils.retornaPedidosStep();

        when(statusRepository.findByStatus(any())).thenReturn(statusPendente);
        when(pedidoRepository.findByNumeroPedido(any())).thenReturn(produtos);

        pedidoService.processarPedidosPendentes();
        verify(statusRepository, times(1)).findByStatus(any());
        verify(pedidoRepository, times(1)).findByNumeroPedido(any());
    }

    @Test
    public void testExceptionProcessarPedidosPendentes() {
        when(statusRepository.findByStatus(any())).thenReturn(null);

        assertThrows(NullPointerException.class,
                () -> pedidoService.processarPedidosPendentes());
    }

    @Test
    public void testaDadosClientes() {
        Utils utils = new Utils();
        Clientes clientes = utils.retornaClientesSteps();
        when(clienteRepository.findByNumeroPedido(any())).thenReturn(clientes);

        pedidoService.dadosClientes(clientes.getNumeroPedido());

        verify(clienteRepository, times(1)).findByNumeroPedido(any());
    }

    @Test
    void testProcessarPedidosEmAndamento() {
        Utils utils = new Utils();
        List<PedidosStatus> statusEmAndamento = utils.retornaPedidosStatusEmAndamentoStep();
        List<Produtos> produtos = utils.retornaPedidosStep();

        when(statusRepository.findByStatus(any())).thenReturn(statusEmAndamento);
        when(pedidoRepository.findByNumeroPedido(any())).thenReturn(produtos);

        pedidoService.buscaPedidosEmAndamento();
        verify(statusRepository, times(1)).findByStatus(any());
        verify(pedidoRepository, times(1)).findByNumeroPedido(any());
    }


    @Test
    void testProcessarPedidosFinalizadosDoDia() {
        Utils utils = new Utils();
        List<PedidosStatus> finalizados = utils.retornaPedidosFinalizadosStep();
        List<Produtos> produtos = utils.retornaPedidosStep();

        when(statusRepository.findByStatusData(any(), any())).thenReturn(finalizados);
        when(pedidoRepository.findByNumeroPedido(any())).thenReturn(produtos);

        pedidoService.buscaPedidosFinalizadosDoDia();
        verify(statusRepository, times(1)).findByStatusData(any(), any());
        verify(pedidoRepository, times(1)).findByNumeroPedido(any());
    }

    @Test
    void testDeveAtualizarStatus() {
        String status = "EM ANDAMENTO";
        Long numeroPedido = 1L;
        doNothing().when(statusRepository).updateStatus(any(), any());
        pedidoService.atualizaStatus(numeroPedido, status);
        verify(statusRepository, times(1)).updateStatus(any(), any());
    }

    @Test
    void testDeveSalvarTempoRelogioNovosPedidos() {
        String tempo = "30";
        Long numeroPedido = 1L;

        doNothing().when(statusRepository).saveTempoNovosPedidos(any(), any());
        pedidoService.salvarTempoRelogioNovosPedidos(numeroPedido, tempo);

        verify(statusRepository, times(1)).saveTempoNovosPedidos(any(), any());

    }

    @Test
    void testDeveSalvarTempoRelogioPedidosEmAndamento() {
        String tempo = "30";
        Long numeroPedido = 1L;

        doNothing().when(statusRepository).saveTempoEmAndamento(any(), any());

        pedidoService.salvarTempoRelogioEmAndamento(numeroPedido, tempo);

        verify(statusRepository, times(1)).saveTempoEmAndamento(any(), any());

    }

    @Test
    void testDeveSearchPedido() {
        Utils utils = new Utils();
        List<Produtos> produtos = utils.retornaPedidosStep();
        Long numero = 1L;

        when(pedidoRepository.findByNumeroPedido(any())).thenReturn(produtos);

        pedidoService.searchPedido(numero);

        verify(pedidoRepository, times(1)).findByNumeroPedido(any());

    }

    @Test
    void testDevebuscarStatus() {
        Utils utils = new Utils();
        PedidosStatus status = utils.retornaStatusStep();
        Long numero = 1L;

        when(statusRepository.finByNumeroPedido(any())).thenReturn(status);

        pedidoService.buscaStatus(numero);

        verify(statusRepository, times(1)).finByNumeroPedido(any());
    }

    @Test
    void testNaoDevebuscarStatus() {
        Long numero = 1L;

        when(statusRepository.finByNumeroPedido(any())).thenReturn(null);

        pedidoService.buscaStatus(numero);

        verify(statusRepository, times(1)).finByNumeroPedido(any());
    }

    @Test
    void testBuscaTempoDoPedido() {
        Utils utils = new Utils();
        PedidosStatus status = utils.retornaStatusStep();
        Long numero = 1L;

        when(statusRepository.findbyTempoEmAndamentoAndTempoNovosPedidos(any())).thenReturn(status);

        pedidoService.buscaTempoDoPedido(numero);

        verify(statusRepository, times(1)).findbyTempoEmAndamentoAndTempoNovosPedidos(any());

    }

    @Test
    void testDeveRemoverCaracteresEspeciais(){

        String expected = "11999999999";

        String telefone = "(11) 99999-9999";

        pedidoService.removeCaracteres(telefone);

        assertEquals(expected, pedidoService.removeCaracteres(telefone));
    }

}