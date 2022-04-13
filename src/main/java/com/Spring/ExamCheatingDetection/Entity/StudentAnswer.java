package com.Spring.ExamCheatingDetection.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  int id;

    @Column(name = "string_answer")
    private String StringAnswer;

    @Column(name = "boolean_answer")
    private  boolean BooleanAnswer;


    @Column(name = "answer_is_correct")
    private  boolean AnswerIsCorrect;





    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE},fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;



    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.REMOVE})
    @JoinColumn(name = "exam_id")
    private Exam exam;


}
