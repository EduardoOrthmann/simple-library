CREATE TABLE authors
(
    id          UUID         NOT NULL,
    name        VARCHAR(255) NOT NULL,
    birth_date  DATE         NOT NULL,
    nationality VARCHAR(255),
    CONSTRAINT pk_authors PRIMARY KEY (id)
);

CREATE TABLE books
(
    id               UUID         NOT NULL,
    isbn             VARCHAR(13)  NOT NULL,
    title            VARCHAR(255) NOT NULL,
    genre            VARCHAR(255) NOT NULL,
    publication_year INTEGER      NOT NULL,
    quantity         INTEGER      NOT NULL,
    author_id        UUID         NOT NULL,
    publisher_id     UUID         NOT NULL,
    CONSTRAINT pk_books PRIMARY KEY (id)
);

CREATE TABLE publishers
(
    id           UUID         NOT NULL,
    name         VARCHAR(255) NOT NULL,
    address      VARCHAR(255),
    phone_number VARCHAR(16)  NOT NULL,
    CONSTRAINT pk_publishers PRIMARY KEY (id)
);

ALTER TABLE books
    ADD CONSTRAINT uc_books_isbn UNIQUE (isbn);

ALTER TABLE publishers
    ADD CONSTRAINT uc_publishers_name UNIQUE (name);

ALTER TABLE books
    ADD CONSTRAINT FK_BOOKS_ON_AUTHOR FOREIGN KEY (author_id) REFERENCES authors (id);

ALTER TABLE books
    ADD CONSTRAINT FK_BOOKS_ON_PUBLISHER FOREIGN KEY (publisher_id) REFERENCES publishers (id);