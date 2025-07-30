package com.mobasserazaman.LibraryManagement.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobasserazaman.LibraryManagement.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
    List<Book> findByCountGreaterThan(int count);
}
