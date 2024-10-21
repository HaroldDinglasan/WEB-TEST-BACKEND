package com.rocs.osd.controller.student;

import com.rocs.osd.domain.student.Student;
import com.rocs.osd.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
/**
 *RestController for managing student operation request.
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;
    /**
     * Constructs a new StudentController with StudentService.
     *
     * @param studentService handles student operations
     */
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    /**
     * Retrieves the list of all students.
     *
     * @return the list of all students
     */
    @GetMapping("/studentList")
    public ResponseEntity<List<Student>> getAllStudent() {
        return new ResponseEntity<>(studentService.getAllStudent(), HttpStatus.OK);
    }
    /**
     * Retrieves the student by ID.
     *
     * @param id ID of the student
     * @return student
     */
    @GetMapping("/studentId/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        System.out.println("Requested ID: " + id);
        Optional<Student> student = studentService.getStudentById(id);
        return student.map(s -> new ResponseEntity<>(s, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    /**
     * Retrieves a student by student number.
     *
     * @param studentNumber student number of the student
     * @return student
     */
    @GetMapping("/studentNumber/{studentNumber}")
    public ResponseEntity<Student> getStudentByNumber(@PathVariable String studentNumber) {
        return new ResponseEntity<>(this.studentService.getStudentByNumber(studentNumber), HttpStatus.OK);

    }
}
