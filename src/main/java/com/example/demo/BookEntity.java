package com.example.demo;


import javax.persistence.*;

import lombok.*;
@Entity
@NamedQueries(
        {
                @NamedQuery(query = "SELECT u FROM UserEntity u WHERE u.firstName = :firstName", name = UserEntity.FIND_BY_NAME)
        })
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookEntity
{
    public static final String FIND_BY_NAME = "UserEntity.FIND_BY_NAME";
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "author")
    private String author;


}