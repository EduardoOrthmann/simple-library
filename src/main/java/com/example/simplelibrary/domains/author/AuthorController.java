package com.example.simplelibrary.domains.author;

import com.example.simplelibrary.domains.author.dto.AuthorRequestDto;
import com.example.simplelibrary.domains.author.dto.AuthorResponseDto;
import com.example.simplelibrary.domains.book.dto.BookResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorResponseDto>> findAll() {
        return ResponseEntity.ok(authorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(authorService.findById(id));
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookResponseDto>> findBooksByAuthorId(@PathVariable UUID id) {
        return ResponseEntity.ok(authorService.findBooksByAuthorId(id));
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDto> save(@RequestBody @Valid AuthorRequestDto author) {
        return ResponseEntity.ok(authorService.save(author));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> update(@PathVariable UUID id, @RequestBody @Valid AuthorRequestDto author) {
        return ResponseEntity.ok(authorService.update(id, author));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        authorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
