package com.example.simplelibrary.domains.book.dto;

import com.example.simplelibrary.domains.book.Genre;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequestDto {
    @Schema(example = "9780545582889", description = "The ISBN of the book (International Standard Book Number)")
    @NotNull(message = "O ISBN não pode ser nulo")
    @NotBlank(message = "O ISBN não pode ser vazio")
    @Size(min = 13, max = 13, message = "O ISBN deve ter 13 caracteres")
    private String isbn;

    @NotNull(message = "O título não pode ser nulo")
    @NotBlank(message = "O título não pode ser vazio")
    private String title;

    @NotNull(message = "O gênero não pode ser nulo")
    private Genre genre;

    @NotNull(message = "O ano de publicação não pode ser nulo")
    @Positive(message = "O ano de publicação deve ser positivo")
    @JsonProperty("publication_year")
    private Integer publicationYear;

    @Schema(example = "3", description = "The quantity of copies available")
    @NotNull(message = "A quantidade não pode ser nula")
    @Positive(message = "A quantidade deve ser positiva")
    private Integer quantity;

    @Schema(example = "d290f1ee-6c54-4b01-90e6-d701748f0851", description = "The author's id")
    @NotNull(message = "O autor não pode ser nulo")
    @JsonAlias({"author_id", "author"})
    private UUID author;

    @Schema(example = "d290f1ee-6c54-4b01-90e6-d701748f0851", description = "The publisher's id")
    @NotNull(message = "A editora não pode ser nula")
    @JsonAlias({"publisher_id", "publisher"})
    private UUID publisher;
}
