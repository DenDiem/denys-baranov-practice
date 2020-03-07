package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.*;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BookService {

    private final EntityManager entityManager;
    @Transactional
    public BookEntity createBook(String title, String isbn, String author) {
        BookEntity book = new BookEntity();
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setAuthor(author);
        return entityManager.merge(book);
    }
    @Transactional
    public List<BookEntity> findAllBooks() {
        return entityManager.createQuery("FROM  BookEntity ", BookEntity.class).getResultList();
    }
    public BookEntity findByID(int id) {
        return entityManager.createNamedQuery(BookEntity.FIND_BY_ID, BookEntity.class).setParameter("id", id).getSingleResult();
    }
    @Transactional
    public List<BookEntity> serchByLetter(String letter) {
        List<BookEntity> temp = findAllBooks();
        List<BookEntity> result = new ArrayList<>();
        for (BookEntity bookEntity:temp) {
            if (bookEntity.getAuthor().contains(letter)||bookEntity.getIsbn().contains(letter)||bookEntity.getTitle().contains(letter))
                result.add(bookEntity);
        }
        return result;
    }
}