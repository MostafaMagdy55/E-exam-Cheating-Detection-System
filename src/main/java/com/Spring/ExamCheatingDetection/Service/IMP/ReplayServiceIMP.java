package com.Spring.ExamCheatingDetection.Service.IMP;

import com.Spring.ExamCheatingDetection.Entity.Replay;
import com.Spring.ExamCheatingDetection.Repository.ReplayRepository;
import com.Spring.ExamCheatingDetection.Service.ReplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReplayServiceIMP implements ReplayService {
    @Autowired
    ReplayRepository replayRepository;

    @Override
    public List<Replay> findAll() {
        return replayRepository.findAll();
    }

    @Override
    public Replay findById(int theId) {
        Optional<Replay> result = replayRepository.findById(theId);

        Replay replay = null;

        if (result.isPresent()) {
            replay = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find replay - " + theId);
        }

        return replay;
    }

    @Override
    public void save(Replay replay) {
        replayRepository.save(replay);

    }

    @Override
    public void deleteById(int theId) {

    }
}
