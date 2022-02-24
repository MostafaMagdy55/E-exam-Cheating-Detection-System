package com.Spring.ExamCheatingDetection.Entity;


import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;


@Entity
//@Getter
//@Setter

public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type")
    private boolean Type;

    @Column(name = "EyeMovment")
    private boolean EyeMovment;

    @Column(name = "time")
    private int Time;

    @Column(name = "question_head")
    private  String QuestionHead;

    @Column(name = "option_a")
    private  String OptionA;

    @Column(name = "option_b")
    private  String OptionB;


    @Column(name = "option_c")
    private  String OptionC;

    @Column(name = "option_d")
    private  String OptionD;

    @Column(name = "correct_answer")
    private  String CorrectAnswer;



    @Column(name = "correct_answer_bool")
    private  Boolean CorrectAnswerBool;

    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.REMOVE})
    @JoinColumn(name = "exam_id")
    private Exam exam;


    public Question() {


    }


    public Question(boolean Type, boolean eyeMovment, int Time) {
        this.Type = Type;
        EyeMovment = eyeMovment;
        this.Time = Time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getType() {
        return Type;
    }

    public void setType(boolean type) {
        Type = type;
    }

    public boolean getEyeMovment() {
        return EyeMovment;
    }

    public void setEyeMovment(boolean eyeMovment) {
        EyeMovment = eyeMovment;
    }

    public int getTime() {
        return Time;
    }

    public void setTime(int time) {
        Time = time;
    }

    public String getQuestionHead() {
        return QuestionHead;
    }

    public void setQuestionHead(String questionHead) {
        QuestionHead = questionHead;
    }

    public String getOptionA() {
        return OptionA;
    }

    public void setOptionA(String optionA) {
        OptionA = optionA;
    }

    public String getOptionB() {
        return OptionB;
    }

    public void setOptionB(String optionB) {
        OptionB = optionB;
    }

    public String getOptionC() {
        return OptionC;
    }

    public void setOptionC(String optionC) {
        OptionC = optionC;
    }

    public String getOptionD() {
        return OptionD;
    }

    public void setOptionD(String optionD) {
        OptionD = optionD;
    }

    public String getCorrectAnswer() {
        return CorrectAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        CorrectAnswer = correctAnswer;
    }

    public Boolean getCorrectAnswerBool() {
        return CorrectAnswerBool;
    }

    public void setCorrectAnswerBool(Boolean correctAnswerBool) {
        CorrectAnswerBool = correctAnswerBool;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", Type=" + Type +
                ", EyeMovment=" + EyeMovment +
                ", Time=" + Time +
                ", QuestionHead='" + QuestionHead + '\'' +
                ", OptionA='" + OptionA + '\'' +
                ", OptionB='" + OptionB + '\'' +
                ", OptionC='" + OptionC + '\'' +
                ", OptionD='" + OptionD + '\'' +
                ", CorrectAnswer='" + CorrectAnswer + '\'' +
                ", CorrectAnswerBool='" + CorrectAnswerBool + '\'' +
                ", exam=" + exam +
                '}';
    }
}
