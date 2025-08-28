package com._dev_ruan.helpDesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com._dev_ruan.helpDesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}
