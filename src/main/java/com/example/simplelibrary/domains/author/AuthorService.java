package com.example.simplelibrary.domains.author;

import com.example.simplelibrary.domains.author.dto.AuthorRequestDto;
import com.example.simplelibrary.domains.author.dto.AuthorResponseDto;
import com.example.simplelibrary.domains.book.dto.BookResponseDto;
import com.example.simplelibrary.interfaces.Crud;

import java.util.List;
import java.util.UUID;

public interface AuthorService extends Crud<AuthorRequestDto, AuthorResponseDto, UUID> {
    List<BookResponseDto> findBooksByAuthorId(UUID id);
    boolean existsById(UUID id);
}
