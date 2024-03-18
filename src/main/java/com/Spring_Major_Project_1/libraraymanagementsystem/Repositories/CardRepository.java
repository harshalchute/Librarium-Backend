package com.Spring_Major_Project_1.libraraymanagementsystem.Repositories;

import com.Spring_Major_Project_1.libraraymanagementsystem.Entities.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<LibraryCard,Integer> {

}
