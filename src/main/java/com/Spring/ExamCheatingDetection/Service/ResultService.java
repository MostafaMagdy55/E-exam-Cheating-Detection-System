package com.Spring.ExamCheatingDetection.Service;

import com.Spring.ExamCheatingDetection.Entity.Result;

import java.util.List;

public interface ResultService {

    public List<Result> findAll();

    public Result findById(int theId);

    public void save(Result result);

    public void deleteById(int theId);
}
