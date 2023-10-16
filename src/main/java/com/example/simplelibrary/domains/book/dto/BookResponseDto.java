package com.example.simplelibrary.domains.book.dto;

import com.example.simplelibrary.domains.author.dto.AuthorNameDto;
import com.example.simplelibrary.domains.book.Genre;
import com.example.simplelibrary.domains.publisher.dto.PublisherNameDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponseDto {
    private UUID id;
    private String isbn;

    private String title;
    private Genre genre;

    @JsonProperty("publication_year")
    private Integer publicationYear;

    private Integer quantity;

    private AuthorNameDto author;

    private PublisherNameDto publisher;
}
