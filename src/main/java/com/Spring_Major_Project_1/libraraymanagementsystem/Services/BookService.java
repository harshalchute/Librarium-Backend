package com.Spring_Major_Project_1.libraraymanagementsystem.Services;

import com.Spring_Major_Project_1.libraraymanagementsystem.Entities.Author;
import com.Spring_Major_Project_1.libraraymanagementsystem.Entities.Book;
import com.Spring_Major_Project_1.libraraymanagementsystem.Exceptions.InvalidPageCountException;
import com.Spring_Major_Project_1.libraraymanagementsystem.Repositories.AuthorRepository;
import com.Spring_Major_Project_1.libraraymanagementsystem.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;
    public String addBook(Book book) throws Exception{
        if(book.getNoOfPages() <= 0){
            throw new InvalidPageCountException("Page Count Entered is Incorrect");
        }
        book.setIsIssued(Boolean.FALSE);
        bookRepository.save(book);
        return "Book has been added to DB";
    }

    public String associateBookAndAuthor(Integer bookId,Integer authorId) throws Exception{
//        Book book = bookRepository.findById(bookId).get();
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isEmpty()){
//        throw an Exception that book is not found
            throw new Exception("bookId entered is Incorrect");
        }
        Book book = bookOptional.get();

//        Author author = authorRepository.findById(authorId).get();
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if(authorOptional.isEmpty()){
//        throw an Exception that author is not found
            throw new Exception("authorId entered is Incorrect");
        }
        Author author = authorOptional.get();

//        Associate book and author !!
        book.setAuthor(author);
        author.setNoOfBooks(author.getNoOfBooks()+1);
        bookRepository.save(book);
        authorRepository.save(author);

        return "Book and Author have been Associated";
    }


    public List<Book> findBooksByAuthor(Integer authorId){
        List<Book> allBooks = bookRepository.findAll();
//        I need to iterate where : Book.getAuthor.getId is matching
        List<Book> ansList = new ArrayList<>();
        for(Book book : allBooks){
            if(book.getAuthor().getAuthorId().equals(authorId)){
                ansList.add(book);
            }
        }
        return ansList;
    }
}