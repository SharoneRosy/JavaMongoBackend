package com.example.SpringMongoProject.Controller;

import com.example.SpringMongoProject.Entity.Student;
import com.example.SpringMongoProject.Repository.StudentRepository;
import com.example.SpringMongoProject.Service.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentServices studentServices;
    @PostMapping(value = "/create")
    public String saveStudent(@RequestBody Student students){

        studentServices.saveorUpdate(students);
        return  students.get_id();
    }

    @GetMapping(value = "/getAll")
    public Iterable<Student>getStudents(){

        return studentServices.listAll();
    }

    @PutMapping(value = "/edit/{id}")
    public Student update(@RequestBody Student student,@PathVariable(name = "id")String _id){
        student.set_id(_id);
        studentServices.saveorUpdate(student);
        return student;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable("id") String _id){
        studentServices.deleteStudent(_id);
    }

    @GetMapping("/search/{id}")
    public Student getStudentById(@PathVariable("id") String _id){

        return studentServices.getStudentById(_id);
    }



}
