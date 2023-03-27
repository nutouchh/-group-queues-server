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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("")
public class StudentStatusController {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentStatusRepository studentStatusRepository;
    @Autowired
    private StudentStatusService studentStatusService;

    public static class UpdateStatusRequest {
        private String newStatus;

        public String getNewStatus() {
            return newStatus;
        }

        public void setNewStatus(String newStatus) {
            this.newStatus = newStatus;
        }
    }


    @PutMapping("/students/{studentId}/subjects/{subjectId}")
    public ResponseEntity<?> updateStudentStatus(
            @PathVariable Long studentId,
            @PathVariable Long subjectId,
            @RequestBody UpdateStatusRequest request) {
        Optional<Student> student = studentRepository.findById(studentId);
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if (student.isEmpty() || subject.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Optional<StudentStatus> studentStatusOptional = studentStatusRepository.findByStudentAndSubject(student.get(), subject.get());
        if (studentStatusOptional.isPresent()) {
            StudentStatus studentStatus = studentStatusOptional.get();
            studentStatus.setStatus(request.getNewStatus());
            studentStatusRepository.save(studentStatus);
            return ResponseEntity.ok(studentStatus);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/subjects/{subjectId}")
    public ResponseEntity<?> updateStudentStatus(
            @PathVariable Long subjectId,
            @RequestBody UpdateStatusRequest request) {
        if (subjectRepository.findById(subjectId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<StudentStatus> studentStatuses = studentStatusService.getStudentStatusesBySubjectId(subjectId);
        for (StudentStatus studentStatus : studentStatuses) {
            studentStatus.setStatus(request.getNewStatus());
            studentStatusRepository.save(studentStatus);
        }
        return ResponseEntity.ok().build();
    }
}
