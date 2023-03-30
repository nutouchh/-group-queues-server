package ru.rg.studentsqueue.subjects.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rg.studentsqueue.students.StudentEntity;

/**
 * This class represents the full data of a student enrolled in a subject, including their information and their current status.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectStudentFullData {
    /**
     * The entity representing the student's information.
     */
    private StudentEntity student;
    /**
     * The current status of the student in the subject.
     */
    private String status;
}
