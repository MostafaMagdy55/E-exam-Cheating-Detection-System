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
public class CorrectAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  int id;

    @Column(name = "string_answer")
    private String StringAnswer;

    @Column(name = "boolean_answer")
    private  boolean BooleanAnswer;

    @Override
    public String toString() {
        return "CorrectAnswer{" +
                "id=" + id +
                ", StringAnswer='" + StringAnswer + '\'' +
                ", BooleanAnswer=" + BooleanAnswer +
                '}';
    }
}
