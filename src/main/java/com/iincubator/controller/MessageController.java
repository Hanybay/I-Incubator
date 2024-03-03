package com.iincubator.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iincubator.Entities.Message;
import com.iincubator.Repositories.MessageRepository;

@RestController
@RequestMapping("/api/messages")

public class MessageController {
    
    @Autowired
    MessageRepository messageRepo;

     @GetMapping("/all")
    public Iterable<Message> getAllMessages() {
        return messageRepo.findAll();
    }

}
