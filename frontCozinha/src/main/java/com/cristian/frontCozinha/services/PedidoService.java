package com.cristian.frontCozinha.services;

import com.cristian.frontCozinha.entitites.Clientes;
import com.cristian.frontCozinha.entitites.Pedidos;
import com.cristian.frontCozinha.entitites.PedidosStatus;
import com.cristian.frontCozinha.repository.ClienteRepository;
import com.cristian.frontCozinha.repository.PedidoRepository;
import com.cristian.frontCozinha.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class PedidoService {

    private final StatusRepository statusRepository;
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;

    public List<Pedidos> processarPedidosPendentes() {
        log.info("Processando pedidos pendentes");

        List<Pedidos> pedidosPendentes = new ArrayList<>();

        try {
            List<PedidosStatus> pedidosPendentesStatus = statusRepository.findByStatus("PENDENTE");

            for (PedidosStatus pedidosStatus : pedidosPendentesStatus) {
                Long numeroPedido = pedidosStatus.getNumeroPedido();

                List<Pedidos> pedidos = pedidoRepository.findByNumeroPedido(numeroPedido);

                StringBuilder nomeProdutos = new StringBuilder();
                StringBuilder complementoPedidos = new StringBuilder();

                for (int i = 0; i < pedidos.size(); i++) {
                    Pedidos pedido = pedidos.get(i);

                    if (i > 0) {
                        nomeProdutos.append(" / "); // Adiciona a barra entre os produtos
                        complementoPedidos.append(" / "); // Adiciona a barra entre os complementos
                    }

                    nomeProdutos.append(pedido.getNomeProduto());
                    complementoPedidos.append(pedido.getComplementoLanche());
                }

                Pedidos novoPedido = new Pedidos();
                novoPedido.setNumeroPedido(numeroPedido);
                novoPedido.setNomeProduto(nomeProdutos.toString());
                novoPedido.setComplementoLanche(complementoPedidos.toString());
                pedidosPendentes.add(novoPedido);
            }

        } catch (Exception e) {
            log.error("Erro ao processar pedidos pendentes: {}", e.getMessage());
        }

        return pedidosPendentes;
    }

    public Clientes dadosClientes(Long numeroPedido) {
        log.info("Buscando dados do cliente");
        Clientes clientes = null;

        try {
            clientes = clienteRepository.findByNumeroPedido(numeroPedido);
        } catch (Exception e) {
            log.error("Erro ao obter dados do cliente: {}", e.getMessage());
        }

        return clientes;
    }

    public List<Pedidos> buscaPedidosEmAndamento() {
        log.info("Buscando Pedidos Em Andamento");

        List<Pedidos> pedidosEmAndamento = new ArrayList<>();

        try {
            List<PedidosStatus> andamento = statusRepository.findByStatus("EM ANDAMENTO");

            for (PedidosStatus pedidosStatus : andamento) {
                Long numeroPedido = pedidosStatus.getNumeroPedido();

                List<Pedidos> pedidos = pedidoRepository.findByNumeroPedido(numeroPedido);

                StringBuilder nomeProdutos = new StringBuilder();
                StringBuilder complementoPedidos = new StringBuilder();

                for (int i = 0; i < pedidos.size(); i++) {
                    Pedidos pedido = pedidos.get(i);

                    if (i > 0) {
                        nomeProdutos.append(" / "); // Adiciona a barra entre os produtos
                        complementoPedidos.append(" / "); // Adiciona a barra entre os complementos
                    }

                    nomeProdutos.append(pedido.getNomeProduto());
                    complementoPedidos.append(pedido.getComplementoLanche());
                }

                Pedidos novoPedido = new Pedidos();
                novoPedido.setNumeroPedido(numeroPedido);
                novoPedido.setNomeProduto(nomeProdutos.toString());
                novoPedido.setComplementoLanche(complementoPedidos.toString());
                pedidosEmAndamento.add(novoPedido);
            }
        } catch (Exception e) {
            log.error("Não foi possivel buscar pedidos em andamento");
        }

        return pedidosEmAndamento;
    }

    public List<Pedidos> buscaPedidosFinalizadosDoDia() {
        log.info("Buscando Pedidos Finalizados do Dia");

        List<Pedidos> pedidosFinalizados = new ArrayList<>();

        LocalDate date = LocalDate.now();

        try {
            List<PedidosStatus> finalizados = statusRepository.findByStatusData("FINALIZADO", date);

            for (PedidosStatus pedidosStatus : finalizados) {
                Long numeroPedido = pedidosStatus.getNumeroPedido();

                List<Pedidos> pedidos = pedidoRepository.findByNumeroPedido(numeroPedido);

                StringBuilder nomeProdutos = new StringBuilder();
                StringBuilder complementoPedidos = new StringBuilder();

                for (int i = 0; i < pedidos.size(); i++) {
                    Pedidos pedido = pedidos.get(i);

                    if (i > 0) {
                        nomeProdutos.append(" / "); // Adiciona a barra entre os produtos
                        complementoPedidos.append(" / "); // Adiciona a barra entre os complementos
                    }

                    nomeProdutos.append(pedido.getNomeProduto());
                    complementoPedidos.append(pedido.getComplementoLanche());
                }

                Pedidos novoPedido = new Pedidos();
                novoPedido.setNumeroPedido(numeroPedido);
                novoPedido.setNomeProduto(nomeProdutos.toString());
                novoPedido.setComplementoLanche(complementoPedidos.toString());
                pedidosFinalizados.add(novoPedido);
            }
        } catch (RuntimeException e) {
            log.error("Não foi possivel buscar pedidos finalizados do dia");
        }

        return pedidosFinalizados;
    }

    public void atualizaStatus(Long numeroPedido, String status) {
        try {
            statusRepository.updateStatus(numeroPedido, status);
        } catch (RuntimeException e) {
            log.info("Não foi possivel atualizar o status");
        }
    }

    public void salvarTempoRelogioNovosPedidos(Long numeroPedido, String tempo) {
        try {
            log.info("Salvando tempo de novos pedidos: {}", numeroPedido);
            statusRepository.saveTempoNovosPedidos(numeroPedido, tempo);

            log.info("Tempo tempo do pedido {} salvo com sucesso.", numeroPedido);
        } catch (RuntimeException e) {
            log.error("Não foi possível salvar o tempo do pedido do pedido {}!", numeroPedido, e);
        }
    }

    public void salvarTempoRelogioEmAndamento(Long numeroPedido, String segundos) {
        try {
            log.info("Salvando tempo de Em Andamento: {}", numeroPedido);
            statusRepository.saveTempoEmAndamento(numeroPedido, segundos);

            log.info("Tempo em andamento {} salvo com sucesso.", numeroPedido);
        } catch (RuntimeException e) {
            log.error("Não foi possível salvar o tempo em andamento do pedido {}!", numeroPedido, e);
        }
    }

    public Pedidos searchPedido(Long numero) {

        log.info("*******Procurando Pedido pelo numero: " + numero + "******");

        Pedidos pedidos = new Pedidos();

        try {
            List<Pedidos> listaPedidos = pedidoRepository.findByNumeroPedido(numero);

            if (!listaPedidos.isEmpty()) {
                // Inicializa StringBuilders para nomeProduto e complementoLanche
                StringBuilder nomeProdutos = new StringBuilder();
                StringBuilder complementoPedidos = new StringBuilder();

                // Itera sobre a lista de pedidos encontrados
                for (Pedidos pedido : listaPedidos) {
                    // Verifica se é o primeiro pedido
                    if (nomeProdutos.length() > 0) {
                        nomeProdutos.append(" / ");
                        complementoPedidos.append(" / ");
                    }

                    // Adiciona nome do produto e complemento ao StringBuilder
                    nomeProdutos.append(pedido.getNomeProduto());
                    complementoPedidos.append(pedido.getComplementoLanche());

                    // Define outros atributos do pedido
                    pedidos.setNumeroPedido(pedido.getNumeroPedido());
                    pedidos.setPrecoTotal(pedido.getPrecoTotal());
                    pedidos.setObservacao(pedido.getObservacao());
                }

                // Define os valores dos StringBuilders no pedido
                pedidos.setNomeProduto(nomeProdutos.toString());
                pedidos.setComplementoLanche(complementoPedidos.toString());
            } else {
                log.info("Nenhum pedido encontrado com o número: " + numero);
            }
        } catch (RuntimeException e) {
            log.error("Erro ao procurar o pedido: " + e.getMessage());
        }

        return pedidos;
    }


    public String buscaStatus(Long numero) {

        String retorno = null;
        try {
            PedidosStatus status = statusRepository.finByNumeroPedido(numero);

            if (status != null) {
                retorno = status.getStatus();
            } else {
                retorno = "";
            }
        } catch (RuntimeException e) {
            log.error("Não foi possivel buscar o numero de pedido do status");
        }

        return retorno;
    }

    public String buscaTempoDoPedido(Long numero) {

        try {
            PedidosStatus tempo = statusRepository.findbyTempoEmAndamentoAndTempoNovosPedidos(numero);
            String tempoNovosPedidos = tempo.getTempoNovosPedidos();
            String tempoEmAndamento = tempo.getTempoEmAndamento();

            String[] split = tempoNovosPedidos.split(":");
            int novosPedidosMinutos = Integer.parseInt(split[0]);
            int novosPedidosSegundos = Integer.parseInt(split[1]);

            String[] split2 = tempoEmAndamento.split(":");
            int emAndamentoMinutos = Integer.parseInt(split2[0]);
            int emAndamentoSegundos = Integer.parseInt(split2[1]);

            int totalMinutos = novosPedidosMinutos + emAndamentoMinutos;
            int totalSegundos = novosPedidosSegundos + emAndamentoSegundos;

            // Ajustar os segundos para não exceder 59
            if (totalSegundos >= 60) {
                totalSegundos -= 60;
                totalMinutos += 1;
            }

            String tempoTotal = totalMinutos + ":" + String.format("%02d", totalSegundos);
            salvaTempoTotal(numero, tempoTotal);
            return tempoTotal;

        } catch (RuntimeException e) {
            log.error("Não foi possível buscar o tempo do pedido");
            return ""; // Retorna uma string vazia se ocorrer uma exceção
        }
    }
    private String salvaTempoTotal(Long numero, String tempoTotal) {
        try {
            statusRepository.saveTempoTotal(numero, tempoTotal);
            return tempoTotal;
        } catch (RuntimeException e) {
            log.error("Não foi possível salvar o tempo total do pedido");
            return "";
        }
    }
}
