package com.example.simplelibrary.domains.publisher.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublisherResponseDto {
    private String id;
    private String name;
    private String address;

    @JsonProperty("phone_number")
    private String phoneNumber;
}
