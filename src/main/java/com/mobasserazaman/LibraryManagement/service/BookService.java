package com.mobasserazaman.LibraryManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mobasserazaman.LibraryManagement.entity.Book;
import com.mobasserazaman.LibraryManagement.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

      public List<Book> getAvailableBooks() {
        return bookRepository.findByCountGreaterThan(0);
    }

    public Book addBook(Book book) {
        int count = book.getCount();
        count = count + 1;
        book.setCount(count);
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }
    
}
