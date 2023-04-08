package ru.rg.studentsqueue.students;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rg.studentsqueue.subjects.SubjectEntity;
import ru.rg.studentsqueue.subjects.SubjectRepo;
import ru.rg.studentsqueue.subjects.dto.SubjectStudent;

import java.util.List;

/**
 * A class is a service that provides methods for working with the entities of students and subjects.
 * <p>
 * This class accesses data through the repository objects {@link StudentRepo} and {@link SubjectRepo}.
 * <p>
 * The class is annotated as a Spring component and will be automatically created and managed by the framework.
 */
@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepo studentRepo;
    private final SubjectRepo subjectRepo;

    /**
     * A method for retrieving all student entities from a database.
     *
     * @return A list of all student entities.
     */
    public List<StudentEntity> findAll() {
        return studentRepo.findAll();
    }

    /**
     * A method for getting the student entity by the specified ID.
     *
     * @param id Student ID.
     * @return The student's entity, if found, otherwise null.
     */
    public StudentEntity findById(Long id) {
        return studentRepo.findById(id).orElse(null);
    }

    /**
     * A method for saving a student's entity in a database.
     *
     * @param student The essence of the student that needs to be preserved.
     * @return The saved essence of the student.
     */
    public StudentEntity save(StudentEntity student) {
        StudentEntity resStudent = studentRepo.save(student);
        List<SubjectEntity> subjects = subjectRepo.findAll();

        for (SubjectEntity subject : subjects) {
            subject.getStudents().add(
                    SubjectStudent.builder()
                            .studentId(resStudent.getId())
                            .status("WAITING")
                            .build()
            );

            subjectRepo.save(subject);
        }

        return resStudent;
    }

    /**
     * A method for changing a student's entity by the specified ID.
     *
     * @param id      ID of the student entity that needs to be changed.
     * @param student The new essence of the student.
     * @return The changed essence of the student.
     */
    public StudentEntity change(Long id, StudentEntity student) {
        if (studentRepo.existsById(id)) {
            student.setId(id);
            return studentRepo.save(student);
        }
        ;

        return null;
    }

    /**
     * A method for deleting a student entity by the specified ID.
     *
     * @param id ID of the student entity to delete.
     */
    public void deleteById(Long id) {
        StudentEntity dbStudent = this.findById(id);
        if (dbStudent != null) {
            studentRepo.deleteById(id);
        }
    }
}
