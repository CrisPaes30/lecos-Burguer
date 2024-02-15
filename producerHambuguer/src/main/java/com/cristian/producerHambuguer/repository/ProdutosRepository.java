package com.cristian.producerHambuguer.repository;

import com.cristian.producerHambuguer.entities.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long> {

}
