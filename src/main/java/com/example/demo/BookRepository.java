package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Integer>
{
   // List<BookEntity> findAllWhereIsbn(String contains);
    //Optional<BookEntity> findByIsbn(String isbn);
   // boolean existsByIsbn(String isbn);
    //List<BookEntity> findAllWhereIsbnLikeOrTitleLike(String isbn,String title);
   List<BookEntity> findAllByIsbnLikeOrTitleLike(String isbn, String title);
}