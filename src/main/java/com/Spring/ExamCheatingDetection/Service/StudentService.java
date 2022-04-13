package com.Spring.ExamCheatingDetection.Service;


import com.Spring.ExamCheatingDetection.Entity.Student;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

public interface StudentService  {
    public List<Student> findAll();
    public List<Student>search(String s);
    public Student findById(int theId);

    public void save(Student student, MultipartFile[] files);
    public void save(Student student);
    public void crate(Model model);

    public void deleteById(int theId);
}
