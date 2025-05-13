package com.rookies3.myspringbootlab.controller;

import com.rookies3.myspringbootlab.controller.dto.BookDTO;
import com.rookies3.myspringbootlab.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // 모든 책 조회
    @GetMapping
    public ResponseEntity<List<BookDTO.Response>> getAllBooks() {
        List<BookDTO.Response> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    // ID로 책 조회
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO.Response> getBookById(@PathVariable Long id) {
        BookDTO.Response book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    // ISBN으로 책 조회
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookDTO.Response> getBookByIsbn(@PathVariable String isbn) {
        BookDTO.Response book = bookService.getBookByIsbn(isbn);
        return ResponseEntity.ok(book);
    }

    // 저자로 책 검색
    @GetMapping("/search")
    public ResponseEntity<List<BookDTO.Response>> searchBooksByAuthor(@RequestParam String author) {
        List<BookDTO.Response> books = bookService.searchBooksByAuthor(author);
        return ResponseEntity.ok(books);
    }

    // 책 생성
    @PostMapping
    public ResponseEntity<BookDTO.Response> createBook(@RequestBody BookDTO.Request request) {
        BookDTO.Response createdBook = bookService.createBook(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdBook.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdBook);
    }

    // 책 수정
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO.Response> updateBook(
            @PathVariable Long id,
            @RequestBody BookDTO.Request request) {
        BookDTO.Response updatedBook = bookService.updateBook(id, request);
        return ResponseEntity.ok(updatedBook);
    }

    // 책 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    // ISBN 중복 검사
    @GetMapping("/exists/isbn/{isbn}")
    public ResponseEntity<Boolean> isIsbnExists(@PathVariable String isbn) {
        boolean exists = bookService.isIsbnExists(isbn);
        return ResponseEntity.ok(exists);
    }
}