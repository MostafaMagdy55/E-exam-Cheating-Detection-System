package com.Spring.ExamCheatingDetection.Entity;


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

    @Column(name = "time_for_cheating")
    private int TimeForCheating;


    @Column(name = "question_time")
    private int QuestionTime;
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
        this.TimeForCheating = Time;
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

    public int getTimeForCheating() {
        return TimeForCheating;
    }

    public void setTimeForCheating(int time) {
        TimeForCheating = time;
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

    public boolean isType() {
        return Type;
    }

    public boolean isEyeMovment() {
        return EyeMovment;
    }

    public int getQuestionTime() {
        return QuestionTime;
    }

    public void setQuestionTime(int questionTime) {
        QuestionTime = questionTime;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", Type=" + Type +
                ", EyeMovment=" + EyeMovment +
                ", Time=" + TimeForCheating +
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
