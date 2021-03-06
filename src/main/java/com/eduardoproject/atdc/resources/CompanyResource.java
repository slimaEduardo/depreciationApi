package com.eduardoproject.atdc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.eduardoproject.atdc.dto.CompanyDTO;
import com.eduardoproject.atdc.dto.CompanyNewDTO;
import com.eduardoproject.atdc.dto.ProductDTO;
import com.eduardoproject.atdc.entities.Product;
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

import com.eduardoproject.atdc.entities.Company;
import com.eduardoproject.atdc.services.CompanyService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/companies")
public class CompanyResource {
	
	@Autowired
	private CompanyService service;

	@GetMapping
	public ResponseEntity<List> findAll(){
		List<Company> list = service.findAll();
		List<CompanyDTO> listDto = list.stream().map(obj -> new CompanyDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CompanyDTO> findById(@PathVariable Integer id){
		Company obj = service.findById(id);
		CompanyDTO objDto = new CompanyDTO(obj);
		return ResponseEntity.ok().body(objDto);
	}

	@PostMapping
	public ResponseEntity<Company> insert(@Valid @RequestBody CompanyNewDTO objDto){
		Company obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception{
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Company> update(@PathVariable Integer id,@RequestBody Company obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
