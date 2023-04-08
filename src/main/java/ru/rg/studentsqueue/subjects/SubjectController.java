package ru.rg.studentsqueue.subjects;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rg.studentsqueue.general.ControllerFunctions;
import ru.rg.studentsqueue.subjects.dto.SubjectFullData;
import ru.rg.studentsqueue.subjects.dto.SubjectStudent;

import java.util.List;

/**
 * This class serves as the REST controller for Subject-related operations.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subjects")
public class SubjectController {
    private final SubjectService subjectService;
    private final ControllerFunctions functions;

    /**
     * Retrieves a list of all subjects.
     *
     * @return A ResponseEntity containing a list of all SubjectEntity objects.
     */
    @GetMapping
    public ResponseEntity<List<SubjectEntity>> findAll() {
        return ResponseEntity.ok(subjectService.findAll());
    }

    /**
     * Retrieves the full data for a specific subject by its id.
     *
     * @param id The unique identifier of the subject.
     * @return A ResponseEntity containing the full data of the subject.
     */
    @GetMapping("{id}")
    public ResponseEntity<SubjectFullData> findById(@PathVariable Long id) {
        return functions.findBy(id, subjectService::findById);
    }

    /**
     * Saves a new subject entity.
     *
     * @param subject The SubjectEntity object to be saved.
     * @return A ResponseEntity containing the saved SubjectEntity object.
     */
    @PostMapping
    public ResponseEntity<SubjectEntity> save(@RequestBody SubjectEntity subject) {
        return ResponseEntity.ok(subjectService.save(subject));
    }

    /**
     * Updates an existing subject entity by its id.
     *
     * @param id      The unique identifier of the subject to be updated.
     * @param subject The updated SubjectEntity object.
     * @return A ResponseEntity containing the updated SubjectEntity object.
     */
    @PutMapping("{id}")
    public ResponseEntity<SubjectEntity> change(
            @PathVariable Long id,
            @RequestBody SubjectEntity subject
    ) {
        return functions.change(id, subject, subjectService::change);
    }

    /**
     * Updates the status of a student in a subject by their ids.
     *
     * @param subjectId The unique identifier of the subject.
     * @param studentId The unique identifier of the student.
     * @param student   The SubjectStudent object containing the updated status.
     * @return A ResponseEntity containing the updated SubjectEntity object.
     */
    @PutMapping("{subjectId}/students/{studentId}")
    public ResponseEntity<SubjectEntity> changeStudent(
            @PathVariable Long subjectId,
            @PathVariable Long studentId,
            @RequestBody SubjectStudent student
    ) {
        SubjectEntity responseObj = subjectService.changeStudent(subjectId, studentId, student);
        if (responseObj != null) {
            return ResponseEntity.ok(responseObj);
        }

        return ResponseEntity.status(404).body(null);
    }

    /**
     * Deletes a subject entity by its id.
     *
     * @param id The unique identifier of the subject to be deleted.
     */
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        subjectService.deleteById(id);
    }
}
