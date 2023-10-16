package com.example.simplelibrary.domains.book.dto;

import com.example.simplelibrary.domains.author.dto.AuthorNameDto;
import com.example.simplelibrary.domains.book.Genre;
import com.example.simplelibrary.domains.publisher.dto.PublisherNameDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponseDto {
    private UUID id;

    @Schema(example = "9780545582889", description = "The ISBN of the book (International Standard Book Number)")
    private String isbn;

    private String title;
    private Genre genre;

    @JsonProperty("publication_year")
    private Integer publicationYear;

    @Schema(example = "3", description = "The quantity of copies available")
    private Integer quantity;

    private AuthorNameDto author;

    private PublisherNameDto publisher;
}
