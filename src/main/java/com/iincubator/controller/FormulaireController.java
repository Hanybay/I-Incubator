package com.iincubator.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iincubator.Entities.Message;
import com.iincubator.Repositories.MessageRepository;

@RestController

public class FormulaireController {
    
    @Inject
    MessageRepository messageRepo;

    public FormulaireController(MessageRepository messageRepo) {
        this.messageRepo = messageRepo;
    }

    @PostMapping("/api/saveData")
    public void saveData(@RequestBody Message message) { 
        messageRepo.save(message);
    }
}
