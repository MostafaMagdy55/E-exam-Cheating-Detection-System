package com.Spring.ExamCheatingDetection.controller;

import com.Spring.ExamCheatingDetection.Entity.*;
import com.Spring.ExamCheatingDetection.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    StudentService studentService;

    @Autowired
    InstructorService instructorService;

    @Autowired
    CourseService courseService;

    @Autowired
    MessageService messageService;
    @Autowired
    ExamService examService;

    @Autowired
    ReplayService replayService;

    @RequestMapping("/index")
//    public String index1(Model model)
//    {
//        model.addAttribute("course",new Course());
//        return "Admin/index";
//    }
    public ModelAndView passParametersWithModelAndView() {
        ModelAndView modelAndView = new ModelAndView("Admin/index");
        modelAndView.addObject("course", new Course());
        return modelAndView;
    }

    @RequestMapping("/dashbord")
    public String index1(Model model)
    {
        List<Exam> exams= examService.findAll();
        List<Student> students= studentService.findAll();
        List<Instructor> instructors= instructorService.findAll();
        List<Course> courses= courseService.findAll();


         Long examNum= exams.stream().count();
         Long studentNum= students.stream().count();
         Long instructorNum= instructors.stream().count();
         Long courseNum= courses.stream().count();

        model.addAttribute("course",new Course());

        model.addAttribute("examNum",examNum);
        model.addAttribute("studentNum",studentNum);
        model.addAttribute("instructorNum",instructorNum);
        model.addAttribute("courseNum",courseNum);



        return "Admin/dashbord";
    }

//////////////////////Student/////////////////


    @RequestMapping("CreateStudent")
    public String createStudent(Model model) {
        model.addAttribute("course", new Course());
        List<Course> courses = courseService.findAll();
        model.addAttribute("student", new Student());
        model.addAttribute("courses", courses);
        return "Admin/create_student";
    }


    @RequestMapping("show-student")
    public String ShowStudent(Model model) {
        String dictory = System.getProperty("user.dir");
        String path = dictory + "\\target\\images\\";
        model.addAttribute("course", new Course());
        model.addAttribute("path", path);
        return "Admin/show_student";
    }

    @RequestMapping("/save-student")

    public String saveStudent(@ModelAttribute Student student, @RequestParam("files") MultipartFile[] files, Model model) {
        model.addAttribute("course", new Course());

        if (student.getId() == 0) {
            List<Student> students = studentService.findAll();
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getName().equals(student.getName())) {
                    model.addAttribute("userISExist", "user already exist");

                    List<Course> courses = courseService.findAll();
                    model.addAttribute("courses", courses);
                    return "Admin/create_student";
                }
            }

        }
        student.addCourse(student.getCourses());
        studentService.save(student, files);
        return "redirect:/admin/show-student";
    }

    @RequestMapping("edit-student/{id}")
    public String createStudent(@PathVariable int id, Model model) {
        Student cmd = studentService.findById(id);
        List<Course> courses = courseService.findAll();
        model.addAttribute("student", cmd);
        model.addAttribute("courses", courses);
        model.addAttribute("course", new Course());
        return "Admin/create_student";
    }

    @RequestMapping("/delete-student/{id}")

    public String deleteStudent(@PathVariable int id, Model model) {
        studentService.deleteById(id);
        model.addAttribute("course", new Course());
        return "redirect:/admin/show-student";
    }

    @RequestMapping("/search")
    public String StudentSearch(String s, Model model) {
        model.addAttribute("course", new Course());
        List<Student> students = studentService.search(s);
        System.err.println(students);
        model.addAttribute("studentList", students);
        return "Admin/show_student";

    }


    @ModelAttribute("studentList")
    public List<Student> getAllStudent() {
        return studentService.findAll();
    }

    /////////////////////////Instructor///////////////

    @RequestMapping("CreateInstructor")
    public String createInstructor(Model model) {
        List<Course> courses = ValidateInstructor(courseService.findAll());

        Instructor instructor = new Instructor();
        model.addAttribute("course", new Course());
        model.addAttribute("courses", courses);
        model.addAttribute("instructor", instructor);
        return "Admin/create_instructor";
    }

    @RequestMapping("save-instructor")
    public String saveInstructor(@ModelAttribute Instructor instructor, @RequestParam("files") MultipartFile[] files, Model model) {

        model.addAttribute("course", new Course());

        if (instructor.getId() == 0) {
            List<Instructor> instructors = instructorService.findAll();
            for (int i = 0; i < instructors.size(); i++) {
                if (instructors.get(i).getName().equals(instructor.getName())) {
                    ////////for retevie the the coursess without instructor

                    List<Course> courses = ValidateInstructor(courseService.findAll());
                    model.addAttribute("courses", courses);
                    model.addAttribute("userISExist", "user already exist");
                    return "Admin/create_instructor";
                }
            }

        }
        instructor.addCourse(instructor.getCourses());
        instructorService.save(instructor, files);

        return "redirect:/admin/show-instructor";
    }

    @RequestMapping("/delete-instructor/{id}")
    public String deleteInstructor(@PathVariable int id, Model model) {
        model.addAttribute("course", new Course());
        instructorService.deleteById(id);
        return "redirect:/admin/show-instructor";
    }


    @RequestMapping("edit-instructor/{id}")
    public String UpdateInstructor(@PathVariable int id, Model model) {
        Instructor instructor = instructorService.findById(id);
        model.addAttribute("course", new Course());
        List<Course> courses = ValidateInstructor(courseService.findAll());
        ////for add instructor cousre that was reserterd first time
        for (int i=0;i<instructor.getCourses().size();i++)
        {
            courses.add(instructor.getCourses().get(i));
        }
        model.addAttribute("courses", courses);
        model.addAttribute("instructor", instructor);
        return "Admin/create_instructor";
    }

    @ModelAttribute("instructorList")
    public List<Instructor> getAllInstructor() {
        return instructorService.findAll();
    }


    @RequestMapping("show-instructor")
    public String ShowInstructor(Model model) {
        model.addAttribute("course", new Course());
        //List<Course> courses=courseService.findAll();
        //model.addAttribute("student",new Student());
        //model.addAttribute("courses",courses);
        return "Admin/show_instructor";
    }
////////////////////////////////////////////RegisterCourse////////////////

    @RequestMapping("create-course")
    public String createCourse(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("instructors", instructorService.findAll());
        return "Admin/create_course";
    }

    @RequestMapping("save-course")
    public String createCourse(@ModelAttribute Course course) {
        //   instructorRepository.save(course.getInstructor());
        courseService.save(course);
        return "redirect:/admin/show-course";
    }

//    @RequestMapping("edit-instructor/{id}")
//    public String UpdateCourse(@PathVariable int id ,Model model)
//    {
//        Course course =courseService.findById(id);
//        model.addAttribute("course",course);
//        return "Admin/create_instructor";
//    }
//
//

    @RequestMapping("show-course")
    public String ShowCourse(Model model) {
        model.addAttribute("course", new Course());
        //List<Course> courses=courseService.findAll();
        //model.addAttribute("student",new Student());
        //model.addAttribute("courses",courses);
        return "Admin/show_courses";
    }


    @RequestMapping("/delete-course/{id}")
    public String deleteCourse(@PathVariable int id, Model model) {
        courseService.deleteById(id);
        model.addAttribute("course", new Course());
        return "redirect:/admin/show-course";
    }

    @ModelAttribute("CoursesList")
    public List<Course> getAllCourses() {
        return courseService.findAll();
    }


    /////////////////meaasges///////////////
    @RequestMapping("/problems")
    public String Messages(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("messages", messageService.findAll());
        return "Admin/messages";
    }

    @RequestMapping("/show_message/{id}")
    public String showMessage(@PathVariable int id, Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("message", messageService.findById(id));
        model.addAttribute("replay", new Replay());
        return "Admin/replay";
    }


    @RequestMapping("/send_replay/{id}")
    public String sendReplay(@ModelAttribute Replay replay, @PathVariable int id) {

        Message message = messageService.findById(id);
        message.addReplay(replay);
        replayService.save(replay);
        messageService.save(message);
        return "redirect:/admin/show_message/{id}";
    }


    public List<Course> ValidateInstructor(List<Course> courses) {

        List<Course> courses2 = new ArrayList<>();
        for (int j = 0; j < courses.size(); j++) {
            if (courses.get(j).getInstructor() == null) {
                courses2.add(courses.get(j));
            }
        }
        return courses2;

    }

}
