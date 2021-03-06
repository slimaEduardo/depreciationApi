package com.eduardoproject.atdc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.eduardoproject.atdc.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.eduardoproject.atdc.entities.Category;
import com.eduardoproject.atdc.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	@Autowired
	private CategoryService service;

	@GetMapping
	public ResponseEntity<List<?>> findAll(){
		List<Category> list = service.findAll();
		List<CategoryDTO> listDTO = list.stream().map(obj -> new CategoryDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	} 

	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> findById(@PathVariable Integer id){
		Category obj = service.findById(id);
		CategoryDTO objDto = new CategoryDTO(obj);
		return ResponseEntity.ok().body(objDto);
	}
	
	@PostMapping
	public ResponseEntity<Category> insert(@RequestBody Category obj){
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
	public ResponseEntity<Category> update(@PathVariable Integer id,@RequestBody Category obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
