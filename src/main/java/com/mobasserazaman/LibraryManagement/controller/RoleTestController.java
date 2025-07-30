package com.mobasserazaman.LibraryManagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleTestController {

    @GetMapping("/user/data")
    public String userEndpoint() {
        return "Hello, USER";
    }

    @GetMapping("/admin/data")
    public String adminEndpoint() {
        return "Hello, ADMIN";
    }
}
