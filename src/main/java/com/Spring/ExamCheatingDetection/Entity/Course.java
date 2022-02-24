package com.Spring.ExamCheatingDetection.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "course_name")
    private String CourseName;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE},fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "course",cascade =
            {CascadeType.DETACH,
                    CascadeType.REMOVE,
                    CascadeType.MERGE,
                    CascadeType.REFRESH})

    List<Exam> exams;


;



//
//
//    @OneToOne(mappedBy = "course",fetch=FetchType.LAZY,
//            cascade= {CascadeType.MERGE,
//                    CascadeType.DETACH, CascadeType.REFRESH,CascadeType.REMOVE})
//    private Exam exam;



    @ManyToMany(fetch=FetchType.LAZY,
            cascade= {CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="course_student",
            joinColumns=@JoinColumn(name="course_id"),
            inverseJoinColumns=@JoinColumn(name="student_id")
    )
    private List<Student> students;

    @Override
    public String toString() {
        return CourseName;
    }


//
//    public void AddExam(Exam exam)
//    {
//        this.setExam(exam);
//
//    }
}
