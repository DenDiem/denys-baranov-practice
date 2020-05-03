package com.example.demo.lab8;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class BookDto {
    private String title;
    private String isbn;
    private String author;


}
