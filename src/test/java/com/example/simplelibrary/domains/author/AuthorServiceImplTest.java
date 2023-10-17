package com.example.simplelibrary.domains.author;

import com.example.simplelibrary.domains.author.dto.AuthorMapper;
import com.example.simplelibrary.domains.author.dto.AuthorNameDto;
import com.example.simplelibrary.domains.author.dto.AuthorRequestDto;
import com.example.simplelibrary.domains.author.dto.AuthorResponseDto;
import com.example.simplelibrary.domains.book.dto.BookMapper;
import com.example.simplelibrary.domains.book.dto.BookResponseDto;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {
    @InjectMocks
    private AuthorServiceImpl authorService;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private AuthorMapper mapper;

    @Mock
    private BookMapper bookMapper;

    private Author author;
    private AuthorResponseDto response;
    private AuthorRequestDto request;

    @BeforeEach
    void setUp() {
        author = Author.builder()
                .id(UUID.randomUUID())
                .name("Author Name")
                .birthDate(LocalDate.of(1990, 1, 1))
                .nationality("American")
                .books(new ArrayList<>())
                .build();

        response = AuthorResponseDto.builder()
                .id(author.getId())
                .name(author.getName())
                .birthDate(author.getBirthDate())
                .nationality(author.getNationality())
                .build();
    }

    @Nested
    class QueryMethods {
        @Test
        @DisplayName("findAll() -> should return a list of AuthorResponseDto")
        void findAll_shouldReturnListOfAuthors() {
            // Arrange
            when(authorRepository.findAll()).thenReturn(List.of(author));
            when(mapper.toResponseList(List.of(author))).thenReturn(List.of(response));

            // Act
            List<AuthorResponseDto> result = authorService.findAll();

            // Assert
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(response, result.get(0));

            verify(authorRepository, times(1)).findAll();
            verify(mapper, times(1)).toResponseList(List.of(author));
        }

        @Test
        @DisplayName("findById(UUID id) -> should return AuthorResponseDto by id")
        void findById_shouldReturnAuthorById() {
            // Arrange
            when(authorRepository.findById(author.getId())).thenReturn(Optional.of(author));
            when(mapper.toResponse(author)).thenReturn(response);

            // Act
            AuthorResponseDto result = authorService.findById(author.getId());

            // Assert
            assertNotNull(result);
            assertEquals(response, result);

            verify(authorRepository, times(1)).findById(author.getId());
            verify(mapper, times(1)).toResponse(author);
        }

        @Test
        @DisplayName("findById(UUID id) -> should throw EntityNotFoundException when author not found")
        void findById_shouldThrowEntityNotFoundException() {
            // Arrange
            when(authorRepository.findById(author.getId())).thenReturn(Optional.empty());

            // Act
            Runnable result = () -> authorService.findById(author.getId());

            // Assert
            assertThrows(EntityNotFoundException.class, result::run);

            verify(authorRepository, times(1)).findById(author.getId());
            verify(mapper, times(0)).toResponse(author);
        }

        @Test
        @DisplayName("existsById(UUID id) -> should return true when author exists")
        void existsById_shouldReturnTrue() {
            // Arrange
            when(authorRepository.existsById(author.getId())).thenReturn(true);

            // Act
            boolean result = authorService.existsById(author.getId());

            // Assert
            assertTrue(result);

            verify(authorRepository, times(1)).existsById(author.getId());
        }

        @Test
        @DisplayName("existsById(UUID id) -> should return false when author does not exist")
        void existsById_shouldReturnFalse() {
            // Arrange
            when(authorRepository.existsById(author.getId())).thenReturn(false);

            // Act
            boolean result = authorService.existsById(author.getId());

            // Assert
            assertFalse(result);

            verify(authorRepository, times(1)).existsById(author.getId());
        }

        @Test
        @DisplayName("findBooksByAuthorId(UUID id) -> should return a list of BookResponseDto")
        void findBooksByAuthorId_shouldReturnListOfBooks() {
            // Arrange
            when(authorRepository.findById(author.getId())).thenReturn(Optional.of(author));
            when(bookMapper.toResponseList(author.getBooks())).thenReturn(new ArrayList<>());

            // Act
            List<BookResponseDto> result = authorService.findBooksByAuthorId(author.getId());

            // Assert
            assertNotNull(result);
            assertEquals(0, result.size());

            verify(authorRepository, times(1)).findById(author.getId());
            verify(bookMapper, times(1)).toResponseList(author.getBooks());
        }

        @Test
        @DisplayName("findBooksByAuthorId(UUID id) -> should throw EntityNotFoundException when author not found")
        void findBooksByAuthorId_shouldThrowEntityNotFoundException() {
            // Arrange
            when(authorRepository.findById(author.getId())).thenReturn(Optional.empty());

            // Act
            Runnable result = () -> authorService.findBooksByAuthorId(author.getId());

            // Assert
            assertThrows(EntityNotFoundException.class, result::run);

            verify(authorRepository, times(1)).findById(author.getId());
            verify(bookMapper, times(0)).toResponseList(author.getBooks());
        }

        @Test
        @DisplayName("findAllNames() -> should return a list of AuthorNameDto")
        void findAllNames_shouldReturnListOfAuthorNames() {
            // Arrange
            when(authorRepository.findAll()).thenReturn(List.of(author));
            when(mapper.toNameList(List.of(author))).thenReturn(List.of(new AuthorNameDto(author.getId(), author.getName())));

            // Act
            List<AuthorNameDto> result = authorService.findAllNames();

            // Assert
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(author.getId(), result.get(0).getId());
            assertEquals(author.getName(), result.get(0).getName());

            verify(authorRepository, times(1)).findAll();
            verify(mapper, times(1)).toNameList(List.of(author));
        }
    }

    @Nested
    class MutationMethods {
        @BeforeEach
        void setUp() {
            request = AuthorRequestDto.builder()
                    .name(author.getName())
                    .birthDate(author.getBirthDate())
                    .nationality(author.getNationality())
                    .build();
        }

        @Test
        @DisplayName("save(AuthorRequestDto request) -> should return AuthorResponseDto")
        void save_shouldReturnAuthor() {
            // Arrange
            when(authorRepository.save(author)).thenReturn(author);
            when(mapper.toEntity(request)).thenReturn(author);
            when(mapper.toResponse(author)).thenReturn(response);

            // Act
            AuthorResponseDto result = authorService.save(request);

            // Assert
            assertNotNull(result);
            assertEquals(response, result);

            verify(authorRepository, times(1)).save(author);
            verify(mapper, times(1)).toEntity(request);
            verify(mapper, times(1)).toResponse(author);
        }

        @Test
        @DisplayName("update(UUID id, AuthorRequestDto request) -> should return AuthorResponseDto")
        void update_shouldReturnAuthor() {
            // Arrange
            when(authorRepository.findById(author.getId())).thenReturn(Optional.of(author));
            when(authorRepository.save(author)).thenReturn(author);
            when(mapper.updateEntity(author, request)).thenReturn(author);
            when(mapper.toResponse(author)).thenReturn(response);

            // Act
            AuthorResponseDto result = authorService.update(author.getId(), request);

            // Assert
            assertNotNull(result);
            assertEquals(response, result);

            verify(authorRepository, times(1)).findById(author.getId());
            verify(authorRepository, times(1)).save(author);
            verify(mapper, times(1)).updateEntity(author, request);
            verify(mapper, times(1)).toResponse(author);
        }

        @Test
        @DisplayName("update(UUID id, AuthorRequestDto request) -> should throw EntityNotFoundException when author not found")
        void update_shouldThrowEntityNotFoundException() {
            // Arrange
            when(authorRepository.findById(author.getId())).thenReturn(Optional.empty());

            // Act
            Runnable result = () -> authorService.update(author.getId(), request);

            // Assert
            assertThrows(EntityNotFoundException.class, result::run);

            verify(authorRepository, times(1)).findById(author.getId());
            verify(authorRepository, times(0)).save(author);
            verify(mapper, times(0)).updateEntity(author, request);
            verify(mapper, times(0)).toResponse(author);
        }

        @Test
        @DisplayName("deleteById(UUID id) -> should delete author by id")
        void deleteById_shouldDeleteAuthorById() {
            // Arrange
            when(authorRepository.existsById(author.getId())).thenReturn(true);

            // Act
            authorService.deleteById(author.getId());

            // Assert
            verify(authorRepository, times(1)).existsById(author.getId());
            verify(authorRepository, times(1)).deleteById(author.getId());
        }

        @Test
        @DisplayName("deleteById(UUID id) -> should throw EntityNotFoundException when author not found")
        void deleteById_shouldThrowEntityNotFoundException() {
            // Arrange
            when(authorRepository.existsById(author.getId())).thenReturn(false);

            // Act
            Runnable result = () -> authorService.deleteById(author.getId());

            // Assert
            assertThrows(EntityNotFoundException.class, result::run);

            verify(authorRepository, times(1)).existsById(author.getId());
            verify(authorRepository, times(0)).deleteById(author.getId());
        }
    }
}