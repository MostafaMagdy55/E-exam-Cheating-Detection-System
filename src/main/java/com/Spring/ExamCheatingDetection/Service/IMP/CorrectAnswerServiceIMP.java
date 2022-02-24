package com.Spring.ExamCheatingDetection.Service.IMP;

import com.Spring.ExamCheatingDetection.Entity.CorrectAnswer;
import com.Spring.ExamCheatingDetection.Repository.CorrectAnswerRepository;
import com.Spring.ExamCheatingDetection.Service.CorrectAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorrectAnswerServiceIMP implements CorrectAnswerService {

    @Autowired
    CorrectAnswerRepository correctAnswerRepository;
    @Override
    public List<CorrectAnswer> findAll() {
        return null;
    }

    @Override
    public CorrectAnswer findById(int theId) {
        return null;
    }

    @Override
    public void save(CorrectAnswer correctAnswer) {
        correctAnswerRepository.save(correctAnswer);
    }

    @Override
    public void deleteById(int theId) {

    }
}
