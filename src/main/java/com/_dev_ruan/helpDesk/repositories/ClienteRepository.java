package com._dev_ruan.helpDesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com._dev_ruan.helpDesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	boolean existsByCpf(String cpf);
	boolean existsByEmail(String email);
}
