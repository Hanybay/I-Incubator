package com.iincubator.Entities;

// import java.util.HashSet;
// import java.util.List;
// import java.util.Set;
// import java.util.stream.Collectors;

//import jakarta.persistence.Column;
// import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
// import jakarta.persistence.Enumerated;
// import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data

public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idCompany;
    
    String name;

    public Entreprise(){

    }

    public Entreprise(Long id, String name){
        this.idCompany = id;
        this.name = name;
    }


    public Entreprise(String name){
        this.name = name;
    }

    /*Constructor used in case a client is only a visitor and doesn't want to be a member */
    // public Entreprise(String name, String lastname){
    //     this.username = name;
    //     this.lastname = lastname;
    // }

    // public Message(String email, String password, List<String> roles){
    //     this.email = email;
    //     this.password = password;
    //     this.roles.addAll(roles.stream().map(UserRole::valueOf).collect(Collectors.toList()));
    // }

    /*Constructor used in case a client wants to become a member */
    // public Message(String name, String lastname, String email, String password, List<String> roles){
    //     this.username = name;
    //     this.lastname = lastname;
    //     this.email = email;
    //     this.password = password;
    //     this.roles.addAll(roles.stream().map(UserRole::valueOf).collect(Collectors.toList()));
    // }

}
