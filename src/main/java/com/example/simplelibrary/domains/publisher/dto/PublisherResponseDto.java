package com.example.simplelibrary.domains.publisher.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublisherResponseDto {
    private UUID id;
    private String name;

    @Schema(example = "Rua dos Caçadores, 144", description = "Publisher's address")
    private String address;

    @Schema(example = "(00) 00000-0000", description = "Publisher's phone number")
    @JsonProperty("phone_number")
    @JsonSerialize(using = PhoneNumberSerializer.class)
    private String phoneNumber;
}
