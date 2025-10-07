package com._dev_ruan.helpDesk.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com._dev_ruan.helpDesk.domain.Chamado;
import com._dev_ruan.helpDesk.domain.dtos.ChamadoDTO;
import com._dev_ruan.helpDesk.services.ChamadoService;

import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {

	@Autowired 
	private ChamadoService service;
	
	@GetMapping
	public ResponseEntity<List<ChamadoDTO>> findAll() {
		List<Chamado> list = service.findAll();
		
		List<ChamadoDTO> listDTO = list.stream().map(x -> new ChamadoDTO(x)).toList();
		
		return ResponseEntity.ok().body(listDTO);
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
		
		Chamado chamado = service.findById(id);
		
		return ResponseEntity.ok().body(new ChamadoDTO(chamado));
		
	}
	
	
	@PostMapping
	public ResponseEntity<ChamadoDTO> createChamado(@Valid @RequestBody ChamadoDTO objDto){
		Chamado objChamado = Chamado.fromDTO(objDto);
		service.createChamado(objChamado);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objChamado.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		
		
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<ChamadoDTO> Update(@PathVariable Integer id ,@RequestBody ChamadoDTO objDto) {
		
		Chamado obj = Chamado.fromDTO(objDto);
		service.update(id, objDto);
		return ResponseEntity.ok(new ChamadoDTO(obj));
	}
	
	
	

}
