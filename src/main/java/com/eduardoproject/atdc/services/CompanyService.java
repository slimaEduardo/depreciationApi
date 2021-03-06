package com.eduardoproject.atdc.services;

import java.util.List;

import com.eduardoproject.atdc.dto.CompanyNewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.eduardoproject.atdc.entities.Company;
import com.eduardoproject.atdc.repositories.CompanyRepository;
import com.eduardoproject.atdc.resources.exceptions.DataBaseException;
import com.eduardoproject.atdc.resources.exceptions.ResourceNotFoundException;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository repository;

	public List<Company> findAll() {
		return repository.findAll();
	}

	public Company findById(Integer id) {
		Optional<Company> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Company insert(Company obj) {
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

	public Company update(Integer id, Company obj) {
		try{
		Company entity = repository.getOne(id);
		updateData(entity,obj);
		return repository.save(entity);
		}catch(EntityNotFoundException e) {

			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Company entity, Company obj) {
		entity.setName(obj.getName());
		entity.setFantasyName(obj.getFantasyName());
		}

    public Company fromDTO(CompanyNewDTO objDto) {
		Company cpn = new Company(objDto.getName(),objDto.getFantasyName(),objDto.getCnpj());

		return cpn;
	}

}
