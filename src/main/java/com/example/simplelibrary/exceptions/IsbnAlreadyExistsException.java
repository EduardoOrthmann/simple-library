package com.example.simplelibrary.exceptions;

import com.example.simplelibrary.utils.ErrorMessages;

public class IsbnAlreadyExistsException extends RuntimeException {
    public IsbnAlreadyExistsException(String message) {
        super(message);
    }

    public IsbnAlreadyExistsException() {
        super(ErrorMessages.BOOK_ISBN_EXISTS);
    }
}
