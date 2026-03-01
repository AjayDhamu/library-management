package com.example.library.contoller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class UserController {

    private final BookService bookService;
    private final BookRepository bookRepository;

    @PostMapping("/{id}/borrow")
    public ResponseEntity<?> borrow(@PathVariable String id,
            Authentication authentication) {

        bookService.borrowBook(id, authentication.getName());

        return ResponseEntity.ok("Borrowed");
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<?> returnBook(@PathVariable String id) {

        bookService.returnBook(id);

        return ResponseEntity.ok("Returned");
    }

    @GetMapping("/my")
    public List<Book> myBooks(Authentication authentication) {

        return bookRepository.findByBorrowedBy(
                authentication.getName());
    }
}
