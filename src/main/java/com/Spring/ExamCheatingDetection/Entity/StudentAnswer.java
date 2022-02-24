package com.Spring.ExamCheatingDetection.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity

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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStringAnswer() {
        return StringAnswer;
    }

    public void setStringAnswer(String stringAnswer) {
        StringAnswer = stringAnswer;
    }

    public boolean getBooleanAnswer() {
        return BooleanAnswer;
    }

    public void setBooleanAnswer(boolean booleanAnswer) {
        BooleanAnswer = booleanAnswer;
    }

    public boolean isAnswerIsCorrect() {
        return AnswerIsCorrect;
    }

    public void setAnswerIsCorrect(boolean answerIsCorrect) {
        AnswerIsCorrect = answerIsCorrect;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
