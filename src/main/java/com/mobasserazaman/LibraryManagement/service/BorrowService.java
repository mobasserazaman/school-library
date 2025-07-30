package com.mobasserazaman.LibraryManagement.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mobasserazaman.LibraryManagement.entity.Book;
import com.mobasserazaman.LibraryManagement.entity.BorrowRecord;
import com.mobasserazaman.LibraryManagement.entity.User;
import com.mobasserazaman.LibraryManagement.repository.BorrowRepository;
import com.mobasserazaman.LibraryManagement.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final BookService bookService;
    private final UserRepository userRepository;

    public BorrowRecord borrowBook(Long userId, Long bookId){
        Book book = bookService.findById(bookId).orElseThrow(()-> new RuntimeException("Book not found"));
        if(book.getCount() == 0) throw new RuntimeException("Book is not available");

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        BorrowRecord record = BorrowRecord.builder()
        .book(book)
        .user(user)
        .borrowDate(LocalDate.now())
        .dueDate(LocalDate.now().plusWeeks(2))
        .build();

        book.setCount(book.getCount()-1);
        bookService.updateBook(book);

        return borrowRepository.save(record);
    }

    public BorrowRecord returnBook(Long userId, Long bookId){
        BorrowRecord record = borrowRepository.findByBookIdAndReturnDateIsNull(bookId).orElseThrow(() -> new RuntimeException("No active borrow record found"));

        if(!record.getUser().getId().equals(userId)) throw new RuntimeException("This book was not borrowed by this user.");

        record.setReturnDate(LocalDate.now());

        Book book = record.getBook();
        book.setCount(book.getCount() + 1);
        bookService.updateBook(book);

        return borrowRepository.save(record);
    }
    
    public List<BorrowRecord> getUserHistory(Long userId){
        return borrowRepository.findByUserId(userId);
    }
}
