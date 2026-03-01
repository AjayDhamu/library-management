package com.example.library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Book {
    @Id
    private String id;

    private String title;
    private String author;

    private boolean borrowed;
    private String borrowedBy;
    private LocalDateTime borrowedAt;
    private LocalDateTime expiryTime;

    private BorrowPolicy borrowPolicy;
}
