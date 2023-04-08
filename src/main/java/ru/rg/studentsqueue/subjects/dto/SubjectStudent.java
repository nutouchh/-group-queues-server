package ru.rg.studentsqueue.subjects.dto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents the status of a student enrolled in a subject, including their unique identifier and their current status.
 */
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class SubjectStudent {
    /**
     * The unique identifier of the student.
     */
    private Long studentId;
    /**
     * The current status of the student in the subject.
     */
    private String status;
}
