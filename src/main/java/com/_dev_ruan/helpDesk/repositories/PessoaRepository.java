package com._dev_ruan.helpDesk.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com._dev_ruan.helpDesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
	Optional<Pessoa> findByEmail(String email);

}
