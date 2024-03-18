package com.Spring_Major_Project_1.libraraymanagementsystem.Services;

import com.Spring_Major_Project_1.libraraymanagementsystem.Entities.Author;
import com.Spring_Major_Project_1.libraraymanagementsystem.Entities.Book;
import com.Spring_Major_Project_1.libraraymanagementsystem.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    public String addAuthor(Author author) {
        author.setNoOfBooks(0);
        authorRepository.save(author);
        return "Author has been saved to DB";
    }

    public Author getAuthorWithMaxBooks(){
        List<Author> authorList = authorRepository.findAll();
        Author ansAuthor = null;
        int maxBooks = 0;
        for(Author author : authorList){
            if(author.getNoOfBooks() > maxBooks){
                maxBooks = author.getNoOfBooks();
                ansAuthor = author;
            }
        }
        return ansAuthor;
    }
}
