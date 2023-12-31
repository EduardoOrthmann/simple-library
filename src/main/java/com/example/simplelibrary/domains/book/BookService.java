package com.example.simplelibrary.domains.book;


import com.example.simplelibrary.domains.book.dto.BookRequestDto;
import com.example.simplelibrary.domains.book.dto.BookResponseDto;
import com.example.simplelibrary.domains.book.dto.GenreDto;
import com.example.simplelibrary.interfaces.Crud;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface BookService extends Crud<BookRequestDto, BookResponseDto, UUID> {
    BookResponseDto findByIsbn(String isbn);

    List<BookResponseDto> findByTitle(String title);

    Page<BookResponseDto> findAll(Pageable pageable);

    List<GenreDto> findAllGenres();
}
