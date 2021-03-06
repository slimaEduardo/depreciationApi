package com.eduardoproject.atdc.services;

import java.util.List;

import com.eduardoproject.atdc.dto.CategoryDTO;
import com.eduardoproject.atdc.resources.exceptions.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.eduardoproject.atdc.entities.Category;
import com.eduardoproject.atdc.repositories.CategoryRepository;
import com.eduardoproject.atdc.resources.exceptions.ResourceNotFoundException;
import com.eduardoproject.atdc.resources.exceptions.ObjectNotFoundException;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	public List<Category> findAll() {
		return repository.findAll();
	}

	public Category findById(Integer id) {
		Optional<Category> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
	}
	
	public Category insert(Category obj) {
		return repository.save(obj);
	}

	public void delete(Integer id){
		findById(id);
		try {
			repository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria com produtos cadstrados.");
		}
	}

	public Category update(Integer id, Category obj) {
		try {
		Category entity = repository.getOne(id);
		updateData(entity,obj);
		return repository.save(entity);
		}catch(EntityNotFoundException e) {

			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Category entity, Category obj) {
		entity.setName(obj.getName());
		entity.setDepretiationRate(obj.getDepretiationRate());
		entity.setLifeCycle(obj.getLifeCycle());


	}

	public Category fromDTO(CategoryDTO objDTO) {
		return new Category(objDTO.getId(),objDTO.getName(),objDTO.getDepretiationRate(), objDTO.getLifeCycle());
	}
}
