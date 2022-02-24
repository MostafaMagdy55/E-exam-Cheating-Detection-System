package com.Spring.ExamCheatingDetection.config;

import com.Spring.ExamCheatingDetection.Entity.Admin;
import com.Spring.ExamCheatingDetection.Entity.Instructor;
import com.Spring.ExamCheatingDetection.Entity.Person;
import com.Spring.ExamCheatingDetection.Entity.Student;
import com.Spring.ExamCheatingDetection.Repository.AdminRepository;
import com.Spring.ExamCheatingDetection.Repository.InstructorRepository;
import com.Spring.ExamCheatingDetection.Repository.StudentRepository;
import com.Spring.ExamCheatingDetection.Service.InstructorService;
import com.Spring.ExamCheatingDetection.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
//    private UserRepository userRepository;
//
//    public UserPrincipalDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
    @Autowired
    StudentRepository  studentRepository;
    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        User user = this.userRepository.findByUsername(s);
//        UserPrincipal userPrincipal = new UserPrincipal(user);

        Person student=studentRepository.findByName(s);
        Person instructor=instructorRepository.findByName(s);
        Person admin=adminRepository.findByName(s);


        if (student!= null)
        {
            UserPrincipal userPrincipal = new UserPrincipal(student);
            return userPrincipal;
        }

        else if(instructor !=null)
        {
                UserPrincipal userPrincipal = new UserPrincipal(instructor);
                return userPrincipal;
        }

        else
            {

                UserPrincipal userPrincipal = new UserPrincipal(admin);
                return userPrincipal;
            }

        }



    }
