package com.Spring.ExamCheatingDetection.Service;

import com.Spring.ExamCheatingDetection.Entity.Student;
import com.Spring.ExamCheatingDetection.Entity.WrongAnswer;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface WrongAnswerService {
    public List<WrongAnswer> findAll();

    public WrongAnswer findById(int theId);

    public void save(WrongAnswer wrongAnswer);
    public void crate(Model model);

    public void deleteById(int theId);
}
