package ru.rg.studentsqueue.subjects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The SubjectRepo interface is a repository interface for SubjectEntity objects,
 * extending the JpaRepository interface for basic CRUD operations.
 * This interface provides a method for finding a SubjectEntity object by its ID and
 * returning a list of Object arrays containing both the SubjectEntity object and
 * its associated StudentEntity objects, using a JPQL query with joins and fetches.
 */
@Repository
public interface SubjectRepo extends JpaRepository<SubjectEntity, Long> {
    /**
     * Finds a SubjectEntity object by its ID and returns a list of Object arrays
     * containing both the SubjectEntity object and its associated StudentEntity objects.
     *
     * @param id the ID of the SubjectEntity object to find
     * @return a list of Object arrays containing both the SubjectEntity object and
     * its associated StudentEntity objects
     */
    @Query("select sub, stu from SubjectEntity sub " +
            "join fetch sub.students ss " +
            "join fetch StudentEntity stu on ss.studentId = stu.id " +
            "where sub.id = :id")
    List<Object[]> findByIdStudents(@Param("id") Long id);
}
