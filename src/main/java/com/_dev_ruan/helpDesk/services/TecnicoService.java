package com._dev_ruan.helpDesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com._dev_ruan.helpDesk.domain.Pessoa;
import com._dev_ruan.helpDesk.domain.Tecnico;
import com._dev_ruan.helpDesk.domain.dtos.TecnicoDTO;
import com._dev_ruan.helpDesk.repositories.PessoaRepository;
import com._dev_ruan.helpDesk.repositories.TecnicoRepository;
import com._dev_ruan.helpDesk.services.exceptions.DataIntegrityViolationException;
import com._dev_ruan.helpDesk.services.exceptions.ObjectNotFoundException;



@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> tecnico = repository.findById(id);
		
		return tecnico.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado na base de dados"));
	}

	public List<TecnicoDTO> findAll() {
		List<Tecnico> list = repository.findAll();
		
		List<TecnicoDTO> listDTO = list.stream().map(x -> new TecnicoDTO(x)).toList();
		
		return listDTO;
		
	}
	
	public Tecnico create(TecnicoDTO objDTO) {
		objDTO.setId(null);
		objDTO.setSenha(passwordEncoder.encode(objDTO.getSenha()));
		validaPorCpfEEmail(objDTO);
		Tecnico newObj = new Tecnico(objDTO);
		return repository.save(newObj);
	}

	public Tecnico update(Integer id, TecnicoDTO objDTO) {
		objDTO.setId(id);
		Tecnico oldObj = findById(id);
		validaPorCpfEEmail(objDTO);
		oldObj = new Tecnico(objDTO);
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		Tecnico obj = findById(id);

		if (obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Técnico possui chamados e não pode ser deletado!");
		}

		repository.deleteById(id);
	}
	
	private void validaPorCpfEEmail(TecnicoDTO objDTO) {
		Optional<Pessoa> obj = Optional.ofNullable(pessoaRepository.findByCpf(objDTO.getCpf()));
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}

		obj = Optional.ofNullable(pessoaRepository.findByEmail(objDTO.getEmail()));
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}
	}

}
