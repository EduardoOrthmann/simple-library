package com.example.simplelibrary.exceptions;

import com.example.simplelibrary.utils.ErrorMessages;

public class PublicationYearTooNew extends RuntimeException {
    public PublicationYearTooNew() {
        super(ErrorMessages.BOOK_PUBLICATION_YEAR_TOO_NEW);
    }
}
