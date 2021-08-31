package com.example.springbootsecurityapp.repository;

import com.example.springbootsecurityapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
