package com.iincubator.Entities;

import java.util.ArrayList;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

import java.util.List;
// import java.util.stream.Collectors;
// import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
// import jakarta.persistence.Enumerated;
// import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long conversationId;

    @Column(name = "convDate")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy") // pas du JPA
    Date date;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
    private List<Message> messages = new ArrayList<>();

    @OneToOne
    Entreprise Orecepteur;

    @OneToOne
    Entreprise Oemettrice;

    @OneToMany
    private List<Destinataire> destinataires = new ArrayList<>();


    public Conversation(){

    }

    public Conversation(String name,Entreprise receptrice,Date beginDate,List <Message> messList){
        // this.Convname = name;
        this.Orecepteur = receptrice;
        this.date = beginDate;
        this.messages = messList;
    }


}
