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

    public Message createMessage(Message message){
        Integer user = message.getPostedBy();
        //the following condition checks if the message is null, if the length is between 0 and 255, and if user id exists. 
        if(message!=null && message.getMessageText().length() != 0 && message.getMessageText().length() < 255 && true ){ //add check for final condition later.
            messageRepository.save(message);
            return message;
        }else{
            return null;
        }
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message getMessageById(Integer id){
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if (optionalMessage.isPresent()){
            return optionalMessage.get();
        }
        return null;
    }

    public Message deleteMessageById(Integer id){     
        Message message = getMessageById(id);
        if (message != null){
            messageRepository.deleteById(id);
        }
        return message;
    }
    
    public Message updateMessageById(Integer id, Message message){
        message.setMessageId(id);
        Integer user = message.getPostedBy();
        //the following condition checks if the message is null, if the length is between 0 and 255, and if user id exists. 
        if(message!=null && message.getMessageText().length() != 0 && message.getMessageText().length() < 255 && messageRepository.findById(id)!=null ){ //add check for final condition later.
            messageRepository.save(message);
            return message;
        }else{
            return null;
        }
    }

    public List<Message> getMessagesByUser(Integer postedBy){        
        List<Message> Messages = messageRepository.findMessagesByPostedBy(postedBy);
        return Messages;
    }
}
