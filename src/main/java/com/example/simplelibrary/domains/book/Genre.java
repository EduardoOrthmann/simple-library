package com.example.simplelibrary.domains.book;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Genre {
    SCIENCE_FICTION("Fição Científica"),
    SATIRE("Sátira"),
    DRAMA("Drama"),
    ACTION_AND_ADVENTURE("Ação e Aventura"),
    ROMANCE("Romance"),
    MYSTERY("Mistério"),
    HORROR("Terror"),
    SELF_HELP("Autoajuda"),
    GUIDE("Guia"),
    TRAVEL("Viagem"),
    CHILDREN("Crianças"),
    RELIGION_SPIRITUALITY_AND_NEW_AGES("Religião, Espiritualidade e Novas Eras"),
    SCIENCE("Ciência"),
    HISTORY("História"),
    MATH("Matemática"),
    ANTHOLOGY("Antologia"),
    POETRY("Poesia"),
    ENCYCLOPEDIAS("Enciclopédias"),
    DICTIONARIES("Dicionários"),
    COMICS("Quadrinhos"),
    ART("Arte"),
    COOKBOOKS("Livros de receitas"),
    DIARIES("Diários"),
    JOURNALS("Jornais"),
    PRAYER_BOOKS("Livros de orações"),
    SERIES("Séries"),
    TRILOGY("Trilogia"),
    BIOGRAPHIES("Biografias"),
    AUTOBIOGRAPHIES("Autobiografias"),
    FANTASY("Fantasia");

    private final String genreName;
}
