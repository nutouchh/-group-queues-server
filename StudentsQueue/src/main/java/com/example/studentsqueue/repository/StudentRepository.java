package com.example.studentsqueue.repository;

import com.example.studentsqueue.models.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
