package com.Spring.ExamCheatingDetection;

import com.Spring.ExamCheatingDetection.Service.CourseService;
import com.Spring.ExamCheatingDetection.Service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;

public class testPassword {
@Autowired
   private static CourseService courseService;
@Autowired
private static ExamService examService;
    public static void main(String[] args) {


//        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
//        System.out.println(encoder.encode("mostafa")+" mostafa");


   //     Course course=courseService.findById(0);

     //   System.err.println(new Date());

        LocalTime localTime=LocalTime.now();

        System.out.println(localTime.getHour());
        System.out.println(localTime.getMinute());
    }
}
