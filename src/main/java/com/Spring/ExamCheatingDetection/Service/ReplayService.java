package com.Spring.ExamCheatingDetection.Service;

import com.Spring.ExamCheatingDetection.Entity.Replay;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReplayService {
    public List<Replay> findAll();

    public Replay findById(int theId);

    public void save(Replay replay);

    public void deleteById(int theId);
}
