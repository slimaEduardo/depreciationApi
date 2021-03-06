package com.eduardoproject.atdc.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.eduardoproject.atdc.entities.Category;
import com.eduardoproject.atdc.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.eduardoproject.atdc.entities.Product;
import com.eduardoproject.atdc.repositories.ProductRepository;
import com.eduardoproject.atdc.resources.exceptions.DataBaseException;
import com.eduardoproject.atdc.resources.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private  CompanyService companyService;

	public List<Product> findAll(){
		return repository.findAll();
	}

	public Product findById(Integer id) {
		Optional<Product> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Product insert(Product obj) {
		//Category aux = categoryService.findById(obj.getCategoryId());
		obj.setCategory(categoryService.findById(obj.getCategoryId()));

		obj.setCompany(companyService.findById(obj.getCompanyId()));
		System.out.println("Chegou aqui:" + obj.getCategory().getName() + obj.getCompany().getName());
		return repository.save(obj);
	}

	public void delete(Integer id){
		try {
		repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}

	public Product update(Integer id, Product obj) {
		try {
		Product entity = repository.getOne(id);
		updateData(entity,obj);
		return repository.save(entity);
		}catch(EntityNotFoundException e) {

			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Product entity, Product obj) {
		entity.setName(obj.getName());
		entity.setDescription(obj.getDescription());
		entity.setCompany(obj.getCompany());
		entity.setInvoicePath(obj.getInvoicePath());
		}

	/*public Page<Product> search(String name, List<Integer> ids,Integer page, Integer linesPerPage,String orderBy,String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction),orderBy);
		List<Category> categories = categoryRepository.findAllById(ids);
		return repository.findDistinctByNameIgnoreCaseContainingAndCategoriesIn(name,categories,pageRequest);
	}*/
}
