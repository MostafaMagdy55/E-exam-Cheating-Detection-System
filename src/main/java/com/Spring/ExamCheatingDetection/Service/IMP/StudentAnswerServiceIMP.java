package com.Spring.ExamCheatingDetection.Service.IMP;

import com.Spring.ExamCheatingDetection.Entity.Exam;
import com.Spring.ExamCheatingDetection.Entity.StudentAnswer;
import com.Spring.ExamCheatingDetection.Repository.StudentAnswerRepository;
import com.Spring.ExamCheatingDetection.Repository.StudentRepository;
import com.Spring.ExamCheatingDetection.Service.StudentAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentAnswerServiceIMP implements StudentAnswerService {
    @Autowired
    StudentAnswerRepository studentAnswerRepository;


    @Override
    public List<StudentAnswer> findAll() {
        return studentAnswerRepository.findAll();
    }

    @Override
    public StudentAnswer findById(int theId) {
        return studentAnswerRepository.getById(theId) ;
    }

    @Override
    public void save(StudentAnswer studentAnswer) {
        studentAnswerRepository.save(studentAnswer);
    }

    @Override
    public void deleteById(int theId) {

    }
}
