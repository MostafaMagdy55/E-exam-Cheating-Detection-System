package com.Spring.ExamCheatingDetection.Repository;

import com.Spring.ExamCheatingDetection.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findByName(String name);

//    @Query("SELECT m FROM Student m WHERE m.nameing LIKE ?1%")
//    List<Student>  searchByNameStartsWith( String nameing);

    List<Student>findByNameLike(String s);
}
