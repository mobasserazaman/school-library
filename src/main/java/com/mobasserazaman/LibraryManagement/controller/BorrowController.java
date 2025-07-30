package com.mobasserazaman.LibraryManagement.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobasserazaman.LibraryManagement.entity.BorrowRecord;
import com.mobasserazaman.LibraryManagement.repository.UserRepository;
import com.mobasserazaman.LibraryManagement.security.JwtService;
import com.mobasserazaman.LibraryManagement.service.BorrowService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/borrow")
@RequiredArgsConstructor
@PreAuthorize("hasRole('STUDENT')")
public class BorrowController {

    private final BorrowService borrowService;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    private Long getCurrentUserId(HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtService.extractUsername(token);
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found")).getId();
    }

    @PostMapping("/{bookId}")
    public BorrowRecord borrowBook(@PathVariable Long bookId, HttpServletRequest request){
        Long userId = getCurrentUserId(request);
        return borrowService.borrowBook(userId, bookId);
    }
    
    @PostMapping("/return/{bookId}")
    public BorrowRecord returnBook(@PathVariable Long bookId, HttpServletRequest request){
        Long userId = getCurrentUserId(request);
        return borrowService.returnBook(userId, bookId);
    }

    @GetMapping("/history")
    public List<BorrowRecord> getUserBorrowHistory(HttpServletRequest request){
        Long userId = getCurrentUserId(request);
        return borrowService.getUserHistory(userId);

    }
}
