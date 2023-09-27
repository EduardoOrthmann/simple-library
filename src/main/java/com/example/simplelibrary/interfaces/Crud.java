package com.example.simplelibrary.interfaces;

import java.util.List;

public interface Crud <T, U, V> {
    List<U> findAll();
    U findById(V id);
    U save(T request);
    U update(V id, T request);
    void deleteById(V id);
}
