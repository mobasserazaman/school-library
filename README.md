# 📚 School Library Management System – JWTify Extension

A secure, role-based School Library Management System built with **Spring Boot**, **Spring Security**, **JWT Authentication**, **Spring Data JPA**, **PostgreSQL**, and **Docker**. This project expands the core functionality of the [`JWTify`](https://github.com/mobasserazaman/JWTify) authentication app to support book borrowing, user management, and admin control.

## ✨ Features

### 🔐 Authentication & Authorization
- JWT-based login, registration, and token refresh
- Role-based access control (`STUDENT`, `ADMIN`)
- Secure password storage with BCrypt

### 📘 Library Management
- Students can:
  - View available books
  - Borrow and return books
  - View their borrowing history
- Admins can:
  - Add and remove books
  - Add and remove students
  - View all borrow records

## 🛠 Tech Stack

- Java 17+
- Spring Boot 3
- Spring Security with JWT
- Spring Data JPA (Hibernate)
- PostgreSQL
- Docker & Docker Compose
- Lombok



