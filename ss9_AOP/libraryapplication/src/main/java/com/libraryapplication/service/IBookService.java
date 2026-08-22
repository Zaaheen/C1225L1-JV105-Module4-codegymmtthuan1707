package com.libraryapplication.service;

import com.libraryapplication.entity.Book;

import java.util.List;

public interface IBookService {
    List<Book> getAllBooks();
    Book getBookById(Long id);
    String borrowBook(Long bookId);
    void returnBook(String ticketCode);
}
