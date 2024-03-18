package com.Spring_Major_Project_1.libraraymanagementsystem.Services;

import com.Spring_Major_Project_1.libraraymanagementsystem.Entities.Student;
import com.Spring_Major_Project_1.libraraymanagementsystem.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public String addStudent(Student student){
        studentRepository.save(student);
        return "Student has been Saved to DB";
    }

    public List<Student> findStudents(String branch, double cgpa){
        List<Student> students = studentRepository.findStudentByBranchAndCgpaGreaterThan(branch, cgpa);
        return students;
    }

}
