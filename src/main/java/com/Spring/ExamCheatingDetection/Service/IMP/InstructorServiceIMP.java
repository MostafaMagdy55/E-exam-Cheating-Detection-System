package com.Spring.ExamCheatingDetection.Service.IMP;

import com.Spring.ExamCheatingDetection.Entity.Instructor;
import com.Spring.ExamCheatingDetection.Entity.Student;
import com.Spring.ExamCheatingDetection.Repository.InstructorRepository;
import com.Spring.ExamCheatingDetection.Service.InstructorService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InstructorServiceIMP implements InstructorService {
    @Autowired
    InstructorRepository instructorRepository;
    @Override
    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor findById(int theId) {

        Optional<Instructor> result = instructorRepository.findById(theId);

        Instructor theStudent = null;

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
    public void save(Instructor instructor) {

       instructorRepository.save(instructor);
    }




    @Override
    public void save(Instructor instructor, MultipartFile[] files) {
        String name=instructor.getName();
        List<String>image=new ArrayList<>();
        File f=new File("D:\\Graduation Project   E-exam Cheating Detection System\\E-exam-Cheating-Detection-System\\images\\"+name);
        f.mkdirs();

        if (f.exists())
        {
            for (MultipartFile file : files) {
                Path fileNameAndPath = Paths.get(f.getPath(), file.getOriginalFilename());
                image.add(file.getOriginalFilename());
                instructor.setImage(image.get(0));
                try {
                    Files.write(fileNameAndPath, file.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        instructor.setPassword(encoder.encode(instructor.getPassword()));
        instructor.setRole("ROLE_INSTRUCTOR");
        instructor.setEnabled(true);
        instructorRepository.save(instructor);
    }





    @Override
    public void deleteById(int theId) {
        Optional<Instructor> result = instructorRepository.findById(theId);

        Instructor theInstuctor = null;

        if (result.isPresent()) {
            theInstuctor = result.get();
            String name=theInstuctor.getName();
            System.err.println(name);
            File f=new File("D:\\Graduation Project   E-exam Cheating Detection System\\ExamCheatingDetection\\images\\"+name);
            try {
                FileUtils.deleteDirectory(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // f.delete();
            instructorRepository.deleteById(theId);
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + theId);
        }
    }
}
