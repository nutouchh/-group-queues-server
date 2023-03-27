package com.example.studentsqueue.controllers;

import com.example.studentsqueue.models.Student;
import com.example.studentsqueue.models.StudentStatus;
import com.example.studentsqueue.models.Subject;
import com.example.studentsqueue.repository.StudentRepository;
import com.example.studentsqueue.repository.StudentStatusRepository;
import com.example.studentsqueue.repository.SubjectRepository;
import com.example.studentsqueue.services.StudentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentStatusRepository studentStatusRepository;

    @Autowired
    private StudentStatusService studentStatusService;

    @PostMapping("")
    public ResponseEntity<?> createSubject(@RequestBody Subject subject) {
        subjectRepository.save(subject);
        List<Student> students = studentRepository.findAll();
        for (Student student : students) {
            StudentStatus studentStatus = new StudentStatus();
            studentStatus.setStudent(student);
            studentStatus.setSubject(subject);
            studentStatus.setStatus("WAITING");
            studentStatusRepository.save(studentStatus);
        }
        return ResponseEntity.ok(subject);
    }

    @GetMapping("")
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> subjects = (List<Subject>) subjectRepository.findAll();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getSubjectById(@PathVariable Long id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            Map<String, Object> result = new HashMap<>();
            result.put("name", subject.get().getName());
            result.put("id", id);
            List<StudentStatus> studentStatuses = studentStatusService.getStudentStatusesBySubjectId(id);
            List<Map<String, Object>> studentsData = new ArrayList<>();
            for (StudentStatus studentStatus : studentStatuses) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", studentStatus.getStudent().getId());
                map.put("status", studentStatus.getStatus());
                studentsData.add(map);
            }
            result.put("studentsData", studentsData);
            return result;
        } else {
            return null;
        }
    }
}