package com.example.simplelibrary.exceptions;

import com.example.simplelibrary.utils.ErrorMessages;

public class PublisherNameAlreadyExists extends RuntimeException {
    public PublisherNameAlreadyExists() {
        super(ErrorMessages.PUBLISHER_NAME_EXISTS);
    }
}
