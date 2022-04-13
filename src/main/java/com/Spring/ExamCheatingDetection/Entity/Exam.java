package com.Spring.ExamCheatingDetection.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "number_of_question")
    private int NumberOfQuestion;

    @Column(name = "updated")
    private boolean Updated ;


//    @Column(name = "exam_time")
//    private int ExamTime;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date date;


    @Column(name = "time")
    private int time;



    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.REMOVE}
            , fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;


    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private  Course course;

//    @OneToOne(mappedBy = "exam",fetch=FetchType.LAZY,
//            cascade= {CascadeType.MERGE,
//                    CascadeType.DETACH, CascadeType.REFRESH,CascadeType.REMOVE})
//    private Course course;



    @ManyToMany(fetch=FetchType.LAZY,
            cascade= {CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="exam_student",
            joinColumns=@JoinColumn(name="exam_id"),
            inverseJoinColumns=@JoinColumn(name="student_id")
    )
    private List<Student> students;




//     @OneToOne(fetch=FetchType.LAZY,
//         cascade= {CascadeType.MERGE,
//                 CascadeType.DETACH, CascadeType.REFRESH,CascadeType.REMOVE})
// @JoinColumn(name = "course_id")
// private Course course;


    @OneToOne()
    @JoinColumn(name = "cheating_type_id")
    private CheatingType cheatingType;

//    @OneToOne()
//    @JoinColumn(name = "result_id")
//    private Result result;


    @OneToMany(mappedBy = "exam", cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.REMOVE
    })
    List<Question> questions;


    @OneToMany(mappedBy = "exam", cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.REMOVE
    })
    List<StudentAnswer> studentAnswers;



    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})

    List<Result>results;





    @OneToMany(mappedBy = "exam",fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})

    List<StudentCheatingType>studentCheatingTypeList;

    public void addQuestion(Question question) {
      if (questions==null)
      {
          questions=new ArrayList<>();
          questions.add(question);
      }
        question.setExam(this);

    }




    public void addStudentAnswer(StudentAnswer studentAnswer) {
        if (studentAnswers==null)
        {
            studentAnswers=new ArrayList<>();
            studentAnswers.add(studentAnswer);
        }
        studentAnswer.setExam(this);

    }

    public void AddCouser(Course course)
    {
        this.setCourse(course);

    }


    public void addStudent(List<Student> student) {

        if (students == null) {
            students = new ArrayList<>();
        }

        students=student;
    }
}
