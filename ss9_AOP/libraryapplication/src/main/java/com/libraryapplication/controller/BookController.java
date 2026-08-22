package com.libraryapplication.controller;

import com.libraryapplication.entity.Book;
import com.libraryapplication.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/library")
public class BookController {

    @Autowired
    private IBookService bookService;

    // 1. Màn hình danh sách sách
    @GetMapping({"", "/books"})
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books";
    }

    // 2. Màn hình chi tiết sách (Trực tiếp trong books.html)
    @GetMapping("/detail/{id}")
    public String viewDetail(@PathVariable Long id, Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("selectedBook", bookService.getBookById(id));
        return "books";
    }

    // 3. Thực hiện mượn sách & hiển thị mã thành công (Trực tiếp trong books.html)
    @PostMapping("/borrow/{id}")
    public String processBorrow(@PathVariable Long id, Model model) {
        String ticketCode = bookService.borrowBook(id);
        Book book = bookService.getBookById(id);
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("borrowTicketCode", ticketCode);
        model.addAttribute("borrowedBook", book);
        return "books";
    }

    // 4. Form trả sách
    @GetMapping("/return")
    public String showReturnForm() {
        return "return-book";
    }

    // 5. Xử lý trả sách (Hiển thị kết quả trả trực tiếp trên return-book.html)
    @PostMapping("/return")
    public String processReturn(@RequestParam("ticketCode") String ticketCode, Model model) {
        bookService.returnBook(ticketCode);
        model.addAttribute("returnedTicketCode", ticketCode);
        return "return-book";
    }
}
