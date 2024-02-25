package com.cristian.frontCozinha.entitites;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "status_pedido")
public class PedidosStatus {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
//    @SequenceGenerator(name = "sequence_generator", sequenceName = "status_sequency", allocationSize = 1)
    private Long id;
    @Column(name = "numero_pedido")
    private String numeroPedido;
    @Column(name = "status")
    private String status;
}
