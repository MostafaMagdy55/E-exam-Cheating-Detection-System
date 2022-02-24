package com.Spring.ExamCheatingDetection.Service.IMP;

import com.Spring.ExamCheatingDetection.Entity.Course;
import com.Spring.ExamCheatingDetection.Entity.Exam;
import com.Spring.ExamCheatingDetection.Repository.CheatingTypeRepository;
import com.Spring.ExamCheatingDetection.Repository.CourseRepository;
import com.Spring.ExamCheatingDetection.Repository.ExamRepository;
import com.Spring.ExamCheatingDetection.Repository.InstructorRepository;
import com.Spring.ExamCheatingDetection.Service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamServiceIMP implements ExamService {

    @Autowired
    ExamRepository examRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CheatingTypeRepository cheatingTypeRepository;
    @Autowired
    InstructorRepository instructorRepository;

    @Override
    public List<Exam> findAll() {
        return examRepository.findAll();
    }

    @Override
    public Exam findById(int theId) {
        Optional<Exam> result = examRepository.findById(theId);

        Exam exam = null;

        if (result.isPresent()) {
            exam = result.get();
        } else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return exam;
    }

    @Override
    public void save(Exam exam) {
    examRepository.save(exam);
    }

    @Override
    public void deleteById(int theId) {

    }
}
