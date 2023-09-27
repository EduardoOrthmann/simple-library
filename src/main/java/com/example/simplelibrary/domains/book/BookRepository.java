package com.example.simplelibrary.domains.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    boolean existsByIsbn(String isbn);

    boolean existsByIsbnAndIdNot(String isbn, UUID id);
}
