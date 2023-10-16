package com.example.simplelibrary.domains.book.dto;

import com.example.simplelibrary.domains.author.Author;
import com.example.simplelibrary.domains.author.dto.AuthorNameDto;
import com.example.simplelibrary.domains.book.Book;
import com.example.simplelibrary.domains.publisher.Publisher;
import com.example.simplelibrary.domains.publisher.dto.PublisherNameDto;
import com.example.simplelibrary.interfaces.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookMapper implements Mapper<Book, BookRequestDto, BookResponseDto> {
    @Override
    public BookResponseDto toResponse(Book book) {
        return BookResponseDto.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .genre(book.getGenre())
                .publicationYear(book.getPublicationYear())
                .quantity(book.getQuantity())
                .author(AuthorNameDto.builder()
                        .id(book.getAuthor().getId())
                        .name(book.getAuthor().getName())
                        .build()
                )
                .publisher(PublisherNameDto.builder()
                        .id(book.getPublisher().getId())
                        .name(book.getPublisher().getName())
                        .build()
                )
                .build();
    }

    @Override
    public List<BookResponseDto> toResponseList(Collection<Book> books) {
        return books.stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public Book toEntity(BookRequestDto request) {
        return Book.builder()
                .isbn(request.getIsbn())
                .title(request.getTitle())
                .genre(request.getGenre())
                .publicationYear(request.getPublicationYear())
                .quantity(request.getQuantity())
                .author(Author.builder().id(request.getAuthor()).build())
                .publisher(Publisher.builder().id(request.getPublisher()).build())
                .build();
    }

    @Override
    public Book updateEntity(Book book, BookRequestDto request) {
        book.setIsbn(request.getIsbn());
        book.setTitle(request.getTitle());
        book.setGenre(request.getGenre());
        book.setPublicationYear(request.getPublicationYear());
        book.setQuantity(request.getQuantity());
        book.setAuthor(Author.builder().id(request.getAuthor()).build());
        book.setPublisher(Publisher.builder().id(request.getPublisher()).build());

        return book;
    }
}
