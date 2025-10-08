package com._dev_ruan.helpDesk.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
	
	@PreAuthorize("hasAnyRole('ADMIN', 'TECNICO' , 'CLIENTE')")
	@GetMapping
	public ResponseEntity<List<ChamadoDTO>> findAll() {
		List<Chamado> list = service.findAll();
		
		List<ChamadoDTO> listDTO = list.stream().map(x -> new ChamadoDTO(x)).toList();
		
		return ResponseEntity.ok().body(listDTO);
		
	}
	@PreAuthorize("hasAnyRole('ADMIN', 'TECNICO')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
		
		Chamado chamado = service.findById(id);
		
		return ResponseEntity.ok().body(new ChamadoDTO(chamado));
		
	}
	
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO objDto){
		Chamado obj = service.create(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		
		
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN', 'TECNICO')")
	@PutMapping("/{id}")
	public ResponseEntity<ChamadoDTO> update(@PathVariable Integer id , @Valid @RequestBody ChamadoDTO objDto) {
		
		Chamado newObj = service.update(id, objDto);
		return ResponseEntity.ok().body(new ChamadoDTO(newObj));
	}

}
