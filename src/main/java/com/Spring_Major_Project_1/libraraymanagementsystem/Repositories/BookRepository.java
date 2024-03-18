package com.Spring_Major_Project_1.libraraymanagementsystem.Repositories;

import com.Spring_Major_Project_1.libraraymanagementsystem.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
