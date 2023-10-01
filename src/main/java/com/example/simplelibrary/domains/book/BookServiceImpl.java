package com.example.simplelibrary.domains.book;

import com.example.simplelibrary.domains.author.AuthorService;
import com.example.simplelibrary.domains.book.dto.BookMapper;
import com.example.simplelibrary.domains.book.dto.BookRequestDto;
import com.example.simplelibrary.domains.book.dto.BookResponseDto;
import com.example.simplelibrary.domains.publisher.PublisherService;
import com.example.simplelibrary.exceptions.IsbnAlreadyExistsException;
import com.example.simplelibrary.exceptions.PublicationYearTooNew;
import com.example.simplelibrary.utils.ErrorMessages;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final BookMapper mapper;

    @Override
    public List<BookResponseDto> findAll() {
        return mapper.toResponseList(bookRepository.findAll());
    }

    @Override
    public Page<BookResponseDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).map(mapper::toResponse);
    }

    @Override
    public BookResponseDto findById(UUID id) {
        return bookRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.BOOK_NOT_FOUND));
    }

    @Override
    public BookResponseDto save(BookRequestDto request) {
        if (!authorService.existsById(request.getAuthor())) {
            throw new EntityNotFoundException(ErrorMessages.AUTHOR_NOT_FOUND);
        }

        if (!publisherService.existsById(request.getPublisher())) {
            throw new EntityNotFoundException(ErrorMessages.PUBLISHER_NOT_FOUND);
        }

        if (bookRepository.existsByIsbn(request.getIsbn())) {
            throw new IsbnAlreadyExistsException();
        }

        if (request.getPublicationYear() > LocalDate.now().getYear()) {
            throw new PublicationYearTooNew();
        }

        return mapper.toResponse(
                bookRepository.save(mapper.toEntity(request))
        );
    }

    @Override
    public BookResponseDto update(UUID id, BookRequestDto request) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorMessages.BOOK_NOT_FOUND));

        if (!authorService.existsById(request.getAuthor())) {
            throw new EntityNotFoundException(ErrorMessages.AUTHOR_NOT_FOUND);
        }

        if (!publisherService.existsById(request.getPublisher())) {
            throw new EntityNotFoundException(ErrorMessages.PUBLISHER_NOT_FOUND);
        }

        if (bookRepository.existsByIsbnAndIdNot(request.getIsbn(), id)) {
            throw new IsbnAlreadyExistsException();
        }

        if (request.getPublicationYear() > LocalDate.now().getYear()) {
            throw new PublicationYearTooNew();
        }

        return mapper.toResponse(
                bookRepository.save(mapper.updateEntity(book, request))
        );
    }

    @Override
    public void deleteById(UUID id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException(ErrorMessages.BOOK_NOT_FOUND);
        }

        bookRepository.deleteById(id);
    }

    @Override
    public BookResponseDto findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.BOOK_NOT_FOUND));
    }

    @Override
    public List<BookResponseDto> findByTitle(String title) {
        return mapper.toResponseList(bookRepository.findByTitleContainingIgnoreCase(title));
    }
}
