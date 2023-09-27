package com.example.simplelibrary.domains.book;


import com.example.simplelibrary.domains.book.dto.BookRequestDto;
import com.example.simplelibrary.domains.book.dto.BookResponseDto;
import com.example.simplelibrary.interfaces.Crud;

import java.util.UUID;

public interface BookService extends Crud<BookRequestDto, BookResponseDto, UUID> {
}
