package com.rookies3.myspringbootlab.controller.Dto;

import com.rookies3.myspringbootlab.entity.Book;

import java.time.LocalDate;

public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private Integer price;
    private LocalDate publishDate;

    public BookResponse(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.price = book.getPrice();
        this.publishDate = book.getPublishDate();
    }

    // Getters only
}
