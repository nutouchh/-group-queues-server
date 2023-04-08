package ru.rg.studentsqueue.subjects.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This class represents the full data of a subject, including its id, name, and a list of students enrolled in the subject.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectFullData {
    /**
     * The unique identifier of the subject.
     */
    private Long id;
    /**
     * The name of the subject.
     */
    private String name;
    /**
     * A list of full data for each student enrolled in the subject.
     */
    private List<SubjectStudentFullData> students;
}
