package com.Spring.ExamCheatingDetection.Service.IMP;

import com.Spring.ExamCheatingDetection.Entity.Instructor;
import com.Spring.ExamCheatingDetection.Repository.InstructorRepository;
import com.Spring.ExamCheatingDetection.Service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        instructor.setPassword(encoder.encode(instructor.getPassword()));
        instructor.setRole("ROLE_INSTRUCTOR");
        instructor.setEnabled(true);
        instructorRepository.save(instructor);
    }

    @Override
    public void deleteById(int theId) {
        Optional<Instructor> result = instructorRepository.findById(theId);

        Instructor theStudent = null;

        if (result.isPresent()) {
            theStudent = result.get();
            instructorRepository.deleteById(theId);
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + theId);
        }
    }
}
