package com.example.simplelibrary.domains.author;

import com.example.simplelibrary.domains.book.Book;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birthDate;

    private String nationality;

    @OneToMany(mappedBy = "author", orphanRemoval = true)
    private List<Book> books;
}
