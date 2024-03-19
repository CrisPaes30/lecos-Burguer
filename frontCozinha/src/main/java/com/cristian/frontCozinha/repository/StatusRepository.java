package com.cristian.frontCozinha.repository;

import com.cristian.frontCozinha.entitites.PedidosStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface StatusRepository extends JpaRepository<PedidosStatus, Long> {

    List<PedidosStatus> findByStatus(String status);

    @Query(value ="SELECT * FROM status_pedido WHERE numero_pedido = ?1", nativeQuery = true)
    PedidosStatus finByNumeroPedido(Long numeroPedido);

    @Modifying
    @Transactional
    @Query(value ="UPDATE status_pedido SET status = ?2 WHERE numero_pedido = ?1", nativeQuery = true)
    void updateStatus(Long numeroPedido, String status);
    @Query(value ="SELECT * FROM status_pedido WHERE status = ?1 and data = ?2", nativeQuery = true)
    List<PedidosStatus> findByStatusData(String status, LocalDate data);

    @Modifying
    @Transactional
    @Query(value ="UPDATE status_pedido SET tempo_novos_pedidos = ?2 WHERE numero_pedido = ?1", nativeQuery = true)
    void saveTempoNovosPedidos(Long numeroPedido, String tempo);

    @Modifying
    @Transactional
    @Query(value ="UPDATE status_pedido SET tempo_em_andamento = ?2 WHERE numero_pedido = ?1", nativeQuery = true)
    void saveTempoEmAndamento(Long numeroPedido, String tempo);

    @Query(value ="SELECT * FROM status_pedido WHERE numero_pedido = ?1", nativeQuery = true)
    PedidosStatus findbyTempoEmAndamentoAndTempoNovosPedidos(Long numeroPedido);

    @Modifying
    @Transactional
    @Query(value ="UPDATE status_pedido SET tempo_total = ?2 WHERE numero_pedido = ?1", nativeQuery = true)
    void saveTempoTotal(Long numero, String tempoTotal);
}
