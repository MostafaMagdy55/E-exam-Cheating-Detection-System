package com.Spring.ExamCheatingDetection.Entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@Entity
public class Instructor extends Person{
//
//    @ElementCollection
//    @CollectionTable(name="image", //defaults to student_images
//            joinColumns = @JoinColumn(name="instructor_id"))
//    @Column(name="file_name") //defaults to images
//    private List<String> Images;


    private String Image;



    public Instructor(String name, String email, String phone, String password, String roles, String permissions) {
        super(name, email, phone, password, roles/*, permissions*/);
    }
    public  Instructor()
    {

    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "instructor",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})

    List<Course>courses;



    @OneToMany(mappedBy = "instructor",fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})

    List<Exam>exams;


//    public void addCourse(Course course) {
//        if (courses==null)
//        {
//            courses=new ArrayList<>();
//            courses.add(course);
//        }
//        course.setInstructor(this);
//
//    }

    public void addCourse(List<Course> course) {

        if (courses == null) {
            courses = new ArrayList<>();
        }

        courses=course;
        for(int i=0;i<course.size();i++)
        {
            course.get(i).setInstructor(this);
        }
    }


}
