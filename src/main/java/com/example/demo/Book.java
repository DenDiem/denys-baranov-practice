package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class Book {
    private String title;
    private String isbn;
    private String author;

    @Override
    public String toString() {
        return title +  " " + isbn +  " " + author;
    }
}
