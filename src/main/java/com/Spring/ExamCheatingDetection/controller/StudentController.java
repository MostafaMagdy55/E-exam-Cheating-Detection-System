package com.Spring.ExamCheatingDetection.controller;

import com.Spring.ExamCheatingDetection.Entity.*;
import com.Spring.ExamCheatingDetection.Service.*;
import com.Spring.ExamCheatingDetection.config.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
//////dependaency injection////////
    @Autowired
    ExamService examService;
    @Autowired
    StudentAnswerService studentAnswerService;
    @Autowired
    StudentService studentService;
    @Autowired
    CourseService courseService;
    @Autowired
    QuestionService questionService;
//////////////Static varibel//////////////
    private static int counter=1;
    private static int size=0;




    @RequestMapping("/index")
    public String index( Model model)
    {
        Student student=studentService.findById(userId());
        model.addAttribute("student",student);
         return "Student/index";
    }



    @RequestMapping("/profile")
    public String profile( Model model)
    {
        Student student=studentService.findById(userId());
        model.addAttribute("student",student);
        return "Student/profile";
    }


    @RequestMapping("/exams")
    public String exams( Model model)
    {
        Student student=studentService.findById(userId());
        model.addAttribute("student",student);
        model.addAttribute("date",java.time.LocalDate.now().toString());
        return "Student/myExams";
    }



    @RequestMapping("/courses")
    public String courses( Model model)
    {
        Student student=studentService.findById( userId());
        model.addAttribute("student",student);
        return "Student/courses";
    }



    @RequestMapping("/result")
    public String result( Model model)
    {
        Student student=studentService.findById(userId());
        model.addAttribute("student",student);
        return "Student/result";
    }



    @RequestMapping("enter-exam/{examId}/{questionId}")
    public String enterExam(Model model ,@PathVariable int examId,@PathVariable int questionId) {

       Student student=studentService.findById(userId());
       Exam exam=examService.findById(examId);
//        if(student.getExams().contains(exam))
//        {
//            return "Student/submitSucessfully";
//        }
//        else
//        {

            student.addExam(exam);
            List<Question>question= exam.getQuestions();
            size=question.size();
            StudentAnswer studentAnswer=new StudentAnswer();
            model.addAttribute("studentAnswer",studentAnswer);
            Question que=questionService.findById(questionId);
            model.addAttribute("question",que);
            studentService.save(student);
            return "Student/exam";
        }







    @RequestMapping("/save-exam/{examId}/{questionId}")
    public String saveExam(Model model, @ModelAttribute StudentAnswer answer,@PathVariable int examId,@PathVariable int questionId){


        Student student=studentService.findById(userId());
        answer.setStudent(student);

            if(counter<size && answer==null)
        {


            StudentAnswer studentAnswer=new StudentAnswer();
            model.addAttribute("studentAnswer",studentAnswer);


            Question que=questionService.findById(questionId);
            model.addAttribute("question",que);
            counter ++;
            return "Student/exam";
        }
        else if(counter<size && answer!=null)
        {

            studentAnswerService.save(answer);
            Question que=questionService.findById(questionId);
            StudentAnswer studentAnswer=new StudentAnswer();
            model.addAttribute("studentAnswer",studentAnswer);
            model.addAttribute("question",que);
            counter++;
            return "Student/exam";
        }

        else
        {
            studentAnswerService.save(answer);
            counter=1;
            evaluateExam(userId(),examId);
            model.addAttribute("student",userId());
            model.addAttribute("exam",examId);
            return "Student/submitSucessfully";
        }


    }
    public  String  evaluateExam(int studentID,int examID)

  {
    Student student=studentService.findById(studentID);
    Exam exam=examService.findById(examID);
    List<Question> questions=exam.getQuestions();

    for(int i=0;i<questions.size();i++)
    {
        if (questions.get(i).getType()==false)
        {
            if (student.getStudentAnswers().get(i).getStringAnswer().equals(questions.get(i).getCorrectAnswer()))
            {
                student.getStudentAnswers().get(i).setAnswerIsCorrect(true);
                studentAnswerService.save( student.getStudentAnswers().get(i));
            }

        }
        else{
            if (student.getStudentAnswers().get(i).getBooleanAnswer()== questions.get(i).getCorrectAnswerBool())
            {
                student.getStudentAnswers().get(i).setAnswerIsCorrect(true);
                studentAnswerService.save( student.getStudentAnswers().get(i));
            }
        }
    }
      return "Student/submitSucessfully";

 }

public  int userId()

{
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    UserPrincipal user = (UserPrincipal)auth.getPrincipal();
    int userId = user.getId();
    return userId;
}
}
