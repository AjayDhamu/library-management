package com.example.library.scheduler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookScheduler {

    private final BookRepository bookRepository;

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void autoReturn() {

        LocalDateTime now = LocalDateTime.now();

        List<Book> books = bookRepository.findByBorrowedTrue();

        for (Book book : books) {

            if (book.getExpiryTime() != null &&
                    book.getExpiryTime().isBefore(now)) {

                book.setBorrowed(false);
                book.setBorrowedBy(null);
                book.setBorrowedAt(null);
                book.setExpiryTime(null);

                bookRepository.save(book);
            }
        }
    }
}
