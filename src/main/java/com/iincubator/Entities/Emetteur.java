package com.iincubator.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
// import jakarta.persistence.Enumerated;
// import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
// import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Data
@EqualsAndHashCode(exclude = "message")
public class Emetteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmetteur;

    private String emailEmetteur;

    @JsonIgnore
    @ManyToOne
    private Message message;
    
    public Emetteur (){

    }

    public Emetteur(String email){
        this.emailEmetteur = email;
    }
    
    public Emetteur(String email, Message m){
        this.emailEmetteur = email;
        this.message = m;
    }
}
