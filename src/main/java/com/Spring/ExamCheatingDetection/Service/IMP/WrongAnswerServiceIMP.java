package com.Spring.ExamCheatingDetection.Service.IMP;

import com.Spring.ExamCheatingDetection.Entity.WrongAnswer;
import com.Spring.ExamCheatingDetection.Repository.WrongAnswerRepository;
import com.Spring.ExamCheatingDetection.Service.WrongAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
@Service
public class WrongAnswerServiceIMP implements WrongAnswerService {
    @Autowired
    WrongAnswerRepository wrongAnswerRepository;
    @Override
    public List<WrongAnswer> findAll() {
        return null;
    }

    @Override
    public WrongAnswer findById(int theId) {
        return null;
    }

    @Override
    public void save(WrongAnswer wrongAnswer) {
      wrongAnswerRepository.save(wrongAnswer);
    }

    @Override
    public void crate(Model model) {

    }

    @Override
    public void deleteById(int theId) {

    }
}
