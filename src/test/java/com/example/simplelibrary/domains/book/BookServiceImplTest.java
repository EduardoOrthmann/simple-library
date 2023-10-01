package com.example.simplelibrary.domains.book;

import com.example.simplelibrary.domains.author.Author;
import com.example.simplelibrary.domains.author.AuthorService;
import com.example.simplelibrary.domains.book.dto.BookMapper;
import com.example.simplelibrary.domains.book.dto.BookRequestDto;
import com.example.simplelibrary.domains.book.dto.BookResponseDto;
import com.example.simplelibrary.domains.publisher.Publisher;
import com.example.simplelibrary.domains.publisher.PublisherService;
import com.example.simplelibrary.exceptions.IsbnAlreadyExistsException;
import com.example.simplelibrary.exceptions.PublicationYearTooNew;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorService authorService;

    @Mock
    private PublisherService publisherService;

    @Mock
    private BookMapper mapper;

    private Book book;
    private BookResponseDto responseSample;
    private BookRequestDto requestSample;

    @BeforeEach
    void setUp() {
        book = Book.builder()
                .id(UUID.randomUUID())
                .isbn("9783161484100")
                .title("Clean Code")
                .genre(Genre.SCIENCE)
                .publicationYear(2008)
                .quantity(464)
                .author(new Author())
                .publisher(new Publisher())
                .build();

        responseSample = BookResponseDto.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .genre(book.getGenre())
                .publicationYear(book.getPublicationYear())
                .quantity(book.getQuantity())
                .authorId(book.getAuthor().getId())
                .publisherId(book.getPublisher().getId())
                .build();
    }

    @Nested
    class QueryMethods {
        @Test
        @DisplayName("findAll() -> should return list of books")
        void findAll_shouldReturnListOfBooks() {
            // Arrange
            when(bookRepository.findAll()).thenReturn(List.of(book));
            when(mapper.toResponseList(List.of(book))).thenReturn(List.of(responseSample));

            // Act
            List<BookResponseDto> result = bookService.findAll();

            // Assert
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(responseSample, result.get(0));

            verify(bookRepository, times(1)).findAll();
            verify(mapper, times(1)).toResponseList(List.of(book));
        }

        @Test
        @DisplayName("findAll(Pageable) -> should return page of books")
        void findAllPageable_shouldReturnPageOfBooks() {
            // Arrange
            Pageable pageable = Pageable.unpaged();

            when(bookRepository.findAll(pageable)).thenReturn(Page.empty());

            // Act
            Page<BookResponseDto> result = bookService.findAll(pageable);

            // Assert
            assertNotNull(result);
            assertEquals(0, result.getTotalElements());
            assertEquals(1, result.getTotalPages());

            verify(bookRepository, times(1)).findAll(pageable);
        }

        @Test
        @DisplayName("findById(UUID) -> should return book by id")
        void findById_shouldReturnBook() {
            // Arrange
            when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
            when(mapper.toResponse(book)).thenReturn(responseSample);

            // Act
            BookResponseDto result = bookService.findById(book.getId());

            // Assert
            assertNotNull(result);
            assertEquals(responseSample, result);

            verify(bookRepository, times(1)).findById(book.getId());
            verify(mapper, times(1)).toResponse(book);
        }

        @Test
        @DisplayName("findById(UUID) -> should throw EntityNotFoundException when book not found")
        void findById_shouldThrowEntityNotFoundException_whenBookNotFound() {
            // Arrange
            when(bookRepository.findById(book.getId())).thenReturn(Optional.empty());

            // Act
            Runnable result = () -> bookService.findById(book.getId());

            // Assert
            assertThrows(EntityNotFoundException.class, result::run);

            verify(bookRepository, times(1)).findById(book.getId());
        }

        @Test
        @DisplayName("findByIsbn(String) -> should return book by isbn")
        void findByIsbn_shouldReturnBook() {
            // Arrange
            when(bookRepository.findByIsbn(book.getIsbn())).thenReturn(Optional.of(book));
            when(mapper.toResponse(book)).thenReturn(responseSample);

            // Act
            BookResponseDto result = bookService.findByIsbn(book.getIsbn());

            // Assert
            assertNotNull(result);
            assertEquals(responseSample, result);

            verify(bookRepository, times(1)).findByIsbn(book.getIsbn());
            verify(mapper, times(1)).toResponse(book);
        }

        @Test
        @DisplayName("findByIsbn(String) -> should throw EntityNotFoundException when book not found")
        void findByIsbn_shouldThrowEntityNotFoundException_whenBookNotFound() {
            // Arrange
            when(bookRepository.findByIsbn(book.getIsbn())).thenReturn(Optional.empty());

            // Act
            Runnable result = () -> bookService.findByIsbn(book.getIsbn());

            // Assert
            assertThrows(EntityNotFoundException.class, result::run);

            verify(bookRepository, times(1)).findByIsbn(book.getIsbn());
        }

        @Test
        @DisplayName("findByTitle(String) -> should return book by title")
        void findByTitle_shouldReturnBook() {
            // Arrange
            when(bookRepository.findByTitle(book.getTitle())).thenReturn(Optional.of(book));
            when(mapper.toResponse(book)).thenReturn(responseSample);

            // Act
            BookResponseDto result = bookService.findByTitle(book.getTitle());

            // Assert
            assertNotNull(result);
            assertEquals(responseSample, result);

            verify(bookRepository, times(1)).findByTitle(book.getTitle());
            verify(mapper, times(1)).toResponse(book);
        }

        @Test
        @DisplayName("findByTitle(String) -> should throw EntityNotFoundException when book not found")
        void findByTitle_shouldThrowEntityNotFoundException_whenBookNotFound() {
            // Arrange
            when(bookRepository.findByTitle(book.getTitle())).thenReturn(Optional.empty());

            // Act
            Runnable result = () -> bookService.findByTitle(book.getTitle());

            // Assert
            assertThrows(EntityNotFoundException.class, result::run);

            verify(bookRepository, times(1)).findByTitle(book.getTitle());
        }
    }

    @Nested
    class MutationMethods {
        @BeforeEach
        void setUp() {
            requestSample = BookRequestDto.builder()
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
        @DisplayName("save(BookRequestDto) -> should return saved book")
        void save_shouldReturnSavedBook() {
            // Arrange
            when(authorService.existsById(book.getAuthor().getId())).thenReturn(true);
            when(publisherService.existsById(book.getPublisher().getId())).thenReturn(true);
            when(bookRepository.existsByIsbn(book.getIsbn())).thenReturn(false);
            when(mapper.toEntity(requestSample)).thenReturn(book);
            when(bookRepository.save(book)).thenReturn(book);
            when(mapper.toResponse(book)).thenReturn(responseSample);

            // Act
            BookResponseDto result = bookService.save(requestSample);

            // Assert
            assertNotNull(result);
            assertEquals(responseSample, result);

            verify(authorService, times(1)).existsById(book.getAuthor().getId());
            verify(publisherService, times(1)).existsById(book.getPublisher().getId());
            verify(bookRepository, times(1)).existsByIsbn(book.getIsbn());
            verify(mapper, times(1)).toEntity(requestSample);
            verify(bookRepository, times(1)).save(book);
            verify(mapper, times(1)).toResponse(book);
        }

        @Test
        @DisplayName("save(BookRequestDto) -> should throw EntityNotFoundException when author not found")
        void save_shouldThrowEntityNotFoundException_whenAuthorNotFound() {
            // Arrange
            when(authorService.existsById(book.getAuthor().getId())).thenReturn(false);

            // Act
            Runnable result = () -> bookService.save(requestSample);

            // Assert
            assertThrows(EntityNotFoundException.class, result::run);

            verify(authorService, times(1)).existsById(book.getAuthor().getId());
        }

        @Test
        @DisplayName("save(BookRequestDto) -> should throw EntityNotFoundException when publisher not found")
        void save_shouldThrowEntityNotFoundException_whenPublisherNotFound() {
            // Arrange
            when(authorService.existsById(book.getAuthor().getId())).thenReturn(true);
            when(publisherService.existsById(book.getPublisher().getId())).thenReturn(false);

            // Act
            Runnable result = () -> bookService.save(requestSample);

            // Assert
            assertThrows(EntityNotFoundException.class, result::run);

            verify(authorService, times(1)).existsById(book.getAuthor().getId());
            verify(publisherService, times(1)).existsById(book.getPublisher().getId());
        }

        @Test
        @DisplayName("save(BookRequestDto) -> should throw IsbnAlreadyExistsException when isbn already exists")
        void save_shouldThrowIsbnAlreadyExistsException_whenIsbnAlreadyExists() {
            // Arrange
            when(authorService.existsById(book.getAuthor().getId())).thenReturn(true);
            when(publisherService.existsById(book.getPublisher().getId())).thenReturn(true);
            when(bookRepository.existsByIsbn(book.getIsbn())).thenReturn(true);

            // Act
            Runnable result = () -> bookService.save(requestSample);

            // Assert
            assertThrows(IsbnAlreadyExistsException.class, result::run);

            verify(authorService, times(1)).existsById(book.getAuthor().getId());
            verify(publisherService, times(1)).existsById(book.getPublisher().getId());
            verify(bookRepository, times(1)).existsByIsbn(book.getIsbn());
        }

        @Test
        @DisplayName("save(BookRequestDto) -> should throw PublicationYearTooNew when publication year is too new")
        void save_shouldThrowPublicationYearTooNew_whenPublicationYearIsTooNew() {
            // Arrange
            when(authorService.existsById(book.getAuthor().getId())).thenReturn(true);
            when(publisherService.existsById(book.getPublisher().getId())).thenReturn(true);
            when(bookRepository.existsByIsbn(book.getIsbn())).thenReturn(false);
            requestSample.setPublicationYear(LocalDate.now().getYear() + 1);

            // Act
            Runnable result = () -> bookService.save(requestSample);

            // Assert
            assertThrows(PublicationYearTooNew.class, result::run);

            verify(authorService, times(1)).existsById(book.getAuthor().getId());
            verify(publisherService, times(1)).existsById(book.getPublisher().getId());
            verify(bookRepository, times(1)).existsByIsbn(book.getIsbn());
        }

        @Test
        @DisplayName("update(UUID, BookRequestDto) -> should return updated book")
        void update_shouldReturnUpdatedBook() {
            // Arrange
            when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
            when(authorService.existsById(book.getAuthor().getId())).thenReturn(true);
            when(publisherService.existsById(book.getPublisher().getId())).thenReturn(true);
            when(bookRepository.existsByIsbnAndIdNot(book.getIsbn(), book.getId())).thenReturn(false);
            when(mapper.updateEntity(book, requestSample)).thenReturn(book);
            when(bookRepository.save(book)).thenReturn(book);
            when(mapper.toResponse(book)).thenReturn(responseSample);

            // Act
            BookResponseDto result = bookService.update(book.getId(), requestSample);

            // Assert
            assertNotNull(result);
            assertEquals(responseSample, result);

            verify(bookRepository, times(1)).findById(book.getId());
            verify(authorService, times(1)).existsById(book.getAuthor().getId());
            verify(publisherService, times(1)).existsById(book.getPublisher().getId());
            verify(bookRepository, times(1)).existsByIsbnAndIdNot(book.getIsbn(), book.getId());
            verify(mapper, times(1)).updateEntity(book, requestSample);
            verify(bookRepository, times(1)).save(book);
            verify(mapper, times(1)).toResponse(book);
        }

        @Test
        @DisplayName("update(UUID, BookRequestDto) -> should throw EntityNotFoundException when book not found")
        void update_shouldThrowEntityNotFoundException_whenBookNotFound() {
            // Arrange
            when(bookRepository.findById(book.getId())).thenReturn(Optional.empty());

            // Act
            Runnable result = () -> bookService.update(book.getId(), requestSample);

            // Assert
            assertThrows(EntityNotFoundException.class, result::run);

            verify(bookRepository, times(1)).findById(book.getId());
        }

        @Test
        @DisplayName("update(UUID, BookRequestDto) -> should throw EntityNotFoundException when author not found")
        void update_shouldThrowEntityNotFoundException_whenAuthorNotFound() {
            // Arrange
            when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
            when(authorService.existsById(book.getAuthor().getId())).thenReturn(false);

            // Act
            Runnable result = () -> bookService.update(book.getId(), requestSample);

            // Assert
            assertThrows(EntityNotFoundException.class, result::run);

            verify(bookRepository, times(1)).findById(book.getId());
            verify(authorService, times(1)).existsById(book.getAuthor().getId());
        }

        @Test
        @DisplayName("update(UUID, BookRequestDto) -> should throw EntityNotFoundException when publisher not found")
        void update_shouldThrowEntityNotFoundException_whenPublisherNotFound() {
            // Arrange
            when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
            when(authorService.existsById(book.getAuthor().getId())).thenReturn(true);
            when(publisherService.existsById(book.getPublisher().getId())).thenReturn(false);

            // Act
            Runnable result = () -> bookService.update(book.getId(), requestSample);

            // Assert
            assertThrows(EntityNotFoundException.class, result::run);

            verify(bookRepository, times(1)).findById(book.getId());
            verify(authorService, times(1)).existsById(book.getAuthor().getId());
            verify(publisherService, times(1)).existsById(book.getPublisher().getId());
        }

        @Test
        @DisplayName("update(UUID, BookRequestDto) -> should throw IsbnAlreadyExistsException when isbn already exists")
        void update_shouldThrowIsbnAlreadyExistsException_whenIsbnAlreadyExists() {
            // Arrange
            when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
            when(authorService.existsById(book.getAuthor().getId())).thenReturn(true);
            when(publisherService.existsById(book.getPublisher().getId())).thenReturn(true);
            when(bookRepository.existsByIsbnAndIdNot(book.getIsbn(), book.getId())).thenReturn(true);

            // Act
            Runnable result = () -> bookService.update(book.getId(), requestSample);

            // Assert
            assertThrows(IsbnAlreadyExistsException.class, result::run);

            verify(bookRepository, times(1)).findById(book.getId());
            verify(authorService, times(1)).existsById(book.getAuthor().getId());
            verify(publisherService, times(1)).existsById(book.getPublisher().getId());
            verify(bookRepository, times(1)).existsByIsbnAndIdNot(book.getIsbn(), book.getId());
        }

        @Test
        @DisplayName("update(UUID, BookRequestDto) -> should throw PublicationYearTooNew when publication year is too new")
        void update_shouldThrowPublicationYearTooNew_whenPublicationYearIsTooNew() {
            // Arrange
            when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
            when(authorService.existsById(book.getAuthor().getId())).thenReturn(true);
            when(publisherService.existsById(book.getPublisher().getId())).thenReturn(true);
            when(bookRepository.existsByIsbnAndIdNot(book.getIsbn(), book.getId())).thenReturn(false);
            requestSample.setPublicationYear(LocalDate.now().getYear() + 1);

            // Act
            Runnable result = () -> bookService.update(book.getId(), requestSample);

            // Assert
            assertThrows(PublicationYearTooNew.class, result::run);

            verify(bookRepository, times(1)).findById(book.getId());
            verify(authorService, times(1)).existsById(book.getAuthor().getId());
            verify(publisherService, times(1)).existsById(book.getPublisher().getId());
            verify(bookRepository, times(1)).existsByIsbnAndIdNot(book.getIsbn(), book.getId());
        }

        @Test
        @DisplayName("deleteById(UUID) -> should delete book")
        void deleteById_shouldDeleteBook() {
            // Arrange
            when(bookRepository.existsById(book.getId())).thenReturn(true);

            // Act
            bookService.deleteById(book.getId());

            // Assert
            verify(bookRepository, times(1)).existsById(book.getId());
            verify(bookRepository, times(1)).deleteById(book.getId());
        }

        @Test
        @DisplayName("deleteById(UUID) -> should throw EntityNotFoundException when book not found")
        void deleteById_shouldThrowEntityNotFoundException_whenBookNotFound() {
            // Arrange
            when(bookRepository.existsById(book.getId())).thenReturn(false);

            // Act
            Runnable result = () -> bookService.deleteById(book.getId());

            // Assert
            assertThrows(EntityNotFoundException.class, result::run);

            verify(bookRepository, times(1)).existsById(book.getId());
        }
    }
}