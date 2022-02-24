package com.Spring.ExamCheatingDetection.Service;

import com.Spring.ExamCheatingDetection.Entity.Exam;

import java.util.List;

public interface ExamService {
    public List<Exam> findAll();

    public Exam findById(int theId);

    public void save(Exam exam);

    public void deleteById(int theId);
}
