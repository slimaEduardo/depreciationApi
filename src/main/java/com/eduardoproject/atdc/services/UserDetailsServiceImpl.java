package com.eduardoproject.atdc.services;

import com.eduardoproject.atdc.entities.User;
import com.eduardoproject.atdc.repositories.UserRepository;
import com.eduardoproject.atdc.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User usr = repo.findByEmail(email);
        if(usr == null) {
            throw new UsernameNotFoundException(email);
        }


        return new UserSS(usr.getId(),usr.getEmail(),usr.getPassword(), usr.getProfile());
    }
}
