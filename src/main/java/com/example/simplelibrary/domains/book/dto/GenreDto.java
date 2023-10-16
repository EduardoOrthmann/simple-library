package com.example.simplelibrary.domains.book.dto;

import com.example.simplelibrary.domains.book.Genre;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenreDto {
    @Schema(example = "FANTASY", description = "Genre's value")
    private Genre genre;

    @Schema(example = "Fantasia", description = "Genre's name")
    private String name;
}
