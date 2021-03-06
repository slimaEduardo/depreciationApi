package com.eduardoproject.atdc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardoproject.atdc.entities.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
