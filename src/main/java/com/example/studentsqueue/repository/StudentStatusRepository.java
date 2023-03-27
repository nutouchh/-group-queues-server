package com.example.studentsqueue.repository;

import com.example.studentsqueue.models.Student;
import com.example.studentsqueue.models.StudentStatus;
import com.example.studentsqueue.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentStatusRepository extends JpaRepository<StudentStatus, Long> {
    List<StudentStatus> findBySubjectId(Long subjectId);
    Optional<StudentStatus> findByStudentAndSubject(Student student, Subject subject);
}