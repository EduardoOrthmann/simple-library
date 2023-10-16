package com.example.simplelibrary.domains.author.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorNameDto {
    private UUID id;

    @Schema(example = "J. K. Rowling", description = "Author's name")
    private String name;
}
