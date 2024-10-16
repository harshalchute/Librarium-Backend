# Librarium Backend
## Overview : 

Librarium Backend is a backend system designed to handle the core operations of a library, including managing students, books, authors, library cards, and transactions. It efficiently supports the library’s fundamental tasks such as book issuing, return tracking, and fine calculation.

### Key Features :
      Student Management: Keep track of students and their library cards.
      Book Management: Manage books, authors, and availability.
      Transaction Handling: Issue and return books, track transactions, and manage fines.
      
### Tech Stack :
      Java with Spring Boot
      MySQL for database
      Hibernate for ORM [Object-Relational Mapping]
      
### Database Structure :
      The system revolves around five key tables:
        -student_info: Stores student data.
        -card_info: Manages library card information.
        -author_info: Keeps author details.
        -book_info: Tracks books and their details.
        -transaction_info: Handles borrowing and returning of books.

### Setup Instructions :
    Clone the repository:
      git clone https://github.com/username/librarium-backend.git
      
    Set up the MySQL database using the provided schema.
    
    Configure the database connection in application.properties.
    
    Build and run the project:
      mvn spring-boot:run
