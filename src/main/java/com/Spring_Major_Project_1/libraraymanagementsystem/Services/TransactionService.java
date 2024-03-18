package com.Spring_Major_Project_1.libraraymanagementsystem.Services;

import com.Spring_Major_Project_1.libraraymanagementsystem.Entities.Book;
import com.Spring_Major_Project_1.libraraymanagementsystem.Entities.LibraryCard;
import com.Spring_Major_Project_1.libraraymanagementsystem.Entities.Transaction;
import com.Spring_Major_Project_1.libraraymanagementsystem.Enums.TransactionStatus;
import com.Spring_Major_Project_1.libraraymanagementsystem.Repositories.BookRepository;
import com.Spring_Major_Project_1.libraraymanagementsystem.Repositories.CardRepository;
import com.Spring_Major_Project_1.libraraymanagementsystem.Repositories.TransactionRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service

public class TransactionService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public static final Integer MAX_NO_OF_ISSUED_BOOK = 3;

    public static final Integer FINE_PER_DAY = 5;

    public String issueBook(Integer bookId,Integer cardId) throws Exception{
//        Find book and card from repositories
//        Book book = bookRepository.findById(bookId).get();
//        LibraryCard card = cardRepository.findById(cardId).get();


//        Book and LibraryCard should be valid
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isEmpty()){
            throw new Exception("bookId entered is incorrect");
        }
        Book book = bookOptional.get();

        Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(cardId);
        if(optionalLibraryCard.isEmpty()){
            throw new Exception("cardId entered is incorrect");
        }
        LibraryCard card = optionalLibraryCard.get();



//        Create the transaction object
        Transaction transaction = new Transaction();
        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setTransactionStatus(TransactionStatus.PENDING);


//        Book should not be already issued
        if(book.getIsIssued()){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transactionRepository.save(transaction);
            return "Book is already issued to cardId " + card.getCardNo();
        }

//        Limit of card has exceeded
        if(card.getNoOfBooksIssued() > MAX_NO_OF_ISSUED_BOOK){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transactionRepository.save(transaction);
            return "Max limit of this card has exceeded";
        }

//        Check for if the card has expired its validity
        Long timeInMsOfCardValidity = card.getValidity().getTime();
        Long currentTimeInMs = System.currentTimeMillis();
        if(timeInMsOfCardValidity < currentTimeInMs){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transactionRepository.save(transaction);
            return "The card has been expired";
        }

//      Happy Flow !!!

        transaction.setTransactionStatus(TransactionStatus.ISSUED);

//        Set the relevant attributes of transactionObject
        book.setIsIssued(true);
        card.setNoOfBooksIssued(card.getNoOfBooksIssued()+1);

//        Change the attributes of Card and Book Entity
//        Rule is whatever has been modified : should be saved !!
        cardRepository.save(card);
        bookRepository.save(book);
        transaction = transactionRepository.save(transaction);

        return "The transaction has been completed with transactionId " + transaction.getTransactionId();
    }


    public String returnBook(Integer cardId, Integer bookId){
//        I need to find out the transaction : with that bookId,cardId and ISSUED status
        Book book = bookRepository.findById(bookId).get();
        LibraryCard card = cardRepository.findById(cardId).get();

        Transaction transaction = transactionRepository.findTransactionByBookAndCardAndTransactionStatus(book,card,TransactionStatus.ISSUED);

//        Fine amount to be calculated :
        Long timeDifferenceMs = System.currentTimeMillis() - transaction.getIssueDate().getTime();

//        This time is in Ms and we need to convert it in days
        Long days = TimeUnit.DAYS.convert(timeDifferenceMs,TimeUnit.MILLISECONDS);

        Integer fineAmount = 0;
        if(days > 15){
            fineAmount = Math.toIntExact((days-15)*FINE_PER_DAY);  // Type-Casting of Integer is done by Math.toIntExact()
        }


//        Save Transaction
        transaction.setFineAmount(fineAmount);
        transaction.setTransactionStatus(TransactionStatus.COMPLETED);
        transaction.setReturnDate(new Date());
        book.setIsIssued(Boolean.FALSE);
        card.setNoOfBooksIssued(card.getNoOfBooksIssued()-1);

        transactionRepository.save(transaction);
        cardRepository.save(card);
        bookRepository.save(book);

        return "The Book ha been successfully returned";
    }
}
