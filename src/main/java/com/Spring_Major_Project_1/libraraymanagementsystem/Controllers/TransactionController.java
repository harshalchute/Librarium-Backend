package com.Spring_Major_Project_1.libraraymanagementsystem.Controllers;

import com.Spring_Major_Project_1.libraraymanagementsystem.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PutMapping("/issueBook")
    public String issueBook(@RequestParam("bookId")Integer bookId,@RequestParam("cardId")Integer cardId){
        try {
            String result = transactionService.issueBook(bookId,cardId);
            return result;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @PutMapping("/returnBook")
    public String returnBook(@RequestParam("cardId")Integer cardId,
                             @RequestParam("bookId")Integer bookId){

        String result = transactionService.returnBook(cardId, bookId);
        return result;
    }

}
