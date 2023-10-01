package com.example.simplelibrary.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessages {
    // Validation
    public final String VALIDATION_ERROR = "Erro de validação";
    public final String INVALID_PATH_PARAMETER = "Parâmetro de rota inválido";

    // Author
    public final String AUTHOR_NOT_FOUND = "Autor não encontrado";

    // Book
    public final String BOOK_NOT_FOUND = "Livro não encontrado";
    public final String BOOK_ISBN_EXISTS = "ISBN já cadastrado";
    public final String BOOK_PUBLICATION_YEAR_TOO_NEW = "Ano de publicação maior que o ano atual";

    // Publisher
    public final String PUBLISHER_NOT_FOUND = "Editora não encontrada";
    public final String PUBLISHER_NAME_EXISTS = "Nome da editora já cadastrado";
}
