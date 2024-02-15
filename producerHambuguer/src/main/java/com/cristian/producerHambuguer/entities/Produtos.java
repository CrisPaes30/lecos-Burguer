package com.cristian.producerHambuguer.entities;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "produtos")
public class Produtos {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator", sequenceName = "produto_sequency", allocationSize = 1)
    private Long id;
    private String numeroPedido;
    private String nomeProduto;
    private String complementoLanche;
    private String observacao;
    private Double precoUnitario;
    private Double precoTotal;
}
