package com.eduardoproject.atdc.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardoproject.atdc.entities.Company;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Transactional(readOnly = true)
    Company findByCnpj(String cnpj);

}
