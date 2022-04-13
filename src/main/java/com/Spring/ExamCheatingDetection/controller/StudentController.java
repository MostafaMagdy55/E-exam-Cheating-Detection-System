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
import java.util.stream.Collectors;


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
    @Autowired
    MessageService messageService;
    @Autowired
    ResultService resultService;
    //////////////Static varibel//////////////
    private static int counter = 1;
    private static int size = 0;


    @RequestMapping("/index/{id}")
    public String index(Model model) {
        Student student = studentService.findById(userId());
        model.addAttribute("student", student);
        return "Student/index";
    }


    @RequestMapping("/profile/{id}")
    public String profile(Model model) {
        Student student = studentService.findById(userId());
        model.addAttribute("student", student);
        return "Student/profile";
    }


    @RequestMapping("/exams/{id}")
    public String exams(Model model) {
        Student student = studentService.findById(userId());
        model.addAttribute("student", student);
        model.addAttribute("date", java.time.LocalDate.now().toString());
        model.addAttribute("hour", java.time.LocalTime.now().getHour());
        model.addAttribute("minute", java.time.LocalTime.now().getMinute());
        return "Student/myExams";
    }


    @RequestMapping("/courses/{id}")
    public String courses(Model model) {
        Student student = studentService.findById(userId());
        model.addAttribute("student", student);
        return "Student/courses";
    }


    @RequestMapping("/result/{id}")
    public String result(Model model) {
        Student student = studentService.findById(userId());
        model.addAttribute("student", student);
//        student.getExams().get(1).getStudentAnswers().get(1).isAnswerIsCorrect();
//        List<Integer> studentGrade=new ArrayList<>();
//        int num=0;
//        for(int i=0;i<student.getExams().size();i++)
//        {
//            for(int j=0;j<student.getExams().get(i).getStudentAnswers().size();j++)
//            {
//                if(student.getExams().get(i).getStudentAnswers().get(j).isAnswerIsCorrect()==true)
//                {
//                    num++;
//                }
//            }
//            studentGrade.add(num);
//            num=0;
//
//
//        }
//        System.err.println(studentGrade);
//        model.addAttribute("studentGrade",studentGrade);
        return "Student/result";
    }


    @RequestMapping("enter-exam/{examId}/{questionId}")
    public String enterExam(Model model, @PathVariable int examId, @PathVariable int questionId) {
        Student student = studentService.findById(userId());
        Exam exam = examService.findById(examId);
        if (student.getExams().contains(exam)) {
            return "Student/submitSucessfully";
        } else {
            //Exam exam = examService.findById(examId);
            List<Question> question = exam.getQuestions();
            size = question.size();
            Question que = questionService.findById(questionId);
            model.addAttribute("question", que);

            model.addAttribute("studentAnswer", new StudentAnswer());
            // Student student = studentService.findById(userId());
            student.addExam(exam);
            studentService.save(student);
            return "Student/exam";
        }
    }


    @RequestMapping("/save-exam/{examId}/{questionId}")
    public String saveExam(Model model, @ModelAttribute StudentAnswer answer, @PathVariable int examId, @PathVariable int questionId) {

        ///////for test and improve evaluate the exam///////
        Exam exam = examService.findById(examId);
        exam.addStudentAnswer(answer);


        Student student = studentService.findById(userId());
        answer.setStudent(student);

        if (counter < size && answer == null) {


            StudentAnswer studentAnswer = new StudentAnswer();
            model.addAttribute("studentAnswer", studentAnswer);


            Question que = questionService.findById(questionId);
            model.addAttribute("question", que);
            counter++;
            return "Student/exam";
        } else if (counter < size && answer != null) {

            studentAnswerService.save(answer);
            Question que = questionService.findById(questionId);
            StudentAnswer studentAnswer = new StudentAnswer();
            model.addAttribute("studentAnswer", studentAnswer);
            model.addAttribute("question", que);
            counter++;
            return "Student/exam";
        } else {
            studentAnswerService.save(answer);
            counter = 1;
            //evaluateExam(userId(),examId);
            // model.addAttribute("student",userId());
            model.addAttribute("examID", examId);
            return "Student/submitSucessfully";
        }


    }

    @RequestMapping("/evaluate_exam/{examID}")
    public String evaluateExam(@PathVariable int examID, Model model) {
        Student student = studentService.findById(userId());
        Exam exam = examService.findById(examID);
        List<Question> questions = exam.getQuestions();

        int num = 0;
        List<StudentAnswer> studentAnswers1 = studentAnswerService.findAll();
        List<StudentAnswer> studentAnswers = studentAnswers1.stream().filter(n -> n.getExam().getId() == examID).collect(Collectors.toList());
        List<StudentAnswer> studentAnswersToExam = studentAnswers.stream().filter(n -> n.getStudent().getId() == student.getId()).collect(Collectors.toList());

        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getType() == false) {
                if (studentAnswersToExam.get(i).getStringAnswer() != "" && studentAnswersToExam.get(i).getStringAnswer().equals(questions.get(i).getCorrectAnswer())) {
                    num++;
                    studentAnswersToExam.get(i).setAnswerIsCorrect(true);
                    studentAnswerService.save(studentAnswersToExam.get(i));
                    /// studentAnswerService.save(student.getStudentAnswers().get(i));
                }

            } else {
                if (studentAnswersToExam.get(i).isBooleanAnswer() == questions.get(i).getCorrectAnswerBool()) {
                    num++;
                    studentAnswersToExam.get(i).setAnswerIsCorrect(true);
                    studentAnswerService.save(studentAnswersToExam.get(i));
                    //studentAnswerService.save(student.getStudentAnswers().get(i));
                }
            }

        }
        Result result = new Result();
        result.setExam(exam);
        result.setExamResult(num);
        result.setNumOfQuestion(exam.getNumberOfQuestion());
        result.setStudent(student);
        student.getResults().add(result);
        resultService.save(result);
        model.addAttribute("student", student);
        return "Student/result";

    }

    @RequestMapping("/messages")
    public String Message(Model model) {
        model.addAttribute("student", studentService.findById(userId()));
        model.addAttribute("message", new Message());
        return "Student/message";

    }

    @RequestMapping("/myMessages")
    public String MyMessages(Model model) {
        model.addAttribute("student", studentService.findById(userId()));
        model.addAttribute("message", new Message());
        return "Student/myMessage";

    }

    @RequestMapping("/send_message")
    public String SendMessages(@ModelAttribute Message message) {
        Student student = studentService.findById(userId());
        student.addMessage(message);
        messageService.save(message);
        studentService.save(student);
        return "redirect:/student/messages";

    }


    @RequestMapping("/enter-exam/-10/-10")
    public String Fired() {
        return "Student/firedpage";
    }


    public int userId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal user = (UserPrincipal) auth.getPrincipal();
        int userId = user.getId();
        return userId;
    }


}
