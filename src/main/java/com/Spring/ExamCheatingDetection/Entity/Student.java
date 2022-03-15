package com.Spring.ExamCheatingDetection.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter

public class Student extends Person{


    public Student(String name, String email, String phone, String password, String roles, List<Course> courses) {
        super(name, email, phone, password, roles);
        this.courses = courses;
    }

    @ElementCollection
    @CollectionTable(name="image", //defaults to student_images
            joinColumns = @JoinColumn(name="student_id"))
    @Column(name="file_name") //defaults to images
    private List<String> Images;


    @ManyToMany(fetch=FetchType.LAZY,
            cascade= { CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="course_student",
            joinColumns=@JoinColumn(name="student_id"),
            inverseJoinColumns=@JoinColumn(name="course_id")
    )
    private List<Course> courses;






    @ManyToMany(fetch=FetchType.LAZY,
            cascade= { CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="exam_student",
            joinColumns=@JoinColumn(name="student_id"),
            inverseJoinColumns=@JoinColumn(name="exam_id")
    )
    private List<Exam> exams;




    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})

    List<StudentAnswer>studentAnswers;



    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})

    List<Message>messages;



    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})

    List<StudentCheatingType>studentCheatingTypeList;
//
//    @OneToOne()
//    @JoinColumn(name = "student_answer_id")
//    private StudentAnswer studentAnswer;


    public Student() {

    }
    public void addCourse(List<Course> course) {

        if (courses == null) {
            courses = new ArrayList<>();
        }

        courses=course;
    }
    public void addMessage(Message message) {

            if (messages == null) {
                messages = new ArrayList<>();
            }

            messages.add(message);
            message.setStudent(this);
        }




    public void addExam(Exam exam) {

        if (exams == null) {
            exams = new ArrayList<>();
        }

        exams.add(exam);
    }


}


//    public void addExam(Exam exam) {
//        if (exams==null)
//        {
//            exams=new ArrayList<>();
//        }
//        exams.add(exam);
//    }

