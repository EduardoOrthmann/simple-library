package com.example.simplelibrary.domains.publisher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, UUID> {
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, UUID id);
}
