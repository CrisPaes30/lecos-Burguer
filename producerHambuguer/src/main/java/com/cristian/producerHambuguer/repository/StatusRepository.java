package com.cristian.producerHambuguer.repository;

import com.cristian.producerHambuguer.entities.Produtos;
import com.cristian.producerHambuguer.entities.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface StatusRepository extends JpaRepository<StatusPedido, Long> {

}
