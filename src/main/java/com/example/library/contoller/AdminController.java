package com.example.library.contoller;

import org.springframework.web.bind.annotation.*;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final BookRepository bookRepository;

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {

        book.setBorrowed(false);

        return bookRepository.save(book);
    }
}
