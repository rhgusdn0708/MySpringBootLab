package com.rookies3.myspringbootlab.runner;

import com.rookies3.myspringbootlab.entity.*;
import com.rookies3.myspringbootlab.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class BookDataInitRunner implements CommandLineRunner {
    private final PublisherRepository publisherRepository;
    private final BookRepository bookRepository;

    @Override
    public void run(String... args) {
        // 샘플 출판사 생성
        Publisher publisher = Publisher.builder()
            .name("한빛미디어")
            .establishedDate(LocalDate.of(1993, 1, 1))
            .address("서울시 강남구")
            .build();
        publisherRepository.save(publisher);

        // 샘플 도서 생성
        Book book = Book.builder()
            .title("자바 ORM 표준 JPA 프로그래밍")
            .author("김영한")
            .isbn("9788960777330")
            .price(40000)
            .publishDate(LocalDate.of(2020, 1, 1))
            .publisher(publisher)
            .build();
        bookRepository.save(book);
    }
}