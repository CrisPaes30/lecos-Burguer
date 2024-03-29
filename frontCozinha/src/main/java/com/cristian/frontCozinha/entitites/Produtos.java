package com.cristian.frontCozinha.entitites;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "produtos")
public class Produtos {

    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "NUMERO_PEDIDO")
    private Long numeroPedido;
    @Column(name = "NOME_PRODUTO")
    private String nomeProduto;
    @Column(name = "COMPLEMENTO_LANCHE")
    private String complementoLanche;
    @Column(name = "OBSERVACOES")
    private String observacao;
    @Column(name = "PRECO_UNITARIO")
    private Double precoUnitario;
    @Column(name = "PRECO_TOTAL")
    private Double precoTotal;
    @Column(name = "TAXA_ENTREGA")
    private Double taxaEntrega;
    @Column(name = "FORMA_PAGAMENTO")
    private String formaPagamento;
}
