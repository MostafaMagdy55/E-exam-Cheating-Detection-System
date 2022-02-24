package com.Spring.ExamCheatingDetection.Service;

import com.Spring.ExamCheatingDetection.Entity.Exam;
import com.Spring.ExamCheatingDetection.Entity.StudentAnswer;

import java.util.List;

public interface StudentAnswerService {

    public List<StudentAnswer> findAll();

    public StudentAnswer findById(int theId);

    public void save(StudentAnswer studentAnswer);

    public void deleteById(int theId);
}
