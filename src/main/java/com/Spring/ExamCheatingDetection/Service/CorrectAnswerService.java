package com.Spring.ExamCheatingDetection.Service;

import com.Spring.ExamCheatingDetection.Entity.CorrectAnswer;
import com.Spring.ExamCheatingDetection.Entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.ui.Model;

import java.util.List;

public interface CorrectAnswerService {
    public List<CorrectAnswer> findAll();

    public CorrectAnswer findById(int theId);

    public void save(CorrectAnswer correctAnswer);

    public void deleteById(int theId);
}
