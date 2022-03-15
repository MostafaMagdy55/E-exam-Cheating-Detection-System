package com.Spring.ExamCheatingDetection.Service;

import com.Spring.ExamCheatingDetection.Entity.Question;

import java.util.List;

public interface QuestionService {

    public Question findById(int theId);
    public List<Question> findAll();
    public void save(Question question);


    public void deleteById(int theId);
}
