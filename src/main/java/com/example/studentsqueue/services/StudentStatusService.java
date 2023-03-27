package com.example.studentsqueue.services;


import com.example.studentsqueue.models.StudentStatus;
import com.example.studentsqueue.repository.StudentStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentStatusService {

    @Autowired
    private StudentStatusRepository studentStatusRepository;

    public List<StudentStatus> getStudentStatusesBySubjectId(Long subjectId) {
        return studentStatusRepository.findBySubjectId(subjectId);
    }

}