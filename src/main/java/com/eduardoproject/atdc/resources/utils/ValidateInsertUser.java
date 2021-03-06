package com.eduardoproject.atdc.resources.utils;

import com.eduardoproject.atdc.dto.UserNewDTO;
import com.eduardoproject.atdc.entities.User;
import com.eduardoproject.atdc.repositories.UserRepository;
import com.eduardoproject.atdc.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ValidateInsertUser implements ConstraintValidator<UserInsert, UserNewDTO> {

    @Autowired
    private UserRepository repo;

    @Override
    public void initialize(UserInsert ann) {
    }

    @Override
    public boolean isValid(UserNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

         User aux = repo.findByEmail(objDto.getEmail());


         if (aux != null) {
         list.add(new FieldMessage("email", "Email já existente"));
         }


        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
