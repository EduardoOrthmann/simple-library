package com.example.simplelibrary.domains.book;

import com.example.simplelibrary.domains.book.dto.BookRequestDto;
import com.example.simplelibrary.domains.book.dto.BookResponseDto;
import com.example.simplelibrary.domains.book.dto.GenreDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
@Tag(name = "Book")
public class BookController {
    private final BookService bookService;

    @Operation(summary = "Find all books")
    @GetMapping("/all")
    public ResponseEntity<List<BookResponseDto>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @Operation(summary = "Find all books with pagination")
    @GetMapping
    public ResponseEntity<Page<BookResponseDto>> findAllPageable(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy) {

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortBy));

        return ResponseEntity.ok(bookService.findAll(pageRequest));
    }

    @Operation(summary = "Find book by id")
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @Operation(summary = "Find book by isbn")
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookResponseDto> findByIsbn(@PathVariable String isbn) {
        return ResponseEntity.ok(bookService.findByIsbn(isbn));
    }

    @Operation(summary = "Find book by title")
    @GetMapping("/title")
    public ResponseEntity<List<BookResponseDto>> findByTitle(@RequestParam String title) {
        return ResponseEntity.ok(bookService.findByTitle(title));
    }

    @Operation(summary = "Find All Book Genres")
    @GetMapping("/genres")
    public ResponseEntity<List<GenreDto>> findAllGenres() {
        return ResponseEntity.ok(bookService.findAllGenres());
    }

    @Operation(summary = "Save book")
    @PostMapping
    public ResponseEntity<BookResponseDto> save(@RequestBody @Valid BookRequestDto book) {
        return ResponseEntity.ok(bookService.save(book));
    }

    @Operation(summary = "Update book by id")
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> update(@PathVariable UUID id, @RequestBody @Valid BookRequestDto book) {
        return ResponseEntity.ok(bookService.update(id, book));
    }

    @Operation(summary = "Delete book by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
