package com.cristian.frontCozinha.cupom;

import com.cristian.frontCozinha.entitites.Clientes;
import com.cristian.frontCozinha.entitites.Produtos;
import com.cristian.frontCozinha.services.PedidoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class ImpressaoCupom {
    @Autowired
    private PedidoService pedidoService;

    public void geraCupomFiscal(String numeroPedido) {
        log.info("Gerando cupom fiscal para o pedido: {}", numeroPedido);
        try {
            Long numero = Long.valueOf(numeroPedido);
            Clientes clientes = pedidoService.dadosClientes(numero);

            // Dados do cliente
            String nome = clientes.getNomeCliente();
            String telefone = clientes.getContato();
            String endereco = clientes.getEndereco();
            String bairro = clientes.getBairro();
            String complemento = clientes.getComplemento();
            String referencia = clientes.getReferencia();
            String cidade = clientes.getCidade();

            // Total do pedido e taxa de entrega
            Produtos produtos = new Produtos();
            List<Produtos> pedidos = pedidoService.buscaPedidosFinalizadosDoDia();
            List<String> itensList = new ArrayList<>();

            for (Produtos p: pedidos) {
                if(p.getNumeroPedido().equals(numero)){
                    String[] produto = p.getNomeProduto().split("/");
                    String[] comple = p.getComplementoLanche().split("/");

                    int maxLength = Math.max(produto.length, comple.length);
                    for (int i = 0; i < maxLength; i++) {
                        String nomeProduto = i < produto.length ? produto[i] : "";
                        String complementoLanche = i < comple.length ? comple[i] : "";
                        itensList.add(nomeProduto + " " + complementoLanche);
                        produtos.setPrecoUnitario(p.getPrecoUnitario());
                        produtos.setPrecoTotal(p.getPrecoTotal());
                        produtos.setFormaPagamento(p.getFormaPagamento());
                        produtos.setTaxaEntrega(p.getTaxaEntrega());
                    }
                }
            }

            Double valorPrecoUnitario = produtos.getPrecoUnitario();
            Double totalPedido = produtos.getPrecoTotal();
            Double taxaEntrega = produtos.getTaxaEntrega();
            Double valorTotal = totalPedido + taxaEntrega;

            // Caminho para o arquivo de texto
            String caminhoArquivo = "cupom_fiscal.txt";

            // Imprimir o cupom fiscal
            try {
                imprimirCupom(numero, nome, telefone, endereco, bairro, complemento, referencia, cidade,
                        itensList, valorPrecoUnitario, totalPedido ,valorTotal, taxaEntrega, produtos.getFormaPagamento(), caminhoArquivo);
            } catch (IOException e) {
                System.out.println("Erro ao criar o arquivo: " + e.getMessage());
            }

        } catch (RuntimeException e) {
            log.error("Erro ao obter dados do cliente: {}", e.getMessage());
        }
    }

    private static void imprimirCupom(Long numeroPedido, String nome, String telefone, String endereco, String bairro,
                                      String complemento, String referencia, String cidade, List<String> itens,
                                      Double valorPrecoUnitario, Double totalPedido, Double valorTotal, Double taxaEntrega,
                                      String formaPagamento, String caminhoArquivo) throws IOException {
        // Obtendo a data e hora do pedido
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dataHoraPedido = dateFormat.format(new Date());

        // Criação do arquivo e escrita do conteúdo
        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            // Escrever cabeçalho
            writer.write("---------------------------------------------------------------------------------------\n");
            writer.write("                             Leco´s Burguer\n");
            writer.write("+-------------------------------------------------------------------------------------+\n");
            writer.write(String.format("                                Pedido: %-80d\n", numeroPedido));
            writer.write("+-------------------------------------------------------------------------------------+\n\n");
            writer.write(" Data: " + dataHoraPedido + "\n\n");
            writer.write(" **************Obrigado por pedir no Leco´s Burguer!!!*****************\n\n");

            // Escrever dados do cliente
            writer.write(" Dados do Cliente\n");
            writer.write(" Nome: " + nome + "\n");
            writer.write(" Telefone: " + telefone + "\n");
            writer.write(" Endereço: " + endereco + "\n");
            writer.write(" Bairro: " + bairro + "\n");
            writer.write(" Compl: " + complemento + "\n");
            writer.write(" Ref: " + referencia + "\n");
            writer.write(" Cidade: " + cidade + "\n\n");

            // Escrever itens do pedido
            writer.write(" Qtd  Item                                                          Preço\n");
            for (String item : itens) {
                // Limitar o tamanho do item para 50 caracteres
                if (item.length() > 50) {
                    item = item.substring(0, 50);
                }
                writer.write(String.format("%-4d%-60s   R$ %7.2f\n",  1, item, valorPrecoUnitario));
            }

            // Escrever subtotal, taxa de entrega, forma de pagamento e valor total
            writer.write("\n+-------------------------------------------------------------------------------------+\n");
            writer.write(String.format("Valor total do Pedido:                                             R$ %7.2f\n", totalPedido));
            writer.write(String.format("Taxa de Entrega:                                                   R$ %7.2f\n", taxaEntrega));
            writer.write(String.format("Forma de Pagamento:                                                %-40s\n", formaPagamento));
            writer.write("+--------------------------------------------------------------------------------------+\n");
            writer.write(String.format("Cobrar do Cliente                                                  R$ %7.2f\n", valorTotal));
            writer.write("+-------------------------------------------------------------------------------------+\n");
        }
    }
}
