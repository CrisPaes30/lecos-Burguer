package com.cristian.producerHambuguer.entities;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "clientes")
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator", sequenceName = "clientes_sequency", allocationSize = 1)
    private Long id;
    private Long numeroPedido;
    private String contato;
    private String nomeCliente;
    private String endereco;
    private String complemento;
    private String bairro;
    private String referencia;
    private String cidade;
}
