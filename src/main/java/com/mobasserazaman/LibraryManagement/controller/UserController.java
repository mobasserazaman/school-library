package com.mobasserazaman.LibraryManagement.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobasserazaman.LibraryManagement.entity.User;
import com.mobasserazaman.LibraryManagement.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



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

    @PostMapping()
    public User addStudent(@RequestBody User user) { 
        return userService.addStudent(user);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id){
        userService.deleteUser(id);
    }

    
    
    
}
