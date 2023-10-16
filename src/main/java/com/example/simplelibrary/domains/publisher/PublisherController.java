package com.example.simplelibrary.domains.publisher;

import com.example.simplelibrary.domains.book.dto.BookResponseDto;
import com.example.simplelibrary.domains.publisher.dto.PublisherNameDto;
import com.example.simplelibrary.domains.publisher.dto.PublisherRequestDto;
import com.example.simplelibrary.domains.publisher.dto.PublisherResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/publisher")
@RequiredArgsConstructor
@Tag(name = "Publisher")
public class PublisherController {
    private final PublisherService publisherService;

    @Operation(summary = "Find all publishers")
    @GetMapping
    public ResponseEntity<List<PublisherResponseDto>> findAll() {
        return ResponseEntity.ok(publisherService.findAll());
    }

    @Operation(summary = "Find publisher by id")
    @GetMapping("/{id}")
    public ResponseEntity<PublisherResponseDto> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(publisherService.findById(id));
    }

    @Operation(summary = "Find books by publisher id")
    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookResponseDto>> findBooksByPublisherId(@PathVariable UUID id) {
        return ResponseEntity.ok(publisherService.findBooksByPublisherId(id));
    }

    @Operation(summary = "Find all publisher names")
    @GetMapping("/names")
    public ResponseEntity<List<PublisherNameDto>> findAllNames() {
        return ResponseEntity.ok(publisherService.findAllNames());
    }

    @Operation(summary = "Save publisher")
    @PostMapping
    public ResponseEntity<PublisherResponseDto> save(@RequestBody @Valid PublisherRequestDto publisher) {
        return ResponseEntity.ok(publisherService.save(publisher));
    }

    @Operation(summary = "Update publisher by id")
    @PutMapping("/{id}")
    public ResponseEntity<PublisherResponseDto> update(@PathVariable UUID id, @RequestBody @Valid PublisherRequestDto publisher) {
        return ResponseEntity.ok(publisherService.update(id, publisher));
    }

    @Operation(summary = "Delete publisher by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        publisherService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
