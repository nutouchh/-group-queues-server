package ru.rg.studentsqueue.subjects;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rg.studentsqueue.subjects.dto.SubjectStudent;

import java.util.List;

/**
 * This class represents the Subject entity, mapped to the "subjects" table in the database.
 */
@Entity
@Table(name = "subjects")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectEntity {
    /**
     * Subject ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Subject's name
     */
    private String name;
    /**
     * Sets the list of students enrolled in the subject.
     */
    @ElementCollection
    private List<SubjectStudent> students;
}
