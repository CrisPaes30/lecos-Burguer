package com.cristian.frontCozinha.entitites;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "clientes")
public class Clientes {

    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "NUMERO_PEDIDO")
    private Long numeroPedido;
    @Column(name = "CONTATO")
    private String contato;
    @Column(name = "NOME_CLIENTE")
    private String nomeCliente;
    @Column(name = "ENDERECO")
    private String endereco;
    @Column(name = "COMPLEMENTO")
    private String complemento;
}
