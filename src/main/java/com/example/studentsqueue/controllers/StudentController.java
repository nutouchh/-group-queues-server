package com.example.studentsqueue.controllers;

import com.example.studentsqueue.models.Student;
import com.example.studentsqueue.models.StudentStatus;
import com.example.studentsqueue.models.Subject;
import com.example.studentsqueue.repository.StudentRepository;
import com.example.studentsqueue.repository.StudentStatusRepository;
import com.example.studentsqueue.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentStatusRepository studentStatusRepository;

    @GetMapping("")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getSubjectById(@PathVariable Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        studentRepository.save(student);
        List<Subject> subjects = subjectRepository.findAll();
        for (Subject subject : subjects) {
            StudentStatus studentStatus = new StudentStatus();
            studentStatus.setStudent(student);
            studentStatus.setSubject(subject);
            studentStatus.setStatus("WAITING");
            studentStatusRepository.save(studentStatus);
        }
        return ResponseEntity.ok(student);
    }
}
