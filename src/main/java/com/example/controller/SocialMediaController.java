package com.example.controller;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import com.example.service.AccountService;
import com.example.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//my imports here
import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    //@Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }

    //#4 get messages
    @GetMapping("/messages")
    public ResponseEntity getAllMessages(){
        List<Message> allMessages = messageService.getAllMessages();
        //System.out.println(allMessages);
        return ResponseEntity.status(200).body(allMessages);
    }

    //#5 get message by id
    @GetMapping("/messages/{id}")
    public ResponseEntity getMessageById(@PathVariable Integer id){
        Message message = messageService.getMessageById(id); //later, hook up.
        return ResponseEntity.status(200).body(message);
    }
}
