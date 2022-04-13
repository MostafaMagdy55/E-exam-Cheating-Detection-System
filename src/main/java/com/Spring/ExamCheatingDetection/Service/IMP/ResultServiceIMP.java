package com.Spring.ExamCheatingDetection.Service.IMP;


import com.Spring.ExamCheatingDetection.Entity.Result;
import com.Spring.ExamCheatingDetection.Repository.ResultRepository;
import com.Spring.ExamCheatingDetection.Service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultServiceIMP implements ResultService {

    @Autowired
    ResultRepository resultRepository;

    @Override
    public List<Result> findAll() {
        return resultRepository.findAll();
    }

    @Override
    public Result findById(int theId) {
        Optional<Result> result = resultRepository.findById(theId);

        Result result1 = null;

        if (result.isPresent()) {
            result1 = result.get();
        }
        else {
            // we didn't find the result
            throw new RuntimeException("Did not find result - " + theId);
        }

        return result1;
    }

    @Override
    public void save(Result result) {
        resultRepository.save(result);

    }

    @Override
    public void deleteById(int theId) {

    }
}
