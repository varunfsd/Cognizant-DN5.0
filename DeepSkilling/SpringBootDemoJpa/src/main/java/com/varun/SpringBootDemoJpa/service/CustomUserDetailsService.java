package com.varun.SpringBootDemoJpa.service;

import com.varun.SpringBootDemoJpa.model.User;
import com.varun.SpringBootDemoJpa.repository.UserRepository;
import com.varun.SpringBootDemoJpa.security.UserPrincipal;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(@NonNull String username)
            throws UsernameNotFoundException {

        User user = repository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User Not Found"));

        return new UserPrincipal(user);
    }
}