package com.mobasserazaman.LibraryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobasserazaman.LibraryManagement.entity.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
    Optional<User> findByStudentId(Long studentId);
    int deleteByStudentId(Long studentId);
}
