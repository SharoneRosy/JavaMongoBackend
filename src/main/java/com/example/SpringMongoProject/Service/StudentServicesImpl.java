package com.example.SpringMongoProject.Service;

import com.example.SpringMongoProject.Entity.Student;
import com.example.SpringMongoProject.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class StudentServicesImpl implements StudentServices{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void saveorUpdate(Student students) {
        studentRepository.save(students);
    }

    @Override
    public Iterable<Student> listAll() {
        return this.studentRepository.findAll();
    }

    @Override
    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student getStudentById(String _id) {
        return studentRepository.findById(_id).get();
    }
}
