package com.eduardoproject.atdc.repositories;

import com.eduardoproject.atdc.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardoproject.atdc.entities.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{


    /*@Transactional(readOnly = true)
    Page<Product> findDistinctByNameIgnoreCaseContainingAndCategoriesIn(String name, List<Category> categories, Pageable pageRequest);*/
}
