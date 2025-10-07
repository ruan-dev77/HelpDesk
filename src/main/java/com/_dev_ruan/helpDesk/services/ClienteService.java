package com._dev_ruan.helpDesk.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._dev_ruan.helpDesk.domain.Cliente;
import com._dev_ruan.helpDesk.domain.dtos.ClienteDTO;
import com._dev_ruan.helpDesk.domain.dtos.ClienteUpdateDTO;
import com._dev_ruan.helpDesk.repositories.ClienteRepository;
import com._dev_ruan.helpDesk.services.exceptions.DataIntegrityViolationException;
import com._dev_ruan.helpDesk.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public Cliente findById(Integer id) {
		
		Optional<Cliente> obj = repository.findById(id);
			
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: "+ id));
}
	
	public List<ClienteDTO> findAll() {
		return repository.findAll()
				.stream()
				.map(ClienteDTO::new)
				.collect(Collectors.toList());
		
		
	}
	
	public Cliente createCliente(ClienteDTO objDTO) {
		
		Cliente obj = fromDTO(objDTO);
		validarCPF(obj);
		validarEmail(obj);
	
	
	
	return repository.save(obj);		
		
	}
	
	public Cliente updateCliente(Integer id, @Valid ClienteUpdateDTO objDTO) {
	    Cliente obj = findById(id);
	    
	    if (objDTO.getNome() != null) obj.setNome(objDTO.getNome());
	    if (objDTO.getEmail() != null) obj.setEmail(objDTO.getEmail());
	    if (objDTO.getCpf() != null) obj.setCpf(objDTO.getCpf());
	    
	    return repository.save(obj);
	}
	
	public void delete(Integer id) {
		
		Cliente objDelete = repository.findById(id)
				.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado para realizar a deleção"));
		
		if(objDelete.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("À chamados ligados a este tecnico. Não é possível realizar a deleção");
		}
		
		repository.delete(objDelete);
		
		
		
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		Cliente obj = new Cliente(
				null,
				objDTO.getNome(),
				objDTO.getCpf(),
				objDTO.getEmail(),
				null
				);
		
		return obj;
	}
	
public void validarCPF(Cliente ClienteOBJ) {
		
		
		if(ClienteOBJ.getCpf() == null || repository.existsByCpf(ClienteOBJ.getCpf()))   {
			
			throw new DataIntegrityViolationException("CPF");
			
		}	
	}
	
	public void validarEmail(Cliente ClienteOBJ) {
		if(ClienteOBJ.getEmail() == null || repository.existsByEmail(ClienteOBJ.getEmail()))   {
			throw new DataIntegrityViolationException("EMAIL");
			
		}
	
	
	}}
