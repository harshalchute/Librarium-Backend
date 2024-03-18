package com.Spring_Major_Project_1.libraraymanagementsystem.Entities;

import com.Spring_Major_Project_1.libraraymanagementsystem.Enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table(name = "transaction_info")
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String transactionId;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    @CreationTimestamp
    private Date issueDate;

    private Date returnDate;

    private Integer fineAmount;

    @JoinColumn
    @ManyToOne
    private LibraryCard card;

    @JoinColumn
    @ManyToOne
    private Book book;
}
