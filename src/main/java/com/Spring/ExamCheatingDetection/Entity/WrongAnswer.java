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
public class WrongAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  int id;


    @Column(name = "wrong_answer1")
    private String WrongAnswer1;

    @Column(name = "wrong_answer2")
    private String WrongAnswer2;

    @Column(name = "wrong_answer3")
    private String WrongAnswer3;

    @Column(name = "boolean_answer")
    private  boolean BooleanAnswer;

    @Override
    public String toString() {
        return "WrongAnswer{" +
                "id=" + id +
                ", WrongAnswer1='" + WrongAnswer1 + '\'' +
                ", WrongAnswer2='" + WrongAnswer2 + '\'' +
                ", WrongAnswer3='" + WrongAnswer3 + '\'' +
                ", BooleanAnswer=" + BooleanAnswer +
                '}';
    }
}
