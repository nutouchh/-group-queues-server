package ru.rg.studentsqueue.students;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * An interface representing a repository for managing objects of the {@link StudentEntity} type.
 */
@Repository
public interface StudentRepo extends JpaRepository<StudentEntity, Long> {
}
