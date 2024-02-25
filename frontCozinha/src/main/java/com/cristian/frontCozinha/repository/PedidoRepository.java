package com.cristian.frontCozinha.repository;

import com.cristian.frontCozinha.entitites.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedidos, Long> {

    @Query(value ="SELECT nome_produto, complemento_lanche, preco_total " +
            "FROM produtos WHERE numero_pedido = ?", nativeQuery = true)
    Pedidos findByNumeroPedido(@Param("numeroPedido") String numeroPedido);
}
