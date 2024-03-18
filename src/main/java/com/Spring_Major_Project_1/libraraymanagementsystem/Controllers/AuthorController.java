package com.Spring_Major_Project_1.libraraymanagementsystem.Controllers;

import com.Spring_Major_Project_1.libraraymanagementsystem.Entities.Author;
import com.Spring_Major_Project_1.libraraymanagementsystem.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @PostMapping("/addAuthor")
    public String addAuthor(@RequestBody Author author){
        String result = authorService.addAuthor(author);
        return result;
    }

    @GetMapping("/getAuthorWithMaxBooks")
    public Author getAuthor(){
        Author ansAuthor = authorService.getAuthorWithMaxBooks();
        return ansAuthor;
    }
}
