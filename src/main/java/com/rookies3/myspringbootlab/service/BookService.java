package com.rookies3.myspringbootlab.service;

import com.rookies3.myspringbootlab.controller.Dto.BookDTO.BookCreateRequest;
import com.rookies3.myspringbootlab.controller.Dto.BookDTO.BookResponse;
import com.rookies3.myspringbootlab.controller.Dto.BookDTO.BookUpdateRequest;
import com.rookies3.myspringbootlab.entity.Book;
import com.rookies3.myspringbootlab.repository.BookRepository;
import com.rookies3.myspringbootlab.exception.BusinessException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public BookResponse createBook(BookCreateRequest request) {
        Book book = new Book(request.getTitle(), request.getAuthor(), request.getPrice(), request.getPublishDate());
        bookRepository.save(book);
        return new BookResponse(book);
    }

    @Transactional
    public BookResponse updateBook(Long id, BookUpdateRequest request) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BusinessException("Book not found"));
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPrice(request.getPrice());
        book.setPublishDate(request.getPublishDate());
        return new BookResponse(book);
    }

    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BusinessException("Book not found"));
        return new BookResponse(book);
    }
}
