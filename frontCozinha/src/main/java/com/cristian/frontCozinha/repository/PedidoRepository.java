package com.cristian.frontCozinha.repository;

import com.cristian.frontCozinha.entitites.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Produtos, Long> {

    @Query(value ="SELECT * " +
            "FROM produtos WHERE numero_pedido = ?", nativeQuery = true)
    List<Produtos> findByNumeroPedido(@Param("numeroPedido") Long numeroPedido);
}
