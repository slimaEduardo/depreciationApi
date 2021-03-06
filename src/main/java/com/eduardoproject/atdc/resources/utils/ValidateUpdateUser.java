package com.eduardoproject.atdc.resources.utils;

import com.eduardoproject.atdc.dto.UserDTO;
import com.eduardoproject.atdc.entities.User;
import com.eduardoproject.atdc.repositories.UserRepository;
import com.eduardoproject.atdc.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ValidateUpdateUser implements ConstraintValidator<UserUpdate, UserDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserRepository repo;

    @Override
    public void initialize(UserUpdate ann) {
    }

    @Override
    public boolean isValid(UserDTO objDto, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        Map<String,String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();
        System.out.println("chegou aqui");
        User aux = repo.findByEmail(objDto.getEmail());
        if (aux != null && !aux.getId().equals(uriId)) {
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
