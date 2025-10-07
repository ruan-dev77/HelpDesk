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

import com._dev_ruan.helpDesk.domain.Cliente;
import com._dev_ruan.helpDesk.domain.dtos.ClienteDTO;
import com._dev_ruan.helpDesk.domain.dtos.ClienteUpdateDTO;
import com._dev_ruan.helpDesk.services.ClienteService;

@RequestMapping(value = "/Clientes")
@RestController
public class ClienteResource {
	@Autowired
	private ClienteService service;

	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {

		Cliente obj = service.findById(id);

		return ResponseEntity.ok().body(new ClienteDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll(){
		
		return ResponseEntity.ok(service.findAll());
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@PostMapping
	public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO objDTO) {
		Cliente obj = service.createCliente(objDTO);
		
		ClienteDTO objNew = new ClienteDTO(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).body(objNew);	
		
	}
	
	@PutMapping(value ="/{id}")
	public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Integer id,@RequestBody ClienteUpdateDTO objDTO) {
		Cliente obj = service.updateCliente(id, objDTO);
		
		service.updateCliente(id, objDTO);
		
		return ResponseEntity.ok(new ClienteDTO(obj));
		
		
	}
}
