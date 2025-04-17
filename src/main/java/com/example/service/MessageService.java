package com.example.service;

import com.example.repository.MessageRepository;
import com.example.entity.Message;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service //not transactional. 
public class MessageService {
    MessageRepository messageRepository;

    @Autowired
    MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message getMessageById(Integer id){
        
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if (optionalMessage.isPresent()){
            return optionalMessage.get();
        }
        System.out.println("it's nulllllllllllllllllllllllllllll");
        return null;
    }
}
