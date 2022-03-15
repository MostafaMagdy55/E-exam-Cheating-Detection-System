package com.Spring.ExamCheatingDetection.Service.IMP;

import com.Spring.ExamCheatingDetection.Entity.Message;
import com.Spring.ExamCheatingDetection.Repository.MessageRepository;
import com.Spring.ExamCheatingDetection.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceIMP implements MessageService {

    @Autowired
    MessageRepository messageRepository;
    @Override
    public List<Message> findAll() {
        if( messageRepository.findAll()==null)
        {
            return  new ArrayList<>();
        }
        return messageRepository.findAll();
    }

    @Override
    public Message findById(int theId) {
        Optional<Message> result = messageRepository.findById(theId);

        Message message = null;

        if (result.isPresent()) {
            message = result.get();
        } else {
            // we didn't find the employee
            throw new RuntimeException("Did not find Message - " + theId);
        }

        return message;
    }



    @Override
    public void save(Message message) {
        messageRepository.save(message);
    }

    @Override
    public void deleteById(int theId) {

    }
}
