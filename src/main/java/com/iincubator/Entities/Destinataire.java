package com.iincubator.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(exclude = "message")
public class Destinataire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDestinataire;

    private String emailDestinataire;

@JsonIgnore
@ManyToOne
@JoinColumn(name = "message_id")
private Message message;

    public Destinataire(){

    }

    public Destinataire(String email){
        this.emailDestinataire = email;
    }

    public Destinataire(String email,Message m){
        this.emailDestinataire = email;
        this.message = m;
    }
}
