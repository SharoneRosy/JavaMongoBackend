package com.example.SpringMongoProject.Entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class StudentTest {



    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        Student actualStudent = new Student();
        actualStudent.setMobile("Mobile");
        actualStudent.setStudentaddress("42 Main St");
        actualStudent.setStudentname("Studentname");
        actualStudent.set_id(" id");
        String actualToStringResult = actualStudent.toString();
        String actualMobile = actualStudent.getMobile();
        String actualStudentaddress = actualStudent.getStudentaddress();

        // Assert that nothing has changed
        assertEquals("42 Main St", actualStudentaddress);
        assertEquals("Mobile", actualMobile);
        assertEquals("Studentname", actualStudent.getStudentname());
        assertEquals("Student{_id=' id', studentname='Studentname', studentaddress='42 Main St', mobile='Mobile'}",
                actualToStringResult);
    }


}
