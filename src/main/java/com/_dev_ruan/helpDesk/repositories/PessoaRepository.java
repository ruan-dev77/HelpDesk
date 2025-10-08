package com._dev_ruan.helpDesk.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com._dev_ruan.helpDesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
	Pessoa findByEmail(String email);
	Pessoa findByCpf(String cpf);
}
