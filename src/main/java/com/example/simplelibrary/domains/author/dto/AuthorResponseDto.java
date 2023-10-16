package com.example.simplelibrary.domains.author.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorResponseDto {
    private UUID id;
    private String name;

    @Schema(description = "Data de nascimento do autor", example = "01/01/2000")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("birth_date")
    private LocalDate birthDate;

    @Schema(description = "Nacionalidade do autor", example = "Brasileiro")
    private String nationality;
}
