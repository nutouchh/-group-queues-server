package com.example.studentsqueue.repository;

import com.example.studentsqueue.models.Subject;
import org.springframework.data.repository.CrudRepository;

public interface SubjectRepository  extends CrudRepository<Subject, Long> {
}
