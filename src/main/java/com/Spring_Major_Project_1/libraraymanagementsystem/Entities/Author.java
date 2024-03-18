package com.Spring_Major_Project_1.libraraymanagementsystem.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "author_info")
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;
    private String name;
    private Integer age;
    private String emailId;
    private double rating;
    private int noOfBooks;
}
