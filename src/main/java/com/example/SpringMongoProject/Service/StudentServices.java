package com.example.SpringMongoProject.Service;

import com.example.SpringMongoProject.Entity.Student;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface StudentServices {

    void saveorUpdate(Student students);

    Iterable<Student> listAll();

    void deleteStudent(String id);

    Student getStudentById(String id);
}
