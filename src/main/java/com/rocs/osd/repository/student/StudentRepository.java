package com.rocs.osd.repository.student;


import com.rocs.osd.domain.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * An interface to the Student repository.
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Checks if a student exists with the specified student number and email.
     *
     * @param studentNumber the student number
     * @param email the email of the student
     * @return true if studentNumber and email exist
     */
    boolean existsByStudentNumberAndEmail(String studentNumber, String email);

    /**
     * Finds a student by student number.
     *
     * @param studentNumber the student number
     * @return studentNumber
     */
    Student findByStudentNumber(String studentNumber);

    /**
     * Finds a student by ID.
     *
     * @param id the student ID
     * @return ID
     */
    Student findByUser_Id(long id);

    /**
     * Finds a student by email.
     *
     * @param email the student email
     * @return email
     */
    Student findByEmail(String email);
}
