package com.mobasserazaman.LibraryManagement.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username cannot be empty") 
    @Column(nullable = false, unique = true)
    private String username;
    @NotBlank(message = "Password cannot be empty") 
    @Column(nullable = false)
    private String password;
    @NotNull(message = "Student ID cannot be null")
    @Column(nullable = false, unique = true)
    private Long studentId;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    @JsonManagedReference("user")
    private List<BorrowRecord> borrowRecords = new ArrayList<>();

}
