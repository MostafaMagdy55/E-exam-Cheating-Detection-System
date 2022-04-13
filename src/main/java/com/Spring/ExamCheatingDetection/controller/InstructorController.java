package com.Spring.ExamCheatingDetection.controller;

import com.Spring.ExamCheatingDetection.Entity.*;
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

    private static int examId = 0;
    private static int numEXAMQuestion = 0;
    private static int editQ = 0;
    private static int editQC = 0;
    private static int QuestionId = 0;
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
    public String index(Model model) {
        Instructor instructor = instructorService.findById(userId());
        model.addAttribute("instructor", instructor);
        return "Instructor/index";
    }


    @RequestMapping("/profile")
    public String profile(Model model) {
        Instructor instructor = instructorService.findById(userId());
        model.addAttribute("instructor", instructor);
        return "Instructor/profile";
    }


    @RequestMapping("/exams")
    public String exams(Model model) {
        Instructor instructor = instructorService.findById(userId());
        model.addAttribute("instructor", instructor);
        return "Instructor/myExams";
    }


    @RequestMapping("/courses")
    public String courses(Model model) {
        Instructor instructor = instructorService.findById(userId());
        model.addAttribute("instructor", instructor);
        return "Instructor/courses";
    }


    @RequestMapping("/result")
    public String result(Model model) {
        Instructor instructor = instructorService.findById(userId());
        model.addAttribute("instructor", instructor);
        return "Instructor/result";
    }


    @RequestMapping("set-exam")
    public String createExaam(Model model) {
        Exam exam = new Exam();
        model.addAttribute("exam", exam);
        model.addAttribute("courses", instructorService.findById(userId()).getCourses());
        return "Instructor/create-exam";
    }

    @RequestMapping("/save-exam")

    public String saveExam(@ModelAttribute Exam exam, Model model) {

        if (exam.getNumberOfQuestion() <= 0) {
            model.addAttribute("courses", instructorService.findById(userId()).getCourses());
            model.addAttribute("Error", "number of question cant be null");
            return "Instructor/create-exam";
        }
        if (exam.getCourse() == null) {
            model.addAttribute("Error2", "must select course");
            return "Instructor/create-exam";
        }
        Instructor instructor = instructorService.findById(userId());
        exam.setInstructor(instructor);
        exam.setUpdated(true);
        cheatingTypeService.save(exam.getCheatingType());
        examService.save(exam);
        examId = exam.getId();
        numEXAMQuestion = exam.getNumberOfQuestion();


        return "redirect:/instructor/CreateQuestions";
    }


    @RequestMapping("edit-exam/{id}")
    public String UpdateExam(@PathVariable int id, Model model) {
        Exam exam = examService.findById(id);
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("exam", exam);
        return "Instructor/create-exam";
    }


////////////QUuestions////////////

    @RequestMapping("/CreateQuestions")
    public String createQuestion(Model model) {
        if (examId == 0 || numEXAMQuestion == 0) {
            //     QuestionId = 0;
            editQ = 0;
            editQC = 0;
            return "Instructor/submitSucessfully";

        }
        Exam exam = examService.findById(examId);
        editQ = exam.getNumberOfQuestion();
        if (exam.isUpdated() == true && editQC < editQ) {
            try {
                Question question = exam.getQuestions().get(editQC);
                model.addAttribute("question", question);
            } catch (IndexOutOfBoundsException e) {
                Question question = new Question();
                model.addAttribute("question", question);
            } finally {
                editQC++;
                return "Instructor/Create-questions";
            }


        } else {
            Question question = new Question();
            model.addAttribute("question", question);
            // model.addAttribute("QurstionId", QuestionId);
            return "Instructor/Create-questions";
        }


    }

    @RequestMapping("/save-question")
    public String saveQuestion(@ModelAttribute Question question, Model model) {
        Exam exam = examService.findById(examId);
        exam.addQuestion(question);
        questionService.save(question);
        QuestionId = question.getId();
        model.addAttribute("QurstionId", QuestionId);
        model.addAttribute("question", question);
        if (numEXAMQuestion != 0) {
            numEXAMQuestion--;
            return "redirect:/instructor/CreateQuestions";

        } else {
            //for text
            examId = 0;
            return "redirect:/instructor/submitSucessfully";
        }


    }


    @RequestMapping("/edit-question")
    public String EditQuestion(Model model) {
        Question question = questionService.findById(QuestionId);
        model.addAttribute("question", question);

        numEXAMQuestion++;
        return "Instructor/Create-questions";
    }


    @RequestMapping("/evaluate-exam/{id}")
    public String EvaluateExam(Model model, @PathVariable int id) {

        Exam exam = examService.findById(id);
        List<Question> questions = exam.getQuestions();
        List<Student> students = exam.getStudents();
        for (int i = 0; i < students.size(); i++) {

            for (int j = 0; j < questions.size(); j++) {

                if (questions.get(j).getType() == false) {
                    if (students.get(i).getStudentAnswers().get(j).getStringAnswer().equals(questions.get(j).getCorrectAnswer())) {
                        students.get(i).getStudentAnswers().get(j).setAnswerIsCorrect(true);
                        studentAnswerService.save(students.get(i).getStudentAnswers().get(j));
                    }

                } else {

                    if (students.get(i).getStudentAnswers().get(j).isBooleanAnswer() == questions.get(j).getCorrectAnswerBool()) {
                        students.get(i).getStudentAnswers().get(j).setAnswerIsCorrect(true);
                        studentAnswerService.save(students.get(i).getStudentAnswers().get(j));
                    }

                }


            }
        }


        return "Instructor/test_evaluate";
    }


    public int userId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal user = (UserPrincipal) auth.getPrincipal();
        int userId = user.getId();
        return userId;
    }

}
