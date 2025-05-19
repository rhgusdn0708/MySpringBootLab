package com.rookies3.myspringbootlab.controller.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

public class PublisherDTO {
    @Data @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class Request {
        @NotBlank(message = "출판사명은 필수입니다.")
        private String name;
        private LocalDate establishedDate;
        private String address;
    }

    @Data @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class Response {
        private Long id;
        private String name;
        private LocalDate establishedDate;
        private String address;
        private int bookCount;
    }
}