package com.Spring.ExamCheatingDetection.Service;

import com.Spring.ExamCheatingDetection.Entity.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {

    public Question findById(int theId);
    public List<Question> findAll();
    public void save(Question question);


    public void deleteById(int theId);
}
