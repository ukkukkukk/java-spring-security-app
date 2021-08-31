package com.example.springbootsecurityapp.service;

import com.example.springbootsecurityapp.model.Student;
import com.example.springbootsecurityapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        Student newStudent = Student.builder()
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .age(student.getAge())
                .build();

        return studentRepository.save(newStudent);
    }

    public Student updateStudent(Long id, Student student) {
        Student updateStudent = getStudentById(id);

        updateStudent.setFirstName(student.getFirstName());
        updateStudent.setLastName(student.getLastName());
        updateStudent.setAge(student.getAge());

        return studentRepository.save(updateStudent);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Student getStudentById(Long id) {
        return studentRepository.getById(id);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
