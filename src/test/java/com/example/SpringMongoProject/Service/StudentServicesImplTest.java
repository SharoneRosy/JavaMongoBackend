package com.example.SpringMongoProject.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.SpringMongoProject.Entity.Student;
import com.example.SpringMongoProject.Repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {StudentServicesImpl.class})
@ExtendWith(SpringExtension.class)
class StudentServicesImplTest {
    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private StudentServicesImpl studentServicesImpl;


    @Test
    void testSaveorUpdate() {
        // Arrange
        Student student = new Student();
        student.setMobile("Mobile");
        student.setStudentaddress("42 Main St");
        student.setStudentname("Studentname");
        student.set_id(" id");
        when(studentRepository.save(Mockito.<Student>any())).thenReturn(student);

        Student students = new Student();
        students.setMobile("Mobile");
        students.setStudentaddress("42 Main St");
        students.setStudentname("Studentname");
        students.set_id(" id");



        // Act
        studentServicesImpl.saveorUpdate(students);

        // Assert that nothing has changed
        verify(studentRepository).save(Mockito.<Student>any());
        assertEquals("42 Main St", students.getStudentaddress());
        assertEquals("Mobile", students.getMobile());
        assertEquals("Studentname", students.getStudentname());
    }


    @Test
    void testListAll() {
        // Arrange
        ArrayList<Student> studentList = new ArrayList<>();
        when(studentRepository.findAll()).thenReturn(studentList);

        // Act
        Iterable<Student> actualListAllResult = studentServicesImpl.listAll();

        // Assert
        verify(studentRepository).findAll();
        assertTrue(((Collection<Student>) actualListAllResult).isEmpty());
        assertSame(studentList, actualListAllResult);
    }


    @Test
    void testDeleteStudent() {
        // Arrange
        doNothing().when(studentRepository).deleteById(Mockito.<String>any());

        // Act
        studentServicesImpl.deleteStudent("42");

        // Assert that nothing has changed
        verify(studentRepository).deleteById(Mockito.<String>any());
    }


    @Test
    void testGetStudentById() {
        // Arrange
        Student student = new Student();
        student.setMobile("Mobile");
        student.setStudentaddress("42 Main St");
        student.setStudentname("Studentname");
        student.set_id(" id");
        Optional<Student> ofResult = Optional.of(student);
        when(studentRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        Student actualStudentById = studentServicesImpl.getStudentById(" id");

        // Assert
        verify(studentRepository).findById(Mockito.<String>any());
        assertSame(student, actualStudentById);
    }
}
