package com.cristian.frontCozinha.entitites;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "status_pedido")
public class PedidosStatus {

    @Id
    private Long id;
    @Column(name = "numero_pedido")
    private Long numeroPedido;
    @Column(name = "status")
    private String status;
    @Column(name = "data")
    private String data;
    @Column(name = "tempo_novos_pedidos")
    private String tempoNovosPedidos;
    @Column(name = "tempo_Em_Andamento")
    private String tempoEmAndamento;
    @Column(name = "tempo_total")
    private String tempoTotal;
}
