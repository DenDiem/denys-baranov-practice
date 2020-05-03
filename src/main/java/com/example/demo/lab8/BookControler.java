package com.example.demo.lab8;

import com.example.demo.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class BookControler {

    @ResponseBody
    @GetMapping("/books")
    public List<Book> getAllUsersOk() {
        return Arrays.asList(
                new Book("123","123","123"),
                new Book("1231233","sdasd123","asd123")
        );
    }
}
