package com.Spring.ExamCheatingDetection.Service.IMP;

import com.Spring.ExamCheatingDetection.Entity.Student;
import com.Spring.ExamCheatingDetection.Repository.StudentRepository;
import com.Spring.ExamCheatingDetection.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceIMP implements StudentService {

    @Autowired
    StudentRepository studentRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(int theId) {

        Optional<Student> result = studentRepository.findById(theId);

        Student theStudent = null;

        if (result.isPresent()) {
            theStudent = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return theStudent;
    }

    @Override
    public void save(Student student,MultipartFile[] files) {
        String name=student.getName();
        List<String>image=new ArrayList<>();
        File f=new File("D:\\Graduation Project   E-exam Cheating Detection System\\ExamCheatingDetection\\images\\"+name);
        f.mkdirs();

        if (f.exists())
        {
            for (MultipartFile file : files) {
                Path fileNameAndPath = Paths.get(f.getPath(), file.getOriginalFilename());
                image.add(file.getOriginalFilename());
                student.setImages(image);
                try {
                    Files.write(fileNameAndPath, file.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

       BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
       student.setPassword(encoder.encode(student.getPassword()));
        student.setRole("ROLE_STUDENT");
        student.setEnabled(true);
        studentRepository.save(student);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void crate( Model model) {
        Student cmd =new Student();
        model.addAttribute("student",cmd);
    }

    @Override
    public void deleteById(int theId) {
        Optional<Student> result = studentRepository.findById(theId);

        Student theStudent = null;

        if (result.isPresent()) {
            theStudent = result.get();
            String name=theStudent.getName();
            System.err.println(name);
            File f=new File("D:\\Graduation Project   E-exam Cheating Detection System\\ExamCheatingDetection\\images\\"+name);
            try {
                FileUtils.deleteDirectory(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // f.delete();
            studentRepository.deleteById(theId);
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + theId);
        }
    }
}
