package com.example.springbootsecurityapp.controller;

import com.example.springbootsecurityapp.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable("id") Long id) {
        return null;
    }

    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        return null;
    }

    @PutMapping("/students/{id}")
    public Student updateStudent(@PathVariable("id") Long id) {
        return null;
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable("id") Long id) {

    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return null;
    }
}
