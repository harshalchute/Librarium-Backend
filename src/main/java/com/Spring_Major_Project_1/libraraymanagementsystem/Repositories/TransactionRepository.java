package com.Spring_Major_Project_1.libraraymanagementsystem.Repositories;

import com.Spring_Major_Project_1.libraraymanagementsystem.Entities.Book;
import com.Spring_Major_Project_1.libraraymanagementsystem.Entities.LibraryCard;
import com.Spring_Major_Project_1.libraraymanagementsystem.Entities.Transaction;
import com.Spring_Major_Project_1.libraraymanagementsystem.Enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,String> {
    Transaction findTransactionByBookAndCardAndTransactionStatus(Book book, LibraryCard card, TransactionStatus transactionStatus);
}
