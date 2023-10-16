package com.example.simplelibrary.domains.author;

import com.example.simplelibrary.domains.author.dto.AuthorMapper;
import com.example.simplelibrary.domains.author.dto.AuthorNameDto;
import com.example.simplelibrary.domains.author.dto.AuthorRequestDto;
import com.example.simplelibrary.domains.author.dto.AuthorResponseDto;
import com.example.simplelibrary.domains.book.dto.BookMapper;
import com.example.simplelibrary.domains.book.dto.BookResponseDto;
import com.example.simplelibrary.utils.ErrorMessages;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper mapper;
    private final BookMapper bookMapper;

    @Override
    public List<AuthorResponseDto> findAll() {
        return mapper.toResponseList(authorRepository.findAll());
    }

    @Override
    public AuthorResponseDto findById(UUID id) {
        return authorRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.AUTHOR_NOT_FOUND));
    }

    @Override
    public AuthorResponseDto save(AuthorRequestDto request) {
        return mapper.toResponse(
                authorRepository.save(mapper.toEntity(request))
        );
    }

    @Override
    public AuthorResponseDto update(UUID id, AuthorRequestDto request) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorMessages.AUTHOR_NOT_FOUND));

        return mapper.toResponse(
                authorRepository.save(mapper.updateEntity(author, request))
        );
    }

    @Override
    public void deleteById(UUID id) {
        if (!authorRepository.existsById(id)) {
            throw new EntityNotFoundException(ErrorMessages.AUTHOR_NOT_FOUND);
        }

        authorRepository.deleteById(id);
    }

    @Override
    public List<BookResponseDto> findBooksByAuthorId(UUID id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorMessages.AUTHOR_NOT_FOUND));

        return bookMapper.toResponseList(author.getBooks());
    }

    @Override
    public boolean existsById(UUID id) {
        return authorRepository.existsById(id);
    }

    @Override
    public List<AuthorNameDto> findAllNames() {
        return mapper.toNameList(authorRepository.findAll());
    }
}
