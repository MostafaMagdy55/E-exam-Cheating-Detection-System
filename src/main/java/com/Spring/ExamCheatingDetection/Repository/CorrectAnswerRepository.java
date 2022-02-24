package com.Spring.ExamCheatingDetection.Repository;

import com.Spring.ExamCheatingDetection.Entity.CorrectAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorrectAnswerRepository extends JpaRepository<CorrectAnswer,Integer> {
}
