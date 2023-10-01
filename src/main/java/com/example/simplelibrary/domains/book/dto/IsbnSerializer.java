package com.example.simplelibrary.domains.book.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class IsbnSerializer extends JsonSerializer<String> {
    private static final String ISBN_REGEX = "^(\\d{3})-(\\d)-(\\d{2})-(\\d{6})-(\\d)$";

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(s.replaceAll(ISBN_REGEX, "$1-$2-$3-$4-$5"));
    }
}
