package com.eduardoproject.atdc.services;

import com.eduardoproject.atdc.dto.UserDTO;
import com.eduardoproject.atdc.dto.UserNewDTO;
import com.eduardoproject.atdc.entities.User;
import com.eduardoproject.atdc.entities.enums.TypeUser;
import com.eduardoproject.atdc.entities.enums.UserProfile;
import com.eduardoproject.atdc.repositories.UserRepository;
import com.eduardoproject.atdc.resources.exceptions.AuthorizationException;
import com.eduardoproject.atdc.resources.exceptions.DataBaseException;
import com.eduardoproject.atdc.resources.exceptions.ResourceNotFoundException;
import com.eduardoproject.atdc.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

    public List<User> findAll(){
		return repository.findAll();
	}

	public User findById(Integer id) {
		UserSS user = UserAuthService.authenticated();
		if(user==null || !user.hasRole(UserProfile.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado!");
		}
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User insert(User obj) {
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

	public User update(Integer id, User obj) {
		try {
		User entity = repository.getOne(id);
		updateData(entity,obj);
		return repository.save(entity);
		}catch(EntityNotFoundException e) {

			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());

		}

	public User fromDTO(UserNewDTO objDto) {
		User usr = new User(objDto.getName(),objDto.getEmail(),passwordEncoder.encode(objDto.getPassword()), objDto.getPhone1(), objDto.getPhone2());

		return usr;
	}

	public User fromDTO(UserDTO objDto) {
		User usr = new User(objDto.getName(),objDto.getEmail(),null, objDto.getPhone1(), objDto.getPhone2());

		return usr;
	}

}
