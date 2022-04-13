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
public class StudentCheatingType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  int id;



 //   @Column(name = "enable" ,columnDefinition = "integer default 0")

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE},fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @Column(name = "cheating_by_change_identity")
    private int CheatingByChangeIdentity;

    @Column(name = "cheating_by_eye_movement")
    private int CheatingByEyeMovement;

    @Column(name = "noise")
    private  int Noise;

    @Column(name = "cheating_by_speaking")
    private int  CheatingBySpeaking;


    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE},fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;



}
