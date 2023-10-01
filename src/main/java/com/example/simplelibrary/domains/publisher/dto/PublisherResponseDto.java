package com.example.simplelibrary.domains.publisher.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String address;

    @JsonProperty("phone_number")
    private String phoneNumber;
}
