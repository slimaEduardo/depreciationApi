package com.eduardoproject.atdc.repositories;

import com.eduardoproject.atdc.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    @Transactional(readOnly = true)
    User findByEmail(String email);

}
