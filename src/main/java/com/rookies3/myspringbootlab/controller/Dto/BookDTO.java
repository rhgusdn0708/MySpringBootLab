package com.rookies3.myspringbootlab.controller.Dto;

import com.rookies3.myspringbootlab.entity.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class BookDTO {

    // 📌 Book 생성 요청용 DTO
    public static class BookCreateRequest {
        @NotBlank
        private String title;
        @NotBlank
        private String author;
        @NotNull
        private Integer price;
        @NotNull
        private LocalDate publishDate;

        // 생성자
        public BookCreateRequest(String title, String author, Integer price, LocalDate publishDate) {
            this.title = title;
            this.author = author;
            this.price = price;
            this.publishDate = publishDate;
        }

        // 기본 생성자 (직렬화/역직렬화를 위해 필요할 수 있음)
        public BookCreateRequest() {}

        // Getter
        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public Integer getPrice() { return price; }
        public LocalDate getPublishDate() { return publishDate; }

        // Setter
        public void setTitle(String title) { this.title = title; }
        public void setAuthor(String author) { this.author = author; }
        public void setPrice(Integer price) { this.price = price; }
        public void setPublishDate(LocalDate publishDate) { this.publishDate = publishDate; }
    }

    // 📌 Book 수정 요청용 DTO
    public static class BookUpdateRequest {
        private String title;
        private String author;
        private Integer price;
        private LocalDate publishDate;

        public BookUpdateRequest() {}

        // Getter
        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public Integer getPrice() { return price; }
        public LocalDate getPublishDate() { return publishDate; }

        // Setter
        public void setTitle(String title) { this.title = title; }
        public void setAuthor(String author) { this.author = author; }
        public void setPrice(Integer price) { this.price = price; }
        public void setPublishDate(LocalDate publishDate) { this.publishDate = publishDate; }
    }

    // 📌 Book 응답용 DTO
    public static class BookResponse {
        private Long id;
        private String title;
        private String author;
        private Integer price;
        private LocalDate publishDate;

        // 생성자
        public BookResponse(Book book) {
            this.id = book.getId();
            this.title = book.getTitle();
            this.author = book.getAuthor();
            this.price = book.getPrice();
            this.publishDate = book.getPublishDate();
        }

        // Getter
        public Long getId() { return id; }
        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public Integer getPrice() { return price; }
        public LocalDate getPublishDate() { return publishDate; }
    }
}
