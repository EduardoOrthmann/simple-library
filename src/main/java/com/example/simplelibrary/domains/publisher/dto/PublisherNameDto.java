package com.example.simplelibrary.domains.publisher.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublisherNameDto {
    private UUID id;
    private String name;
}
