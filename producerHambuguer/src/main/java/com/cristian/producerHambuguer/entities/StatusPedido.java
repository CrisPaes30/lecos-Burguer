package com.cristian.producerHambuguer.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "status_pedido")
public class StatusPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator", sequenceName = "status_sequency", allocationSize = 1)
    private Long id;
    @Column(name = "numero_pedido")
    private String numeroPedido;
    @Column(name = "status")
    private String status;
    @Column(name = "data")
    private LocalDate date;
    @Column(name = "tempo_novos_pedidos")
    private String tempoNovosPedidos;
    @Column(name = "tempo_Em_Andamento")
    private String tempoEmAndamento;
    @Column(name = "tempo_total")
    private String tempoTotal;
}
