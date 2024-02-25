package com.cristian.frontCozinha.repository;

import com.cristian.frontCozinha.entitites.PedidosStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<PedidosStatus, Long> {
}
