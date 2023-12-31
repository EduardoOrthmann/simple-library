package com.example.simplelibrary.domains.author.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorRequestDto {
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser vazio")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    private String name;

    @Schema(description = "Data de nascimento do autor", example = "01/01/2000")
    @NotNull(message = "A data de nascimento não pode ser nula")
    @Past(message = "A data de nascimento deve ser anterior a data atual")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("birth_date")
    private LocalDate birthDate;

    @Schema(description = "Nacionalidade do autor", example = "Brasileiro")
    @NotNull(message = "A nacionalidade não pode ser nula")
    @NotBlank(message = "A nacionalidade não pode ser vazia")
    @Size(min = 2, max = 50, message = "A nacionalidade deve ter entre 2 e 50 caracteres")
    private String nationality;
}
