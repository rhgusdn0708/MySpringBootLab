package com.rookies3.myspringbootlab.controller.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class BookCreateRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @NotNull
    private Integer price;
    @NotNull
    private LocalDate publishDate;



    // Getters and Setters
}
