package com.mobasserazaman.LibraryManagement.service;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mobasserazaman.LibraryManagement.entity.Role;
import com.mobasserazaman.LibraryManagement.entity.User;
import com.mobasserazaman.LibraryManagement.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User addStudent(User user){
        user.setRole(Role.STUDENT);
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
    
}
