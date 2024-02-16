package com.example.SpringMongoProject.Controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.SpringMongoProject.Entity.Student;
import com.example.SpringMongoProject.Repository.StudentRepository;
import com.example.SpringMongoProject.Service.StudentServices;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {StudentController.class})
@ExtendWith(SpringExtension.class)
class StudentControllerTest {
    @Autowired
    private StudentController studentController;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private StudentServices studentServices;


    @Test
    void testDeleteStudent() throws Exception {
        // Arrange
        doNothing().when(studentServices).deleteStudent(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/student/delete/{id}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }




    @Test
    void testGetStudentById() throws Exception {
        // Arrange
        Student student = new Student();
        student.setMobile("Mobile");
        student.setStudentaddress("42 Main St");
        student.setStudentname("Studentname");
        student.set_id(" id");
        when(studentServices.getStudentById(Mockito.<String>any())).thenReturn(student);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/student/search/{id}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"_id\":\" id\",\"studentname\":\"Studentname\",\"studentaddress\":\"42 Main St\",\"mobile\":\"Mobile\"}"));
    }


    @Test
    void testGetStudents() throws Exception {
        // Arrange
        when(studentServices.listAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/student/getAll");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }



    @Test
    void testSaveStudent() throws Exception {
        // Arrange
        doNothing().when(studentServices).saveorUpdate(Mockito.<Student>any());

        Student student = new Student();
        student.setMobile("Mobile");
        student.setStudentaddress("42 Main St");
        student.setStudentname("Studentname");
        student.set_id(" id");
        String content = (new ObjectMapper()).writeValueAsString(student);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/student/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string(" id"));
    }


    @Test
    void testUpdate() throws Exception {
        // Arrange
        doNothing().when(studentServices).saveorUpdate(Mockito.<Student>any());

        Student student = new Student();
        student.setMobile("Mobile");
        student.setStudentaddress("42 Main St");
        student.setStudentname("Studentname");
        student.set_id(" id");
        String content = (new ObjectMapper()).writeValueAsString(student);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/student/edit/{id}", "42")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"_id\":\"42\",\"studentname\":\"Studentname\",\"studentaddress\":\"42 Main St\",\"mobile\":\"Mobile\"}"));
    }
}
