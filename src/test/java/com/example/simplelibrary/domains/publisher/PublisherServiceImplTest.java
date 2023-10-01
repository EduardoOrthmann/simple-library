package com.example.simplelibrary.domains.publisher;

import com.example.simplelibrary.domains.book.dto.BookMapper;
import com.example.simplelibrary.domains.book.dto.BookResponseDto;
import com.example.simplelibrary.domains.publisher.dto.PublisherMapper;
import com.example.simplelibrary.domains.publisher.dto.PublisherRequestDto;
import com.example.simplelibrary.domains.publisher.dto.PublisherResponseDto;
import com.example.simplelibrary.exceptions.PublisherNameAlreadyExists;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PublisherServiceImplTest {
    @InjectMocks
    private PublisherServiceImpl publisherService;

    @Mock
    private PublisherRepository publisherRepository;

    @Mock
    private PublisherMapper mapper;

    @Mock
    private BookMapper bookMapper;

    private Publisher publisher;
    private PublisherResponseDto response;
    private PublisherRequestDto request;

    @BeforeEach
    void setUp() {
        publisher = Publisher.builder()
                .id(UUID.randomUUID())
                .name("Publisher")
                .address("Address")
                .phoneNumber("1234567890")
                .books(new ArrayList<>())
                .build();

        response = PublisherResponseDto.builder()
                .id(publisher.getId())
                .name(publisher.getName())
                .address(publisher.getAddress())
                .phoneNumber(publisher.getPhoneNumber())
                .build();
    }

    @Nested
    class QueryMethods {
        @Test
        @DisplayName("findAll() -> should return list of PublisherResponseDto")
        void findAll_shouldReturnListOfPublishers() {
            // Arrange
            when(publisherRepository.findAll()).thenReturn(List.of(publisher));
            when(mapper.toResponseList(List.of(publisher))).thenReturn(List.of(response));

            // Act
            List<PublisherResponseDto> result = publisherService.findAll();

            // Assert
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(response, result.get(0));

            verify(publisherRepository, times(1)).findAll();
            verify(mapper, times(1)).toResponseList(List.of(publisher));
        }

        @Test
        @DisplayName("findById() -> should return PublisherResponseDto")
        void findById_shouldReturnPublisher() {
            // Arrange
            when(publisherRepository.findById(publisher.getId())).thenReturn(Optional.of(publisher));
            when(mapper.toResponse(publisher)).thenReturn(response);

            // Act
            PublisherResponseDto result = publisherService.findById(publisher.getId());

            // Assert
            assertNotNull(result);
            assertEquals(response, result);

            verify(publisherRepository, times(1)).findById(publisher.getId());
            verify(mapper, times(1)).toResponse(publisher);
        }

        @Test
        @DisplayName("findById() -> should throw EntityNotFoundException when publisher not found")
        void findById_shouldThrowEntityNotFoundException_whenPublisherNotFound() {
            // Arrange
            when(publisherRepository.findById(publisher.getId())).thenReturn(Optional.empty());

            // Act
            Runnable result = () -> publisherService.findById(publisher.getId());

            // Assert
            assertThrows(EntityNotFoundException.class, result::run);

            verify(publisherRepository, times(1)).findById(publisher.getId());
            verify(mapper, times(0)).toResponse(publisher);
        }

        @Test
        @DisplayName("existsById() -> should return true when publisher exists")
        void existsById_shouldReturnTrue_whenPublisherExists() {
            // Arrange
            when(publisherRepository.existsById(publisher.getId())).thenReturn(true);

            // Act
            boolean result = publisherService.existsById(publisher.getId());

            // Assert
            assertTrue(result);

            verify(publisherRepository, times(1)).existsById(publisher.getId());
        }

        @Test
        @DisplayName("existsById() -> should return false when publisher not exists")
        void existsById_shouldReturnFalse_whenPublisherNotExists() {
            // Arrange
            when(publisherRepository.existsById(publisher.getId())).thenReturn(false);

            // Act
            boolean result = publisherService.existsById(publisher.getId());

            // Assert
            assertFalse(result);

            verify(publisherRepository, times(1)).existsById(publisher.getId());
        }

        @Test
        @DisplayName("findBooksByPublisherId() -> should return list of BookResponseDto")
        void findBooksByPublisherId_shouldReturnListOfBooks() {
            // Arrange
            when(publisherRepository.findById(publisher.getId())).thenReturn(Optional.of(publisher));
            when(bookMapper.toResponseList(publisher.getBooks())).thenReturn(new ArrayList<>());

            // Act
            List<BookResponseDto> result = publisherService.findBooksByPublisherId(publisher.getId());

            // Assert
            assertNotNull(result);
            assertEquals(0, result.size());

            verify(publisherRepository, times(1)).findById(publisher.getId());
            verify(bookMapper, times(1)).toResponseList(publisher.getBooks());
        }

        @Test
        @DisplayName("findBooksByPublisherId() -> should throw EntityNotFoundException when publisher not found")
        void findBooksByPublisherId_shouldThrowEntityNotFoundException_whenPublisherNotFound() {
            // Arrange
            when(publisherRepository.findById(publisher.getId())).thenReturn(Optional.empty());

            // Act
            Runnable result = () -> publisherService.findBooksByPublisherId(publisher.getId());

            // Assert
            assertThrows(EntityNotFoundException.class, result::run);

            verify(publisherRepository, times(1)).findById(publisher.getId());
            verify(bookMapper, times(0)).toResponseList(publisher.getBooks());
        }
    }

    @Nested
    class MutationMethods {
        @BeforeEach
        void setUp() {
            request = PublisherRequestDto.builder()
                    .name(publisher.getName())
                    .address(publisher.getAddress())
                    .phoneNumber(publisher.getPhoneNumber())
                    .build();
        }

        @Test
        @DisplayName("save() -> should return PublisherResponseDto")
        void save_shouldReturnPublisher() {
            // Arrange
            when(publisherRepository.existsByName(request.getName())).thenReturn(false);
            when(mapper.toEntity(request)).thenReturn(publisher);
            when(publisherRepository.save(publisher)).thenReturn(publisher);
            when(mapper.toResponse(publisher)).thenReturn(response);

            // Act
            PublisherResponseDto result = publisherService.save(request);

            // Assert
            assertNotNull(result);
            assertEquals(response, result);

            verify(publisherRepository, times(1)).existsByName(request.getName());
            verify(mapper, times(1)).toEntity(request);
            verify(publisherRepository, times(1)).save(publisher);
            verify(mapper, times(1)).toResponse(publisher);
        }

        @Test
        @DisplayName("save() -> should throw PublisherNameAlreadyExists when publisher name already exists")
        void save_shouldThrowPublisherNameAlreadyExists_whenPublisherNameAlreadyExists() {
            // Arrange
            when(publisherRepository.existsByName(request.getName())).thenReturn(true);

            // Act
            Runnable result = () -> publisherService.save(request);

            // Assert
            assertThrows(PublisherNameAlreadyExists.class, result::run);

            verify(publisherRepository, times(1)).existsByName(request.getName());
            verify(mapper, times(0)).toEntity(request);
            verify(publisherRepository, times(0)).save(publisher);
            verify(mapper, times(0)).toResponse(publisher);
        }

        @Test
        @DisplayName("update() -> should return PublisherResponseDto")
        void update_shouldReturnPublisher() {
            // Arrange
            when(publisherRepository.findById(publisher.getId())).thenReturn(Optional.of(publisher));
            when(publisherRepository.existsByNameAndIdNot(request.getName(), publisher.getId())).thenReturn(false);
            when(mapper.updateEntity(publisher, request)).thenReturn(publisher);
            when(publisherRepository.save(publisher)).thenReturn(publisher);
            when(mapper.toResponse(publisher)).thenReturn(response);

            // Act
            PublisherResponseDto result = publisherService.update(publisher.getId(), request);

            // Assert
            assertNotNull(result);
            assertEquals(response, result);

            verify(publisherRepository, times(1)).findById(publisher.getId());
            verify(publisherRepository, times(1)).existsByNameAndIdNot(request.getName(), publisher.getId());
            verify(mapper, times(1)).updateEntity(publisher, request);
            verify(publisherRepository, times(1)).save(publisher);
            verify(mapper, times(1)).toResponse(publisher);
        }

        @Test
        @DisplayName("update() -> should throw EntityNotFoundException when publisher not found")
        void update_shouldThrowEntityNotFoundException_whenPublisherNotFound() {
            // Arrange
            when(publisherRepository.findById(publisher.getId())).thenReturn(Optional.empty());

            // Act
            Runnable result = () -> publisherService.update(publisher.getId(), request);

            // Assert
            assertThrows(EntityNotFoundException.class, result::run);

            verify(publisherRepository, times(1)).findById(publisher.getId());
            verify(publisherRepository, times(0)).existsByNameAndIdNot(request.getName(), publisher.getId());
            verify(mapper, times(0)).updateEntity(publisher, request);
            verify(publisherRepository, times(0)).save(publisher);
            verify(mapper, times(0)).toResponse(publisher);
        }

        @Test
        @DisplayName("update() -> should throw PublisherNameAlreadyExists when publisher name already exists")
        void update_shouldThrowPublisherNameAlreadyExists_whenPublisherNameAlreadyExists() {
            // Arrange
            when(publisherRepository.findById(publisher.getId())).thenReturn(Optional.of(publisher));
            when(publisherRepository.existsByNameAndIdNot(request.getName(), publisher.getId())).thenReturn(true);

            // Act
            Runnable result = () -> publisherService.update(publisher.getId(), request);

            // Assert
            assertThrows(PublisherNameAlreadyExists.class, result::run);

            verify(publisherRepository, times(1)).findById(publisher.getId());
            verify(publisherRepository, times(1)).existsByNameAndIdNot(request.getName(), publisher.getId());
            verify(mapper, times(0)).updateEntity(publisher, request);
            verify(publisherRepository, times(0)).save(publisher);
            verify(mapper, times(0)).toResponse(publisher);
        }

        @Test
        @DisplayName("deleteById() -> should delete publisher")
        void deleteById_shouldDeletePublisher() {
            // Arrange
            when(publisherRepository.existsById(publisher.getId())).thenReturn(true);

            // Act
            publisherService.deleteById(publisher.getId());

            // Assert
            verify(publisherRepository, times(1)).existsById(publisher.getId());
            verify(publisherRepository, times(1)).deleteById(publisher.getId());
        }

        @Test
        @DisplayName("deleteById() -> should throw EntityNotFoundException when publisher not found")
        void deleteById_shouldThrowEntityNotFoundException_whenPublisherNotFound() {
            // Arrange
            when(publisherRepository.existsById(publisher.getId())).thenReturn(false);

            // Act
            Runnable result = () -> publisherService.deleteById(publisher.getId());

            // Assert
            assertThrows(EntityNotFoundException.class, result::run);

            verify(publisherRepository, times(1)).existsById(publisher.getId());
            verify(publisherRepository, times(0)).deleteById(publisher.getId());
        }
    }
}