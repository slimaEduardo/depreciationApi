package com.eduardoproject.atdc.resources.utils;

import com.eduardoproject.atdc.dto.CompanyNewDTO;
import com.eduardoproject.atdc.entities.Company;
import com.eduardoproject.atdc.repositories.CompanyRepository;
import com.eduardoproject.atdc.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ValidateInsertCompany implements ConstraintValidator<CompanyInsert, CompanyNewDTO> {

    @Autowired
    private CompanyRepository repo;

    @Override
    public void initialize(CompanyInsert ann) {
    }

    @Override
    public boolean isValid(CompanyNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

       if (!DataValidator.isValidCNPJ(objDto.getCnpj())) {
            list.add(new FieldMessage("Cnpj", "CNPJ inválido"));
        }
        Company aux = repo.findByCnpj(objDto.getCnpj()) ;

        if (aux != null) {
            list.add(new FieldMessage("Cnpj", "CNPJ já existente"));
        }

       for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
