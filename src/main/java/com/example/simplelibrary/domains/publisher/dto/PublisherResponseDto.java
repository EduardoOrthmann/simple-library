package com.example.simplelibrary.domains.publisher.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
    @JsonSerialize(using = PhoneNumberSerializer.class)
    private String phoneNumber;
}
