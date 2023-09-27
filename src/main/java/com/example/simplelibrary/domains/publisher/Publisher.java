package com.example.simplelibrary.domains.publisher;

import com.example.simplelibrary.domains.book.Book;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "publishers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    private String address;

    @Column(nullable = false, length = 16)
    private String phoneNumber;

    @OneToMany(mappedBy = "publisher", orphanRemoval = true)
    private List<Book> books;
}
