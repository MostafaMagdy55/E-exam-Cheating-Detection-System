package com.Spring.ExamCheatingDetection.Service;

import com.Spring.ExamCheatingDetection.Entity.Instructor;

import java.util.List;

public interface InstructorService{
    public List<Instructor> findAll();

    public Instructor findById(int theId);

    public void save(Instructor instructor);

    public void deleteById(int theId);
}
