package ru.rg.studentsqueue.students;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rg.studentsqueue.general.ControllerFunctions;

import java.util.List;

/**
 * Controller for working with students.
 */
@RestController
@RequiredArgsConstructor
    @RequestMapping("/api/students")
    public class StudentController {
        private final StudentService studentService; // Сервис для работы со студентами
    private final ControllerFunctions functions; // Функции контроллера для работы со студентами

    /**
     * Get a list of all students.
     *
     * @return List of all students.
     */
    @GetMapping
    public ResponseEntity<List<StudentEntity>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    /**
     * Find a student by ID.
     *
     * @param id Student ID.
     * @return Student with the specified ID.
     */
    @GetMapping("{id}")
    public ResponseEntity<StudentEntity> findById(@PathVariable Long id) {
        return functions.findBy(id, studentService::findById);
    }

    /**
     * Save student information.
     *
     * @param student Information about the student.
     * @return Information about the saved student.
     */
    @PostMapping
    public ResponseEntity<StudentEntity> save(
            @RequestBody StudentEntity student
    ) {
        return ResponseEntity.ok(studentService.save(student));
    }

    /**
     * Change student information.
     *
     * @param id      Student ID.
     * @param student Information about the student.
     * @return Information about the changed student.
     */

    @PutMapping("{id}")
    public ResponseEntity<StudentEntity> change(
            @PathVariable Long id,
            @RequestBody StudentEntity student
    ) {
        return functions.change(id, student, studentService::change);
    }

    /**
     * Delete student information by ID.
     *
     * @param id Student ID.
     */
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
    }
}
