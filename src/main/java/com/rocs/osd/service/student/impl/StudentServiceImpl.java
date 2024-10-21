package com.rocs.osd.service.student.impl;

import com.rocs.osd.domain.student.Student;
import com.rocs.osd.repository.student.StudentRepository;
import com.rocs.osd.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Implementation of Student interface that interacts with the StudentRepository
 * to retrieve and manage student data from the database
 */
@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    /**
     * Constructs a StationServiceImpl
     * @param studentRepository student repository
     */
    @Autowired
    public StudentServiceImpl (StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }
    @Override
    public Optional<Student> getStudentById(Long id) {
        System.out.println("Fetching student by ID: " + id);
        return studentRepository.findById(id);
    }
    @Override
    public Student getStudentByNumber(String studentNumber) {
        return studentRepository.findByStudentNumber(studentNumber);
    }
}
