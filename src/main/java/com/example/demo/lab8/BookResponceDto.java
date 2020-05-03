package com.example.demo.lab8;

import com.example.demo.BookEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor(staticName = "of")
public class BookResponceDto {
    private final List<BookEntity> bookEntities;
    private final String message;
}
