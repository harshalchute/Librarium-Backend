package com.Spring_Major_Project_1.libraraymanagementsystem.Controllers;

import com.Spring_Major_Project_1.libraraymanagementsystem.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("card")
public class CardController {
    @Autowired
    private CardService cardService;
    @PostMapping("/generateCard")
    public ResponseEntity addCard(){
        String result = cardService.generateCard();
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PutMapping("/associateCardAndStudent")
    public ResponseEntity associateCardAndStudent(@RequestParam ("cardId")Integer cardId,
                                                  @RequestParam ("studentId")Integer studentId){

        try{
            String result = cardService.associateCardAndStudent(cardId,studentId);
            return new ResponseEntity(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
