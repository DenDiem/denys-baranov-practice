package com.example.demo;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;


@RequiredArgsConstructor
@Controller
public class IndexController {
    private final  List<Book> book_reprository;

    @RequestMapping({"/", ""})
    public String index() {
        return "add-books-page";
    }

    @RequestMapping(value = "/add-book", method = RequestMethod.POST)
    public String booksList(@ModelAttribute Book bookModel,Model model) {

        model.addAttribute("bookModel",bookModel);

        book_reprository.add(new Book(bookModel.getTitle(),bookModel.getIsbn(),bookModel.getAuthor()));
        System.out.println(Arrays.toString(book_reprository.toArray()));
        return "redirect:books-list";
    }
    @RequestMapping(value = "/books-list", method = RequestMethod.GET)
    public String booksList(Model model) {
        model.addAttribute("books", book_reprository);
        return "index";
    }

}
