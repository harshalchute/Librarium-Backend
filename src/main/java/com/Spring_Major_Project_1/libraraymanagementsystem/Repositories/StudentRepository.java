package com.Spring_Major_Project_1.libraraymanagementsystem.Repositories;

import com.Spring_Major_Project_1.libraraymanagementsystem.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// JpaRepository : It contains all the major crud operation methods
@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>{
//    Way 2 of for communication with JPA : Where Jpa will automatically create a query for us
List<Student> findStudentByBranchAndCgpaGreaterThan(String branch,double cgpa);

    Student findStudentByEmailId(String emailId);


}
