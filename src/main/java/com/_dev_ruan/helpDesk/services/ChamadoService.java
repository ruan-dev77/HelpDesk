package com._dev_ruan.helpDesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._dev_ruan.helpDesk.domain.Chamado;
import com._dev_ruan.helpDesk.domain.dtos.ChamadoDTO;
import com._dev_ruan.helpDesk.repositories.ChamadoRepository;
import com._dev_ruan.helpDesk.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {
	
	@Autowired
	private ChamadoRepository repository;
	
		
	public Chamado findById(Integer id) {
			
			Optional<Chamado> obj = repository.findById(id);
				
			return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: "+ id));
}
	public List<Chamado> findAll() {
		return repository.findAll();
	}
	
	public Chamado createChamado(Chamado chm) {
		return repository.save(chm);
	}
	
	public void delete(Integer id) {
        Chamado obj = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado! Id: " + id));
        repository.delete(obj);
    }
	
	public Chamado update(Integer id , ChamadoDTO objDto) {
		Chamado obj = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado! Id:" + id));
		
		
		 obj.setTitulo(objDto.getTitulo());
	        obj.setObservacoes(objDto.getObservacoes());
	        obj.setStatus(objDto.getStatus());
	        obj.setPrioridade(objDto.getPrioridade());
	        obj.setDataFechamentno(objDto.getDataFechamento());

	        return repository.save(obj);
		
	}
}
