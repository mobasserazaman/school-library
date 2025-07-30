package com.mobasserazaman.LibraryManagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobasserazaman.LibraryManagement.entity.BorrowRecord;

public interface BorrowRepository extends JpaRepository<BorrowRecord, Long>{
    List<BorrowRecord> findByUserId(Long userId);
    Optional<BorrowRecord> findByBookIdAndReturnDateIsNull(Long bookId);
    
}
