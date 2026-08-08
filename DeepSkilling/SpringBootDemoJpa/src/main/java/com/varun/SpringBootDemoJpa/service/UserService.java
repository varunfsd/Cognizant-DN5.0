package com.varun.SpringBootDemoJpa.service;

import com.varun.SpringBootDemoJpa.exception.UserAlreadyExistsException;
import com.varun.SpringBootDemoJpa.model.User;
import com.varun.SpringBootDemoJpa.repository.StudentRepository;
import com.varun.SpringBootDemoJpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder encoder;
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository=userRepository;
    }
    public User saveUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException("User already exists.");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
