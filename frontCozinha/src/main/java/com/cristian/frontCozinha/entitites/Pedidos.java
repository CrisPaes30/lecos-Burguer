package com.cristian.frontCozinha.entitites;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "produtos")
public class Pedidos {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
//    @SequenceGenerator(name = "sequence_generator", sequenceName = "produto_sequency", allocationSize = 1)
    private Long id;
    private Long numeroPedido;
    private String nomeProduto;
    private String complementoLanche;
    private String observacao;
    private Double precoUnitario;
    private Double precoTotal;
}
