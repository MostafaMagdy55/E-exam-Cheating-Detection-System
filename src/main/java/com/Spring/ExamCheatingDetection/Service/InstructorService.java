package com.Spring.ExamCheatingDetection.Service;

import com.Spring.ExamCheatingDetection.Entity.Instructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InstructorService{
    public List<Instructor> findAll();

    public Instructor findById(int theId);

    public void save(Instructor instructor);
    public void save(Instructor instructor, MultipartFile[] files);



    public void deleteById(int theId);
}
