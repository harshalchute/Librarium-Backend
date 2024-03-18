package com.Spring_Major_Project_1.libraraymanagementsystem.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity    // This is the schema of how Student table look like.
@Table(name = "student_info")
@Getter
@Setter
@NoArgsConstructor // It is like default constructor
@AllArgsConstructor // It is like constructor having all arguments
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // It Auto generates the Id
    private int rollNo; // This attribute will act as Primary Key

    @Column(nullable = false)
    private String name;

    private String branch;

    @Column(nullable = false)
    private Double cgpa;

    @Column(unique = true)
    private String emailId;
}