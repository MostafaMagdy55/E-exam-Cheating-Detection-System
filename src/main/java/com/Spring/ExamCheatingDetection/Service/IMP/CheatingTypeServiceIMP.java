package com.Spring.ExamCheatingDetection.Service.IMP;

import com.Spring.ExamCheatingDetection.Entity.CheatingType;
import com.Spring.ExamCheatingDetection.Repository.CheatingTypeRepository;
import com.Spring.ExamCheatingDetection.Service.CheatingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CheatingTypeServiceIMP implements CheatingTypeService {

    @Autowired
    CheatingTypeRepository cheatingTypeRepository;


    @Override
    public List<CheatingType> findAll() {
        return null;
    }

    @Override
    public CheatingType findById(int theId) {
        return null;
    }

    @Override
    public void save(CheatingType cheatingType) {
        cheatingTypeRepository.save(cheatingType);
    }

    @Override
    public void deleteById(int theId) {

    }
}
