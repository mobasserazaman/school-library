package com.mobasserazaman.LibraryManagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobasserazaman.LibraryManagement.entity.User;
import com.mobasserazaman.LibraryManagement.service.UserService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/admin/students")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public List<User> getAllStudents() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getStudent(@PathVariable Long id) {
        return userService.getUser(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping()
    public ResponseEntity<?> addStudent(@Valid @RequestBody User user) { 
        userService.addStudent(user);
        return ResponseEntity.ok("User registered") ;
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    
    
    
}
