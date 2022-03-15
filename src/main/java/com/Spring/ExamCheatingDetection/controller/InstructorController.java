package com.Spring.ExamCheatingDetection.controller;

import com.Spring.ExamCheatingDetection.Entity.Exam;
import com.Spring.ExamCheatingDetection.Entity.Instructor;
import com.Spring.ExamCheatingDetection.Entity.Question;
import com.Spring.ExamCheatingDetection.Entity.Student;
import com.Spring.ExamCheatingDetection.Repository.StudentRepository;
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


@RequestMapping("/instructor")
@Controller
public class InstructorController {

    private static  int examId=0;
    private static  int numEXAMQuestion=0;
    private static  int temp=0;
    private static  int QurstionId=0;
     @Autowired
    CourseService courseService;
     @Autowired
    CheatingTypeService cheatingTypeService;
     @Autowired
    ExamService examService;
     @Autowired
    QuestionService questionService;
     @Autowired
    CorrectAnswerService correctAnswerService;

     @Autowired
     InstructorService instructorService;
     @Autowired
     StudentRepository studentService;

    @Autowired
    StudentAnswerService studentAnswerService;

    @RequestMapping("/index")
    public String index(Model model)
    {
        Instructor instructor=instructorService.findById(userId());
        model.addAttribute("instructor",instructor);
        return "Instructor/index";
    }




    @RequestMapping("/profile")
    public String profile( Model model)
    {
        Instructor instructor=instructorService.findById(userId());
        model.addAttribute("instructor",instructor);
        return "Instructor/profile";
    }


    @RequestMapping("/exams")
    public String exams( Model model)
    {
        Instructor instructor=instructorService.findById(userId());
        model.addAttribute("instructor",instructor);
        return "Instructor/myExams";
    }



    @RequestMapping("/courses")
    public String courses( Model model)
    {
        Instructor instructor=instructorService.findById(userId());
        model.addAttribute("instructor",instructor);
        return "Instructor/courses";
    }



    @RequestMapping("/result")
    public String result( Model model)
    {
        Instructor instructor=instructorService.findById(userId());
        model.addAttribute("instructor",instructor);
        return "Instructor/result";
    }






    @RequestMapping("set-exam")
    public String createExaam(Model model)
    {
        Exam exam=new Exam();
        model.addAttribute("exam",exam);
        model.addAttribute("courses",instructorService.findById(userId()).getCourses());
        return "Instructor/create-exam";
    }

    @RequestMapping("/save-exam")

    public String saveExam(@ModelAttribute Exam exam )
    {
        //////set instructor id to exam
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal user = (UserPrincipal)auth.getPrincipal();
        int userId = user.getId();
        Instructor instructor=instructorService.findById(userId);
        exam.setInstructor(instructor);





       // exam.setNumberOfQuestion(exam.getNumberOfQuestion());

        cheatingTypeService.save(exam.getCheatingType());
        examService.save(exam);
        examId=exam.getId();
        numEXAMQuestion=exam.getNumberOfQuestion();
        temp=exam.getNumberOfQuestion();

        return "redirect:/instructor/CreateQuestions";
    }

////////////QUuestions////////////

    @RequestMapping("/CreateQuestions")
    public String createQuestion(Model model)
    {
        if(examId==0 || numEXAMQuestion==0)
        {
            QurstionId=0;
            return "Instructor/submitSucessfully";

        }

        Question question=new Question();
        model.addAttribute("question",question);
        model.addAttribute("QurstionId",QurstionId);
        return "Instructor/Create-questions";
    }

    @RequestMapping("/save-question")
    public String saveQuestion( @ModelAttribute Question question,Model  model)
    {
        Exam exam= examService.findById(examId);
        exam.addQuestion(question);
        questionService.save(question);
        QurstionId=question.getId();
        model.addAttribute("QurstionId",QurstionId);
        model.addAttribute("question",question);
        temp++;
        if (numEXAMQuestion!=0)
        {
            numEXAMQuestion--;
            return "redirect:/instructor/CreateQuestions";

        }
        else {
            return "redirect:/instructor/submitSucessfully";
        }


    }


    @RequestMapping("/edit-question")
    public String EditQuestion(Model model)
    {



        Question question=questionService.findById(QurstionId);
        model.addAttribute("question",question);

        numEXAMQuestion++;
        return "Instructor/Create-questions";
    }



    @RequestMapping("/evaluate-exam/{id}")
    public String EvaluateExam(Model model,@PathVariable int id)
    {

        Exam exam=examService.findById(id);
        List<Question> questions=exam.getQuestions();
        List<Student> students=exam.getStudents();
        for (int i=0;i<students.size();i++)
        {

            for (int j=0;j<questions.size();j++)
            {

                if (questions.get(j).getType()==false)
                {
                    if (students.get(i).getStudentAnswers().get(j).getStringAnswer().equals(questions.get(j).getCorrectAnswer()))
                    {
                        students.get(i).getStudentAnswers().get(j).setAnswerIsCorrect(true);
                        studentAnswerService.save( students.get(i).getStudentAnswers().get(j));
                    }

                }
                else {

                    if (students.get(i).getStudentAnswers().get(j).getBooleanAnswer()== questions.get(j).getCorrectAnswerBool())
                    {
                        students.get(i).getStudentAnswers().get(j).setAnswerIsCorrect(true);
                        studentAnswerService.save( students.get(i).getStudentAnswers().get(j));
                    }

                }



            }
        }


        return "Instructor/test_evaluate";
    }
    public  int userId()

    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal user = (UserPrincipal)auth.getPrincipal();
        int userId = user.getId();
        return userId;
    }

}
