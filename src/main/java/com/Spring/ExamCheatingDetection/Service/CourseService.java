package com.Spring.ExamCheatingDetection.Service;


import com.Spring.ExamCheatingDetection.Entity.Course;

import java.util.List;

public interface CourseService   {
    public List<Course> findAll();

    public Course findById(int theId);

    public void save(Course course);

    public void deleteById(int theId);
}
