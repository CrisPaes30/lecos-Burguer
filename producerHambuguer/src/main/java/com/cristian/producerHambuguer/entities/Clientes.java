package com.cristian.producerHambuguer.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "clientes")
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator", sequenceName = "clientes_sequency", allocationSize = 1)
    private Long id;
    private String numeroPedido;
    private String nomeCliente;
    private String endereco;
    private String complemento;
}
