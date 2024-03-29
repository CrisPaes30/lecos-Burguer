package com.cristian.frontCozinha.repository;

import com.cristian.frontCozinha.entitites.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedidos, Long> {

    @Query(value ="SELECT * " +
            "FROM produtos WHERE numero_pedido = ?", nativeQuery = true)
    List<Pedidos> findByNumeroPedido(@Param("numeroPedido") Long numeroPedido);
}
