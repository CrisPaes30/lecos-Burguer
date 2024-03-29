package com.cristian.producerHambuguer.repository;

import com.cristian.producerHambuguer.entities.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long> {

    @Query(value = "SELECT MAX(p.numeroPedido) FROM Produtos p")
    Long findMaxNumeroPedido();

}
