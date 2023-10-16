package com.example.simplelibrary.domains.author.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorNameDto {
    private UUID id;
    private String name;
}
