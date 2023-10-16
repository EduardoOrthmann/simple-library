package com.example.simplelibrary.domains.author;

import com.example.simplelibrary.domains.author.dto.AuthorNameDto;
import com.example.simplelibrary.domains.author.dto.AuthorRequestDto;
import com.example.simplelibrary.domains.author.dto.AuthorResponseDto;
import com.example.simplelibrary.domains.book.dto.BookResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
@Tag(name = "Author")
public class AuthorController {
    private final AuthorService authorService;

    @Operation(summary = "Find all authors")
    @GetMapping
    public ResponseEntity<List<AuthorResponseDto>> findAll() {
        return ResponseEntity.ok(authorService.findAll());
    }

    @Operation(summary = "Find author by id")
    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(authorService.findById(id));
    }

    @Operation(summary = "Find books by author id")
    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookResponseDto>> findBooksByAuthorId(@PathVariable UUID id) {
        return ResponseEntity.ok(authorService.findBooksByAuthorId(id));
    }

    @Operation(summary = "Find all author names")
    @GetMapping("/names")
    public ResponseEntity<List<AuthorNameDto>> findAllNames() {
        return ResponseEntity.ok(authorService.findAllNames());
    }

    @Operation(summary = "Save author")
    @PostMapping
    public ResponseEntity<AuthorResponseDto> save(@RequestBody @Valid AuthorRequestDto author) {
        return ResponseEntity.ok(authorService.save(author));
    }

    @Operation(summary = "Update author by id")
    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> update(@PathVariable UUID id, @RequestBody @Valid AuthorRequestDto author) {
        return ResponseEntity.ok(authorService.update(id, author));
    }

    @Operation(summary = "Delete author by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        authorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
