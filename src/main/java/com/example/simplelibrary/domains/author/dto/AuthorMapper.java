package com.example.simplelibrary.domains.author.dto;

import com.example.simplelibrary.domains.author.Author;
import com.example.simplelibrary.interfaces.Mapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class AuthorMapper implements Mapper<Author, AuthorRequestDto, AuthorResponseDto> {
    @Override
    public AuthorResponseDto toResponse(Author author) {
        return AuthorResponseDto.builder()
                .id(author.getId())
                .name(author.getName())
                .birthDate(author.getBirthDate())
                .nationality(author.getNationality())
                .build();
    }

    @Override
    public Author toEntity(AuthorRequestDto request) {
        return Author.builder()
                .name(request.getName())
                .birthDate(request.getBirthDate())
                .nationality(request.getNationality())
                .build();
    }

    @Override
    public List<AuthorResponseDto> toResponseList(Collection<Author> list) {
        return list.stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public Author updateEntity(Author author, AuthorRequestDto request) {
        author.setName(request.getName());
        author.setBirthDate(request.getBirthDate());
        author.setNationality(request.getNationality());

        return author;
    }

    public AuthorNameDto toNameDto(Author author) {
        return AuthorNameDto.builder()
                .id(author.getId())
                .name(author.getName())
                .build();
    }

    public List<AuthorNameDto> toNameList(Collection<Author> list) {
        return list.stream()
                .map(this::toNameDto)
                .toList();
    }
}
