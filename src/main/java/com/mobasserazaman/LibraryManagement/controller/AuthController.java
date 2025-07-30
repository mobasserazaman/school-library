package com.mobasserazaman.LibraryManagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobasserazaman.LibraryManagement.entity.Role;
import com.mobasserazaman.LibraryManagement.entity.User;
import com.mobasserazaman.LibraryManagement.repository.UserRepository;
import com.mobasserazaman.LibraryManagement.security.JwtService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody com.mobasserazaman.LibraryManagement.entity.User user) {
        System.out.println("User will now be registered");
        System.out.println("After Jackson deserialization: %s" + user);

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(Role.STUDENT);
        User savedUser = userRepository.save(user);
        System.out.println(String.format("User succesfully registered %s", savedUser));
        return ResponseEntity.ok("User registered.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        System.out.println("User will now be logged in");

        org.springframework.security.core.Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        System.out.println(String.format("Authentication successful? %s", auth.isAuthenticated()));

        UserDetails user = (UserDetails) auth.getPrincipal();
        System.out.println(String.format("User : %s", auth.getPrincipal()));

        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}

@Data
@AllArgsConstructor
class AuthResponse{
    private String token;
}

@Data
class AuthRequest{
    private String username;
    private String password;
}
