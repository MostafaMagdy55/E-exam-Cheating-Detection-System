package com.Spring.ExamCheatingDetection.controller;
import com.Spring.ExamCheatingDetection.Entity.Course;
import com.Spring.ExamCheatingDetection.Entity.Instructor;
import com.Spring.ExamCheatingDetection.Entity.Student;
import com.Spring.ExamCheatingDetection.Service.CourseService;
import com.Spring.ExamCheatingDetection.Service.InstructorService;
import com.Spring.ExamCheatingDetection.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    StudentService studentService ;

    @Autowired
    InstructorService instructorService;

    @Autowired
    CourseService courseService;
   @Autowired
  // private PasswordEncoder passwordEncoder;

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

//////////////////////Student/////////////////


    @RequestMapping("CreateStudent")
    public String createStudent(Model model)
    {
        model.addAttribute("course",new Course());
        List<Course> courses=courseService.findAll();
        model.addAttribute("student",new Student());
        model.addAttribute("courses",courses);
        return "Admin/create_student";
    }


    @RequestMapping("show-student")
    public String ShowStudent(Model model)
    {
        model.addAttribute("course",new Course());
        //List<Course> courses=courseService.findAll();
        //model.addAttribute("student",new Student());
        //model.addAttribute("courses",courses);
        return "Admin/show_student";
    }

    @RequestMapping("/save-student")

    public String saveStudent(@ModelAttribute Student student,@RequestParam("files") MultipartFile[] files,Model model)
    {
        student.addCourse(student.getCourses());
        studentService.save(student,files);
        model.addAttribute("course",new Course());
        return "redirect:/admin/show-student";
    }

    @RequestMapping("edit-student/{id}")
    public String createStudent(@PathVariable int id ,Model model)
    {
        Student cmd=studentService.findById(id);
        List<Course> courses=courseService.findAll();
        model.addAttribute("student",cmd);
        model.addAttribute("courses",courses);
        model.addAttribute("course",new Course());
        return "Admin/create_student";
    }

    @RequestMapping("/delete-student/{id}")

    public String deleteStudent(@PathVariable int id,Model model)
    {
        studentService.deleteById(id);
        model.addAttribute("course",new Course());
        return "redirect:/admin/show-student";
    }


@ModelAttribute("studentList")
public List<Student>getAllStudent(){
        return studentService.findAll();
}

 /////////////////////////Instructor///////////////

    @RequestMapping("CreateInstructor")
    public String createInstructor(Model model)
    {
        Instructor instructor=new Instructor();
        List<Course> courses=courseService.findAll();
        model.addAttribute("course",new Course());
        model.addAttribute("courses",courses);
        model.addAttribute("instructor",instructor);
        return "Admin/create_instructor";
    }

    @RequestMapping("save-instructor")
    public String saveInstructor(@ModelAttribute Instructor instructor,@RequestParam("files") MultipartFile[] files,Model model) {
         List<Course> courses=instructor.getCourses();
        for (int i=0; i<courses.size(); i++)
        {
         instructor.addCourse(courses.get(i));
        }

        instructorService.save(instructor,files);
        model.addAttribute("course",new Course());
        return "redirect:/admin/show-instructor";
    }

    @RequestMapping("/delete-instructor/{id}")
    public String deleteInstructor(@PathVariable int id,Model model)
    {
        model.addAttribute("course",new Course());
        instructorService.deleteById(id);
        return "redirect:/admin/show-instructor";
    }


    @RequestMapping("edit-instructor/{id}")
    public String UpdateInstructor(@PathVariable int id ,Model model)
    {
        Instructor instructor =instructorService.findById(id);
        model.addAttribute("course",new Course());
        model.addAttribute("courses",courseService.findAll());
        model.addAttribute("instructor",instructor);
        return "Admin/create_instructor";
    }

    @ModelAttribute("instructorList")
    public List<Instructor> getAllInstructor(){
        return instructorService.findAll();
    }



    @RequestMapping("show-instructor")
    public String ShowInstructor(Model model)
    {
        model.addAttribute("course",new Course());
        //List<Course> courses=courseService.findAll();
        //model.addAttribute("student",new Student());
        //model.addAttribute("courses",courses);
        return "Admin/show_instructor";
    }
////////////////////////////////////////////RegisterCourse////////////////

   @RequestMapping("create-course")
    public String createCourse(Model model)
    {
        model.addAttribute("course",new Course());
        model.addAttribute("instructors",instructorService.findAll());
        return "Admin/create_course";
    }

    @RequestMapping("save-course")
    public String createCourse(@ModelAttribute Course course)
    {
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
    public String ShowCourse(Model model)
    {
        model.addAttribute("course",new Course());
        //List<Course> courses=courseService.findAll();
        //model.addAttribute("student",new Student());
        //model.addAttribute("courses",courses);
        return "Admin/show_courses";
    }



    @RequestMapping("/delete-course/{id}")
    public String deleteCourse(@PathVariable int id,Model model)
    {
        courseService.deleteById(id);
        model.addAttribute("course",new Course());
        return "redirect:/admin/show-course";
    }

    @ModelAttribute("CoursesList")
    public List<Course> getAllCourses(){
        return courseService.findAll();
    }



}
