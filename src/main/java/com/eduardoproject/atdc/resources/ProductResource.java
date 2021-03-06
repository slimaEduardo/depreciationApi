package com.eduardoproject.atdc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.eduardoproject.atdc.resources.utils.URL;
import com.eduardoproject.atdc.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.eduardoproject.atdc.entities.Product;
import com.eduardoproject.atdc.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	@Autowired
	private ProductService service;
	@GetMapping
	public ResponseEntity<List> findAll(){
		List<Product> list = service.findAll();
		List<ProductDTO> listDto = list.stream().map(obj -> new ProductDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	} 

	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable Integer id){
		Product obj = service.findById(id);
		ProductDTO objDto = new ProductDTO(obj);
		return ResponseEntity.ok().body(objDto);
	}
	
	@PostMapping
	public ResponseEntity<Product> insert(@RequestBody Product obj){
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
	public ResponseEntity<Product> update(@PathVariable Integer id,@RequestBody Product obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}


	/*@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<?>> findPage(@RequestParam(value="name", defaultValue = "") String name,
											@RequestParam(value="categories", defaultValue = "") String categories ,
											@RequestParam(value="page", defaultValue = "0") Integer page,
											@RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage,
											@RequestParam(value="orderBy", defaultValue = "name")String orderBy,
											@RequestParam(value="direction", defaultValue = "ASC")String direction) {
		String decodedName = URL.decodeString(name);
		List<Integer> ids = URL.decodeIntList(categories);
		Page<Product> pageP = service.search(decodedName, ids, page,linesPerPage,orderBy,direction);
		Page<ProductDTO> pageDTO = pageP.map(obj -> new ProductDTO(obj));
		return ResponseEntity.ok().body(pageDTO);
	}*/
}
