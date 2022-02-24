package com.Spring.ExamCheatingDetection.Service.IMP;

import com.Spring.ExamCheatingDetection.Entity.Instructor;
import com.Spring.ExamCheatingDetection.Entity.Question;
import com.Spring.ExamCheatingDetection.Entity.Student;
import com.Spring.ExamCheatingDetection.Repository.QuestionRepository;
import com.Spring.ExamCheatingDetection.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceIMP implements QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    @Override
    public Question findById(int theId) {

        Optional<Question> result = questionRepository.findById(theId);

        Question theQuestion = null;

        if (result.isPresent()) {
            theQuestion = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find question id - " + theId);
        }

        return theQuestion;

    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }
    @Override
    public void save(Question question) {
      questionRepository.save(question);
    }

    @Override
    public void deleteById(int theId) {

    }
}
