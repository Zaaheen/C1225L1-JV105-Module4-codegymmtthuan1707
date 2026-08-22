package com.libraryapplication.service.impl;

import com.libraryapplication.entity.Book;
import com.libraryapplication.entity.BorrowTicket;
import com.libraryapplication.exception.BookOutOfStockException;
import com.libraryapplication.exception.InvalidTicketCodeException;
import com.libraryapplication.repository.IBookRepository;
import com.libraryapplication.repository.IBorrowTicketRepository;
import com.libraryapplication.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private IBorrowTicketRepository borrowTicketRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new InvalidTicketCodeException("Không tìm thấy cuốn sách có ID: " + id));
    }

    @Override
    @Transactional
    public String borrowBook(Long bookId) {
        Book book = getBookById(bookId);

        if (book.getQuantity() <= 0) {
            throw new BookOutOfStockException("Rất tiếc! Cuốn sách '" + book.getTitle() + "' đã hết số lượng mượn.");
        }

        // 1. Giảm số lượng đi 1
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        // 2. Sinh mã 5 chữ số ngẫu nhiên (Có kiểm tra trùng lặp mã trong CSDL)
        String ticketCode;
        Random random = new Random();
        do {
            ticketCode = String.format("%05d", random.nextInt(100000));
        } while (borrowTicketRepository.findByTicketCode(ticketCode).isPresent());

        // 3. Tạo thẻ mượn
        BorrowTicket ticket = new BorrowTicket();
        ticket.setTicketCode(ticketCode);
        ticket.setBook(book);
        ticket.setIsReturned(false);
        borrowTicketRepository.save(ticket);

        return ticketCode;
    }

    @Override
    @Transactional
    public void returnBook(String ticketCode) {
        BorrowTicket ticket = borrowTicketRepository.findByTicketCode(ticketCode)
                .orElseThrow(() -> new InvalidTicketCodeException("Mã mượn sách '" + ticketCode + "' không tồn tại trên hệ thống!"));

        if (Boolean.TRUE.equals(ticket.getIsReturned())) {
            throw new InvalidTicketCodeException("Mã mượn sách '" + ticketCode + "' đã được sử dụng để trả sách trước đó.");
        }

        ticket.setIsReturned(true);
        borrowTicketRepository.save(ticket);

        Book book = ticket.getBook();
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);
    }
}
