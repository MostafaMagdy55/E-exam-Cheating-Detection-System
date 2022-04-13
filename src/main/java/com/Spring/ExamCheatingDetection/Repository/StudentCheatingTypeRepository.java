package com.Spring.ExamCheatingDetection.Repository;

import com.Spring.ExamCheatingDetection.Entity.StudentCheatingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCheatingTypeRepository extends JpaRepository<StudentCheatingType,Integer> {
    //StudentCheatingType findByExamId(int id);
}
