package com._dev_ruan.helpDesk.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._dev_ruan.helpDesk.domain.Tecnico;
import com._dev_ruan.helpDesk.domain.dtos.TecnicoDTO;
import com._dev_ruan.helpDesk.domain.dtos.TecnicoUpdateDTO;
import com._dev_ruan.helpDesk.repositories.TecnicoRepository;
import com._dev_ruan.helpDesk.services.exceptions.DataIntegrityViolationException;
import com._dev_ruan.helpDesk.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository repository;
	
	
	public Tecnico findById(Integer id) {
		
		Optional<Tecnico> obj = repository.findById(id);
			
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: "+ id));
}
	
	public List<TecnicoDTO> findAll() {
		return repository.findAll()
				.stream()
				.map(TecnicoDTO::new)
				.collect(Collectors.toList());
		
		
	}
	
	public Tecnico createTecnico(TecnicoDTO objDTO) {
		Tecnico obj = fromDTO(objDTO);
			validarCPF(obj);
			validarEmail(obj);
		
		
		
		return repository.save(obj);
		}
	
	
	public Tecnico updateTecnico(Integer id, @Valid TecnicoUpdateDTO objDTO) {
	    Tecnico obj = findById(id);
	    
	    if (objDTO.getNome() != null) obj.setNome(objDTO.getNome());
	    if (objDTO.getEmail() != null) obj.setEmail(objDTO.getEmail());
	    if (objDTO.getCpf() != null) obj.setCpf(objDTO.getCpf());
	    
	    return repository.save(obj);
	}

	
	public void delete(Integer id) {
		
		Tecnico objDelete = repository.findById(id)
				.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado para realizar a deleção"));
		
		if(objDelete.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("À chamados ligados a este tecnico. Não é possível realizar a deleção");
		}
		
		repository.delete(objDelete);
		
		
		
	}
		
	
	public Tecnico fromDTO(TecnicoDTO objDTO) {
		Tecnico obj = new Tecnico(
				null,
				objDTO.getNome(),
				objDTO.getCpf(),
				objDTO.getEmail(),
				null
				);
		
		return obj;
	}
	
	public void validarCPF(Tecnico TecnicoOBJ) {
		
		
		if(TecnicoOBJ.getCpf() == null || repository.existsByCpf(TecnicoOBJ.getCpf()))   {
			
			throw new DataIntegrityViolationException("CPF");
			
		}	
	}
	
	public void validarEmail(Tecnico TecnicoOBJ) {
		if(TecnicoOBJ.getEmail() == null || repository.existsByEmail(TecnicoOBJ.getEmail()))   {
			throw new DataIntegrityViolationException("EMAIL");
			
		}
	
	}
	
	
	
	
	
	
}
