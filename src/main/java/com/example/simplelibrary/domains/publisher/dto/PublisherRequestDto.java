package com.example.simplelibrary.domains.publisher.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublisherRequestDto {
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser vazio")
    private String name;

    @Schema(example = "Rua dos Caçadores, 144", description = "Publisher's address")
    private String address;

    @Schema(example = "000000000", description = "Publisher's phone number")
    @NotNull(message = "O número de telefone não pode ser nulo")
    @NotBlank(message = "O número de telefone não pode ser vazio")
    @Size(min = 9, max = 16, message = "O número de telefone deve ter entre 9 e 16 caracteres")
    @JsonProperty("phone_number")
    private String phoneNumber;
}
