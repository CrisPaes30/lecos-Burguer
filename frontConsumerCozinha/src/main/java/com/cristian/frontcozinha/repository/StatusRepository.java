package com.cristian.frontcozinha.repository;


import com.cristian.frontcozinha.entitites.PedidosStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<PedidosStatus, Long> {
}
