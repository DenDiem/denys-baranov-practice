package com.example.demo;


import com.example.demo.lab8.BookDto;
import com.example.demo.lab8.BookResponceDto;
import com.example.demo.lab8.SearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;


@RequiredArgsConstructor
@Controller
public class IndexController {
    private final  List<Book> book_reprository;
    @Autowired
    private ApplicationContext appContext;
    @RequestMapping({"/", ""})
    public String index() {
        return "add-books-page";
    }

    @RequestMapping(value = "/searchFunction", method = RequestMethod.POST)
    public String search(@RequestParam(value = "search", required = false) String letter, Model model) {
        List<BookEntity> searchResults = null;
        BookService bookService = appContext.getBean(BookService.class);
        System.out.println(letter);
        try {
            searchResults = bookService.findAllWhereIsbnLikeOrTitleLike(letter,letter);

        } catch (Exception ex) {
            //here you should handle unexpected errors
            //...
            //throw ex;
        }
        model.addAttribute("books", searchResults);
        return "index-search";

    }
    @RequestMapping(value = "/add-book", method = RequestMethod.POST)
    public String booksList(@ModelAttribute Book bookModel,Model model) {

        model.addAttribute("bookModel",bookModel);

        BookService bookService = appContext.getBean(BookService.class);
        bookService.createBook(bookModel.getTitle(),bookModel.getIsbn(),bookModel.getAuthor());
              System.out.println("ID = " + bookService.findByID(1));


        return "redirect:books-list";
    }
    @RequestMapping(value = "/books-list", method = RequestMethod.GET)
    public String booksList(Model model) {
        BookService bookService = appContext.getBean(BookService.class);
        model.addAttribute("books", bookService.findAllBooks());


        return "index";
    }
    @RequestMapping(value ="book/{id}", method = RequestMethod.GET)
    public String bookPage(@PathVariable("id") int id,Model model) {
        BookService bookService = appContext.getBean(BookService.class);
        BookEntity bookEntity =  bookService.findByID(id);
        model.addAttribute("book",bookEntity);
        return "book-one-page";
    }
    @RequestMapping("/lab8")
    public String indexlab8() {
        return "indexlab8";
    }

    @RequestMapping(value = "/add-book-ajax", method = RequestMethod.POST)
    public ResponseEntity<BookResponceDto> addnewBookAjax(
            @RequestBody final BookDto bookDto
    ) {
        System.out.println("Accept login request");
        System.out.println(bookDto);
        BookService bookService = appContext.getBean(BookService.class);
        bookService.createBook(bookDto.getTitle(),bookDto.getIsbn(),bookDto.getAuthor());
        System.out.println("ID = " + bookService.findByID(1));

        return ResponseEntity.ok(BookResponceDto.of(bookService.findAllBooks(),"success"));

    }
    @RequestMapping(value = "/ajax-search", method = RequestMethod.POST)
    public ResponseEntity<BookResponceDto> ajaxSearch(
            @RequestBody final SearchDto searchDto
    ) {
        List<BookEntity> searchResults = null;
        BookService bookService = appContext.getBean(BookService.class);
        String letter = searchDto.getLetter();
        System.out.println(searchDto);
        try {
            searchResults = bookService.findAllWhereIsbnLikeOrTitleLike(letter,letter);

        } catch (Exception ex) {
            //here you should handle unexpected errors
            //...
            //throw ex;
        }
        return ResponseEntity.ok(BookResponceDto.of(searchResults,"success"));
    }
}
