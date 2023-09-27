package com.example.simplelibrary.domains.book.dto;

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
    private String genre;

    @JsonProperty("publication_year")
    private Integer publicationYear;

    private Integer quantity;

    @JsonProperty("author_id")
    private UUID authorId;

    @JsonProperty("publisher_id")
    private UUID publisherId;
}
