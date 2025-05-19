package com.rookies3.myspringbootlab.service;

import com.rookies3.myspringbootlab.controller.dto.PublisherDTO;
import com.rookies3.myspringbootlab.entity.Publisher;
import com.rookies3.myspringbootlab.exception.*;
import com.rookies3.myspringbootlab.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublisherService {
    private final PublisherRepository publisherRepository;

    // 모든 출판사 조회
    public List<PublisherDTO.Response> getAllPublishers() {
        return publisherRepository.findAll().stream()
            .map(p -> PublisherDTO.Response.builder()
                .id(p.getId())
                .name(p.getName())
                .establishedDate(p.getEstablishedDate())
                .address(p.getAddress())
                .bookCount(p.getBooks().size())
                .build())
            .collect(Collectors.toList());
    }

    // ID로 출판사 조회 (Fetch Join)
    public PublisherDTO.Response getPublisherById(Long id) {
        Publisher publisher = publisherRepository.findByIdWithBooks(id)
            .orElseThrow(() -> new ResourceNotFoundException("출판사", "id", id));
        return convertToResponse(publisher);
    }

    // DTO 변환 메서드
    private PublisherDTO.Response convertToResponse(Publisher publisher) {
        return PublisherDTO.Response.builder()
            .id(publisher.getId())
            .name(publisher.getName())
            .establishedDate(publisher.getEstablishedDate())
            .address(publisher.getAddress())
            .bookCount(publisher.getBooks().size())
            .build();
    }

    // 출판사 생성
    @Transactional
    public PublisherDTO.Response createPublisher(PublisherDTO.Request request) {
        if (publisherRepository.existsByName(request.getName())) {
            throw new DuplicateResourceException("출판사", "이름", request.getName());
        }
        Publisher publisher = Publisher.builder()
            .name(request.getName())
            .establishedDate(request.getEstablishedDate())
            .address(request.getAddress())
            .build();
        publisherRepository.save(publisher);
        return convertToResponse(publisher);
    }

    // 출판사 삭제 (도서 존재 시 실패)
    @Transactional
    public void deletePublisher(Long id) {
        Publisher publisher = publisherRepository.findByIdWithBooks(id)
            .orElseThrow(() -> new ResourceNotFoundException("출판사", "id", id));
        if (!publisher.getBooks().isEmpty()) {
            throw new BusinessException("출판사에 도서가 존재하여 삭제할 수 없습니다.");
        }
        publisherRepository.delete(publisher);
    }
}