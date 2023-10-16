package com.example.simplelibrary.domains.publisher.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublisherNameDto {
    private UUID id;

    @Schema(example = "Bloomsbury Publishing", description = "Publisher's name")
    private String name;
}
