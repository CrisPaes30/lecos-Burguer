package com.cristian.producerHambuguer.repository;

import com.cristian.producerHambuguer.entities.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidosRepository extends JpaRepository<Clientes, Long> {

}
