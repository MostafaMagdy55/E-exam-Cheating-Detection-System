package com.Spring.ExamCheatingDetection.Service;


import com.Spring.ExamCheatingDetection.Entity.CheatingType;

import java.util.List;

public interface CheatingTypeService  {


    public List<CheatingType> findAll();

    public CheatingType findById(int theId);

    public void save(CheatingType cheatingType);

    public void deleteById(int theId);
}
