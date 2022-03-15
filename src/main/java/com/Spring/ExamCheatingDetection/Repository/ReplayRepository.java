package com.Spring.ExamCheatingDetection.Repository;

import com.Spring.ExamCheatingDetection.Entity.Replay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplayRepository extends JpaRepository<Replay,Integer> {
}
