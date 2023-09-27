package com.example.simplelibrary.domains.publisher;

import com.example.simplelibrary.domains.book.dto.BookMapper;
import com.example.simplelibrary.domains.book.dto.BookResponseDto;
import com.example.simplelibrary.domains.publisher.dto.PublisherMapper;
import com.example.simplelibrary.domains.publisher.dto.PublisherRequestDto;
import com.example.simplelibrary.domains.publisher.dto.PublisherResponseDto;
import com.example.simplelibrary.exceptions.PublisherNameAlreadyExists;
import com.example.simplelibrary.utils.ErrorMessages;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;
    private final PublisherMapper mapper;
    private final BookMapper bookMapper;

    @Override
    public List<PublisherResponseDto> findAll() {
        return mapper.toResponseList(publisherRepository.findAll());
    }

    @Override
    public PublisherResponseDto findById(UUID id) {
        return publisherRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.PUBLISHER_NOT_FOUND));
    }

    @Override
    public PublisherResponseDto save(PublisherRequestDto request) {
        if (publisherRepository.existsByName(request.getName())) {
            throw new PublisherNameAlreadyExists();
        }

        return mapper.toResponse(
                publisherRepository.save(mapper.toEntity(request))
        );
    }

    @Override
    public PublisherResponseDto update(UUID id, PublisherRequestDto request) {
        Publisher publisher = publisherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorMessages.PUBLISHER_NOT_FOUND));

        if (publisherRepository.existsByNameAndIdNot(request.getName(), id)) {
            throw new PublisherNameAlreadyExists();
        }

        return mapper.toResponse(
                publisherRepository.save(mapper.updateEntity(publisher, request))
        );
    }

    @Override
    public void deleteById(UUID id) {
        if (!publisherRepository.existsById(id)) {
            throw new EntityNotFoundException(ErrorMessages.PUBLISHER_NOT_FOUND);
        }

        publisherRepository.deleteById(id);
    }

    @Override
    public List<BookResponseDto> findBooksByPublisherId(UUID id) {
        Publisher publisher = publisherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorMessages.PUBLISHER_NOT_FOUND));

        return bookMapper.toResponseList(publisher.getBooks());
    }

    @Override
    public boolean existsById(UUID id) {
        return publisherRepository.existsById(id);
    }
}
