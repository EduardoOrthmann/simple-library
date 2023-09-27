package com.example.simplelibrary.domains.publisher;

import com.example.simplelibrary.domains.book.dto.BookResponseDto;
import com.example.simplelibrary.interfaces.Crud;
import com.example.simplelibrary.domains.publisher.dto.PublisherRequestDto;
import com.example.simplelibrary.domains.publisher.dto.PublisherResponseDto;

import java.util.List;
import java.util.UUID;

public interface PublisherService extends Crud<PublisherRequestDto, PublisherResponseDto, UUID> {
    List<BookResponseDto> findBooksByPublisherId(UUID id);
    boolean existsById(UUID id);
}
