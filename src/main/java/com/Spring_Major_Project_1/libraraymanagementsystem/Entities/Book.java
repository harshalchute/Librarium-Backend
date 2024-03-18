package com.Spring_Major_Project_1.libraraymanagementsystem.Entities;

import com.Spring_Major_Project_1.libraraymanagementsystem.Enums.Genre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "book_info")
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(unique = true)
    private String title;

    private Integer noOfPages;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    private Integer price;

    private Boolean isIssued;

    @JoinColumn
    @ManyToOne
    private Author author;
}
