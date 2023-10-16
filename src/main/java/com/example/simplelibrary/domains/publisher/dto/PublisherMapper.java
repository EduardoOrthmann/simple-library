package com.example.simplelibrary.domains.publisher.dto;

import com.example.simplelibrary.interfaces.Mapper;
import com.example.simplelibrary.domains.publisher.Publisher;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class PublisherMapper implements Mapper<Publisher, PublisherRequestDto, PublisherResponseDto> {
    @Override
    public PublisherResponseDto toResponse(Publisher publisher) {
        return PublisherResponseDto.builder()
                .id(publisher.getId())
                .name(publisher.getName())
                .address(publisher.getAddress())
                .phoneNumber(publisher.getPhoneNumber())
                .build();
    }

    @Override
    public Publisher toEntity(PublisherRequestDto request) {
        return Publisher.builder()
                .name(request.getName())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }

    @Override
    public List<PublisherResponseDto> toResponseList(Collection<Publisher> list) {
        return list.stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public Publisher updateEntity(Publisher publisher, PublisherRequestDto request) {
        publisher.setName(request.getName());
        publisher.setAddress(request.getAddress());
        publisher.setPhoneNumber(request.getPhoneNumber());

        return publisher;
    }

    public PublisherNameDto toName(Publisher publisher) {
        return PublisherNameDto.builder()
                .id(publisher.getId())
                .name(publisher.getName())
                .build();
    }

    public List<PublisherNameDto> toNameList(Collection<Publisher> list) {
        return list.stream()
                .map(this::toName)
                .toList();
    }
}
