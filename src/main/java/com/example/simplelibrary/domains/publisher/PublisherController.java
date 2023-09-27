package com.example.simplelibrary.domains.publisher;

import com.example.simplelibrary.domains.book.dto.BookResponseDto;
import com.example.simplelibrary.domains.publisher.dto.PublisherRequestDto;
import com.example.simplelibrary.domains.publisher.dto.PublisherResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/publisher")
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;

    @GetMapping
    public ResponseEntity<List<PublisherResponseDto>> findAll() {
        return ResponseEntity.ok(publisherService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherResponseDto> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(publisherService.findById(id));
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookResponseDto>> findBooksByPublisherId(@PathVariable UUID id) {
        return ResponseEntity.ok(publisherService.findBooksByPublisherId(id));
    }

    @PostMapping
    public ResponseEntity<PublisherResponseDto> save(@RequestBody @Valid PublisherRequestDto publisher) {
        return ResponseEntity.ok(publisherService.save(publisher));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherResponseDto> update(@PathVariable UUID id, @RequestBody @Valid PublisherRequestDto publisher) {
        return ResponseEntity.ok(publisherService.update(id, publisher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        publisherService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
