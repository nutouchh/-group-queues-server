package ru.rg.studentsqueue.subjects;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rg.studentsqueue.students.StudentEntity;
import ru.rg.studentsqueue.students.StudentRepo;
import ru.rg.studentsqueue.subjects.dto.SubjectFullData;
import ru.rg.studentsqueue.subjects.dto.SubjectStudent;
import ru.rg.studentsqueue.subjects.dto.SubjectStudentFullData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The SubjectService class is a service layer class responsible for handling business logic
 * related to SubjectEntity objects and their associated StudentEntity objects.
 * This class provides methods for finding, creating, updating, and deleting SubjectEntity objects,
 * as well as methods for modifying the associated StudentEntity objects.
 * This class uses two repository interfaces, SubjectRepo and StudentRepo, to interact with the database.
 * This class is annotated with @Service to indicate that it is a service layer component.
 * It is also annotated with @RequiredArgsConstructor to generate a constructor that injects the two repository interfaces.
 */
@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepo subjectRepo;
    private final StudentRepo studentRepo;



    /**
     * Returns a SubjectFullData object containing the name, ID, and associated StudentEntity objects
     * for the given SubjectEntity object.
     *
     * @param subject the SubjectEntity object to convert to SubjectFullData
     * @return a SubjectFullData object containing the name, ID, and associated StudentEntity objects
     */
    private SubjectFullData fullData(SubjectEntity subject) {
        SubjectFullData res = SubjectFullData.builder()
                .name(subject.getName())
                .id(subject.getId())
                .students(new ArrayList<>())
                .build();

        for (SubjectStudent subjectStudent : subject.getStudents()) {
            res.getStudents().add(
                    new SubjectStudentFullData(
                            studentRepo.findById(subjectStudent.getStudentId()).orElse(null),
                            subjectStudent.getStatus()
                    )
            );
        }

        return res;
    }

    /**
     * Returns a list of all SubjectEntity objects.
     *
     * @return a list of all SubjectEntity objects
     */
    public List<SubjectEntity> findAll() {
        return subjectRepo.findAll();
    }

    public SubjectFullData findById(Long id) {
        SubjectEntity subjectEntity = subjectRepo.findById(id).orElse(null);
        if (subjectEntity == null) {
            return null;
        }

        return this.fullData(subjectEntity);
    }

    /**
     * Saves a new subject entity to the database with default waiting status for all students.
     *
     * @param subject the subject entity to be saved
     * @return the saved subject entity
     */
    public SubjectEntity save(SubjectEntity subject) {
        List<StudentEntity> students = studentRepo.findAll();

        subject.setStudents(students.stream()
                .map((student) -> {
                            return SubjectStudent.builder()
                                    .status("WAITING")
                                    .studentId(student.getId())
                                    .build();
                        }
                ).toList());

        return subjectRepo.save(subject);
    }

    /**
     * Changes an existing subject entity with the given id to the new subject entity.
     *
     * @param id      the id of the subject entity to be changed
     * @param subject the new subject entity
     * @return the updated subject entity, or null if the id does not exist
     */
    public SubjectEntity change(Long id, SubjectEntity subject) {
        if (subjectRepo.existsById(id)) {
            subject.setId(id);
            return subjectRepo.save(subject);
        }
        ;

        return null;
    }

    /**
     * Changes the status of a student in a subject entity.
     *
     * @param subjectId the id of the subject entity to be updated
     * @param studentId the id of the student entity to be updated
     * @param student   the updated subject-student entity
     * @return the updated subject entity, or null if the subject id does not exist
     */
    public SubjectEntity changeStudent(Long subjectId, Long studentId, SubjectStudent student) {
        SubjectEntity subject = subjectRepo.findById(subjectId).orElse(null);
        if (subject == null) {
            return null;
        }

        for (SubjectStudent subjectStudent : subject.getStudents()) {
            if (Objects.equals(subjectStudent.getStudentId(), studentId)) {
                subjectStudent.setStatus(student.getStatus());
            }
        }

        return subjectRepo.save(subject);
    }

    /**
     * Deletes a subject entity with the given id from the database.
     *
     * @param id the id of the subject entity to be deleted
     */
    public void deleteById(Long id) {
        subjectRepo.deleteById(id);
    }
}
