package com.example.simplelibrary.domains.book.dto;

import com.example.simplelibrary.domains.author.Author;
import com.example.simplelibrary.domains.book.Book;
import com.example.simplelibrary.domains.book.Genre;
import com.example.simplelibrary.domains.publisher.Publisher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BookMapperTest {
    private static BookMapper mapper;
    private Book book;
    private BookRequestDto bookRequestDto;

    @BeforeAll
    static void beforeAll() {
        mapper = new BookMapper();
    }

    @BeforeEach
    void setUp() {
        book = Book.builder()
                .id(UUID.randomUUID())
                .isbn("1234567890123")
                .title("Book Title")
                .genre(Genre.SCIENCE)
                .publicationYear(2021)
                .quantity(1)
                .author(Author.builder().id(UUID.randomUUID()).build())
                .publisher(Publisher.builder().id(UUID.randomUUID()).build())
                .build();

        bookRequestDto = BookRequestDto.builder()
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .genre(book.getGenre())
                .publicationYear(book.getPublicationYear())
                .quantity(book.getQuantity())
                .author(book.getAuthor().getId())
                .publisher(book.getPublisher().getId())
                .build();
    }

    @Test
    @DisplayName("toResponse -> should map Book to BookResponseDto")
    void toResponse_shouldMapBookToBookResponseDto() {
        // Act
        BookResponseDto result = mapper.toResponse(book);

        // Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("toEntity -> should map BookRequestDto to Book")
    void toEntity_shouldMapBookRequestDtoToBook() {
        // Act
        Book result = mapper.toEntity(bookRequestDto);

        // Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("toResponseList -> should map List<Book> to List<BookResponseDto>")
    void toResponseList_shouldMapListOfBookToListOfBookResponseDto() {
        // Act
        List<BookResponseDto> result = mapper.toResponseList(List.of(book));

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("updateEntity -> should map BookRequestDto to Book")
    void updateEntity_shouldMapBookRequestDtoToBook() {
        // Act
        Book result = mapper.updateEntity(book, bookRequestDto);

        // Assert
        assertNotNull(result);
    }
}