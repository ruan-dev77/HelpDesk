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

import com._dev_ruan.helpDesk.domain.Tecnico;
import com._dev_ruan.helpDesk.domain.dtos.TecnicoDTO;

import com._dev_ruan.helpDesk.services.TecnicoService;


@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

	
	@Autowired
	private TecnicoService service;

	
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {

		Tecnico obj = service.findById(id);

		return ResponseEntity.ok().body(new TecnicoDTO(obj));
	}
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<TecnicoDTO>> findAll(){
		
		return ResponseEntity.ok(service.findAll());
	}
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<TecnicoDTO> createTecnico(@RequestBody TecnicoDTO objDTO) {
		Tecnico obj = service.create(objDTO);
		
		TecnicoDTO objNew = new TecnicoDTO(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).body(objNew);	
		
	}
	
	@PutMapping(value ="/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<TecnicoDTO> updateTecnico(@PathVariable Integer id,@RequestBody TecnicoDTO objDTO) {
		Tecnico obj = service.update(id, objDTO);
		
		service.update(id, objDTO);
		
		return ResponseEntity.ok().body(new TecnicoDTO(obj));
		
		
	}
}
