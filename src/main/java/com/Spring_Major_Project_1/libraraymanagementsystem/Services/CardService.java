package com.Spring_Major_Project_1.libraraymanagementsystem.Services;

import com.Spring_Major_Project_1.libraraymanagementsystem.Enums.CardStatus;
import com.Spring_Major_Project_1.libraraymanagementsystem.Entities.LibraryCard;
import com.Spring_Major_Project_1.libraraymanagementsystem.Entities.Student;
import com.Spring_Major_Project_1.libraraymanagementsystem.Repositories.CardRepository;
import com.Spring_Major_Project_1.libraraymanagementsystem.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private StudentRepository studentRepository;

    public String generateCard(){
//      I want to save some cards to the DB
        LibraryCard card = new LibraryCard(); // This card will go to the DB and save it
        card.setCardStatus(CardStatus.NEW);
        card.setNoOfBooksIssued(0);
        Date expiryDate = new Date(128,6,1);
//        LocalDate expiryDate = LocalDate.of(2028,6,1);
        card.setValidity(expiryDate);
        card = cardRepository.save(card);
        return "The card has been Generated with CardId : " + card.getCardNo();
    }

    public String associateCardAndStudent(Integer cardId,Integer studentId) throws Exception{
        LibraryCard libraryCard = cardRepository.findById(cardId).get();
        Student student = studentRepository.findById(studentId).get();

        if(libraryCard.getCardStatus() != CardStatus.NEW){
            throw new Exception("Card is already associated with another student");
        }
        libraryCard.setCardStatus(CardStatus.ISSUED);
        libraryCard.setStudent(student); // Indirectly setting the Foreign Key in Library Card table
//        but bcoz we are playing with entities ,so we will have to set as per the entity.

        cardRepository.save(libraryCard);
        return "The card and student has been associated";
    }
}