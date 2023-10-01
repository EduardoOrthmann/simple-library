package com.example.simplelibrary.domains.author.dto;

import com.example.simplelibrary.domains.author.Author;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AuthorMapperTest {
    private static AuthorMapper mapper;
    private Author author;
    private AuthorRequestDto authorRequestDto;

    @BeforeAll
    static void beforeAll() {
        mapper = new AuthorMapper();
    }

    @BeforeEach
    void setUp() {
        author = Author.builder()
                .id(UUID.randomUUID())
                .name("Author Name")
                .birthDate(LocalDate.of(1990, 1, 1))
                .nationality("American")
                .books(List.of())
                .build();

        authorRequestDto = AuthorRequestDto.builder()
                .name(author.getName())
                .birthDate(author.getBirthDate())
                .nationality(author.getNationality())
                .build();
    }

    @Test
    @DisplayName("toResponse -> should map Author to AuthorResponseDto")
    void toResponse_shouldMapAuthorToAuthorResponseDto() {
        // Act
        AuthorResponseDto result = mapper.toResponse(author);

        // Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("toEntity -> should map AuthorRequestDto to Author")
    void toEntity_shouldMapAuthorRequestDtoToAuthor() {
        // Act
        Author result = mapper.toEntity(authorRequestDto);

        // Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("toResponseList -> should map list of Author to list of AuthorResponseDto")
    void toResponseList_shouldMapListOfAuthorToListOfAuthorResponseDto() {
        // Act
        List<AuthorResponseDto> result = mapper.toResponseList(List.of(author));

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("updateEntity -> should update Author with AuthorRequestDto")
    void updateEntity_shouldUpdateAuthorWithAuthorRequestDto() {
        // Act
        Author result = mapper.updateEntity(author, authorRequestDto);

        // Assert
        assertNotNull(result);
    }
}