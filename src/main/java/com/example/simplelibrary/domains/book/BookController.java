package com.example.simplelibrary.domains.book;

import com.example.simplelibrary.domains.book.dto.BookRequestDto;
import com.example.simplelibrary.domains.book.dto.BookResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookResponseDto>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @PostMapping
    public ResponseEntity<BookResponseDto> save(@RequestBody @Valid BookRequestDto book) {
        return ResponseEntity.ok(bookService.save(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> update(@PathVariable UUID id, @RequestBody @Valid BookRequestDto book) {
        return ResponseEntity.ok(bookService.update(id, book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
