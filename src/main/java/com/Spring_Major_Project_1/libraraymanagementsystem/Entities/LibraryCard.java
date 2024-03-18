package com.Spring_Major_Project_1.libraraymanagementsystem.Entities;

import com.Spring_Major_Project_1.libraraymanagementsystem.Enums.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "card_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardNo;
    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;
    private int noOfBooksIssued;
    private Date validity;

//    This will directly create Foreign Key in the card_info
    @JoinColumn // This tells that a new column is adding the Library Card
    @OneToOne // Mapping
    private Student student;
}
