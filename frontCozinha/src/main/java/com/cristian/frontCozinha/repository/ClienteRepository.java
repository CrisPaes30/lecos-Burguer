package com.cristian.frontCozinha.repository;

import com.cristian.frontCozinha.entitites.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<Clientes, Long> {

    @Query(value ="SELECT * " +
            "FROM clientes WHERE numero_pedido = ?", nativeQuery = true)
    Clientes findByNumeroPedido(@Param("numeroPedido") Long numeroPedido);
}
