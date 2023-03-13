package com.example.studentsqueue.controllers;

import com.example.studentsqueue.models.Student;
import com.example.studentsqueue.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    

    @PostMapping("/students")
    public ResponseEntity<?> createSubject(@RequestBody Student student) {
        studentRepository.save(student);
        return ResponseEntity.ok(student);
    }
    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable("id") Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        studentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllSubjects() {
        List<Student> students = (List<Student>) studentRepository.findAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getSubjectById(@PathVariable Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateSubject(@PathVariable("id") Long id, @RequestBody Student student) {
        Optional<Student> optionalSubject = studentRepository.findById(id);
        if (optionalSubject.isPresent()) {
            Student existingSubject = optionalSubject.get();
            existingSubject.setName(student.getName());
            studentRepository.save(existingSubject);
            return ResponseEntity.ok(existingSubject);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
