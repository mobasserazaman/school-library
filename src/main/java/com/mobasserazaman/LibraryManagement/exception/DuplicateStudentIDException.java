package com.mobasserazaman.LibraryManagement.exception;

public class DuplicateStudentIDException extends RuntimeException{
    public DuplicateStudentIDException(String message){
        super(message);
    }
}
