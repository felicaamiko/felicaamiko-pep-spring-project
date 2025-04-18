package com.example.controller;

import com.example.entity.Message;
import com.example.entity.Account;

import com.example.repository.MessageRepository;
import com.example.service.AccountService;
import com.example.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

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

    //#1 create account
    @PostMapping("/register")
    public ResponseEntity createAccount(@RequestBody Account account){
        Account createdAccount = accountService.createAccount(account);
        if (createdAccount != null){
            return ResponseEntity.status(200).body(account);
        }
        else{
            return ResponseEntity.status(400).body("");
        }
    }

    //#3 create message
    @PostMapping("/messages")
    public ResponseEntity createMessage(@RequestBody Message message){
        Message createdMessage = messageService.createMessage(message);
        if (createdMessage != null){
            return ResponseEntity.status(200).body(message);
        }
        else{
            return ResponseEntity.status(400).body("");
        }
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
        Message message = messageService.getMessageById(id); 
        return ResponseEntity.status(200).body(message);
    }

    //#6 delete message by id
    @DeleteMapping("/messages/{id}")
    public ResponseEntity deleteMessageById(@PathVariable Integer id){
        Message message = messageService.deleteMessageById(id); 
        return ResponseEntity.status(200).body(message);
    }

    //#7 update message by id
    @PatchMapping("/messages/{id}")
    public ResponseEntity updateMessageById(@PathVariable Integer id, @RequestBody Message message){
        Message updatedMessage = messageService.updateMessageById(id, message); 
        if (updatedMessage != null){
            return ResponseEntity.status(200).body(message);
        }
        else{
            return ResponseEntity.status(400).body("");
        }
    }

    //#8 get messages by user
    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity getMessagesByUser(@PathVariable Integer accountId){
        List<Message> Messages = messageService.getMessagesByUser(accountId); 
        return ResponseEntity.status(200).body(Messages);
    }
}
