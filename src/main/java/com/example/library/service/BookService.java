package com.example.library.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.library.model.Book;
import com.example.library.model.BorrowPolicy;
import com.example.library.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public void borrowBook(String bookId, String username) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.isBorrowed()) {
            throw new RuntimeException("Already borrowed");
        }

        book.setBorrowed(true);
        book.setBorrowedBy(username);
        book.setBorrowedAt(LocalDateTime.now());

        if (BorrowPolicy.END_OF_DAY.equals(book.getBorrowPolicy())) {
            book.setExpiryTime(LocalDate.now().atTime(22, 0));
        }

        bookRepository.save(book);
    }

    @Transactional
    public void returnBook(String bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (!book.isBorrowed()) {
            throw new RuntimeException("Book is not currently borrowed");
        }

        book.setBorrowed(false);
        book.setBorrowedBy(null);
        book.setBorrowedAt(null);
        book.setExpiryTime(null);

        bookRepository.save(book);
    }
}
