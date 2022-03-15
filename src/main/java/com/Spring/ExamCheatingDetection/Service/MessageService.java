package com.Spring.ExamCheatingDetection.Service;

import com.Spring.ExamCheatingDetection.Entity.Message;

import java.util.List;

public interface MessageService {

    public List<Message> findAll();

    public Message findById(int theId);

    public void save(Message message);

    public void deleteById(int theId);
}
