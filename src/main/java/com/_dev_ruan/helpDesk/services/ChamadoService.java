package com._dev_ruan.helpDesk.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._dev_ruan.helpDesk.domain.Chamado;
import com._dev_ruan.helpDesk.domain.Cliente;
import com._dev_ruan.helpDesk.domain.Tecnico;
import com._dev_ruan.helpDesk.domain.dtos.ChamadoDTO;
import com._dev_ruan.helpDesk.repositories.ChamadoRepository;
import com._dev_ruan.helpDesk.repositories.ClienteRepository;
import com._dev_ruan.helpDesk.repositories.TecnicoRepository;
import com._dev_ruan.helpDesk.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {
	
	@Autowired
	private ChamadoRepository repository;
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	
		
	public Chamado findById(Integer id) {
			
			Optional<Chamado> obj = repository.findById(id);
				
			return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: "+ id));
}
	public List<Chamado> findAll() {
		return repository.findAll();
	}
	
	public Chamado create(ChamadoDTO objDTO) {
		objDTO.setId(null);
		objDTO.setDataAbertura(new Date(System.currentTimeMillis()));
        return repository.save(fromDTO(objDTO));
    }
	
	public void delete(Integer id) {
        Chamado obj = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado! Id: " + id));
        repository.delete(obj);
    }
	
	public Chamado update(Integer id , ChamadoDTO objDto) {
		objDto.setId(id);
		Chamado oldObj = findById(id);
		oldObj = fromDTO(objDto);
		return repository.save(oldObj);
	}

	public Chamado fromDTO(ChamadoDTO obj) {
		Chamado newObj = new Chamado();
		newObj.setId(obj.getId());
		newObj.setPrioridade(obj.getPrioridade());
		newObj.setStatus(obj.getStatus());
		newObj.setTitulo(obj.getTitulo());
		newObj.setObservacoes(obj.getObservacoes());

		Tecnico tec = tecnicoRepository.findById(obj.getTecnico()).orElseThrow(() -> new ObjectNotFoundException("Técnico não encontrado! Id: " + obj.getTecnico()));
		Cliente cli = clienteRepository.findById(obj.getCliente()).orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! Id: " + obj.getCliente()));

		newObj.setTecnico(tec);
		newObj.setCliente(cli);
		return newObj;
	}
}
