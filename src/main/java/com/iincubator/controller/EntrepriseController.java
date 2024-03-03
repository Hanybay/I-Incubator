package com.iincubator.controller;

import org.springframework.web.bind.annotation.RestController;

import com.iincubator.Entities.Entreprise;
import com.iincubator.Repositories.EntrepriseRepo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/entreprises")
public class EntrepriseController {
    @Autowired
    private EntrepriseRepo entrepriseRepository;

    @GetMapping("/all")
    public Iterable<Entreprise> getAllEntreprises() {
        return entrepriseRepository.findAll();
    }
    
}
