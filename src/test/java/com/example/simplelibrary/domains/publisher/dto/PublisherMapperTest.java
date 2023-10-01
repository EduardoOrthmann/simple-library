package com.example.simplelibrary.domains.publisher.dto;

import com.example.simplelibrary.domains.publisher.Publisher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PublisherMapperTest {
    private static PublisherMapper mapper;
    private Publisher publisher;
    private PublisherRequestDto publisherRequestDto;

    @BeforeAll
    static void beforeAll() {
        mapper = new PublisherMapper();
    }

    @BeforeEach
    void setUp() {
        publisher = Publisher.builder()
                .id(UUID.randomUUID())
                .name("Publisher Name")
                .address("Publisher Address")
                .phoneNumber("123456789")
                .books(List.of())
                .build();

        publisherRequestDto = PublisherRequestDto.builder()
                .name(publisher.getName())
                .address(publisher.getAddress())
                .phoneNumber(publisher.getPhoneNumber())
                .build();
    }

    @Test
    @DisplayName("toResponse -> should map Publisher to PublisherResponseDto")
    void toResponse_shouldMapPublisherToPublisherResponseDto() {
        // Act
        PublisherResponseDto result = mapper.toResponse(publisher);

        // Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("toEntity -> should map PublisherRequestDto to Publisher")
    void toEntity_shouldMapPublisherRequestDtoToPublisher() {
        // Act
        Publisher result = mapper.toEntity(publisherRequestDto);

        // Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("toResponseList -> should map List<Publisher> to List<PublisherResponseDto>")
    void toResponseList_shouldMapListOfPublisherToListOfPublisherResponseDto() {
        // Act
        List<PublisherResponseDto> result = mapper.toResponseList(List.of(publisher));

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("updateEntity -> should update Publisher with PublisherRequestDto")
    void updateEntity_shouldUpdatePublisherWithPublisherRequestDto() {
        // Act
        Publisher result = mapper.updateEntity(publisher, publisherRequestDto);

        // Assert
        assertNotNull(result);
    }
}