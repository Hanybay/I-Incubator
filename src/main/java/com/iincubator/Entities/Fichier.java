package com.iincubator.Entities;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Fichier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFichier;

    // private String NomFichier;

    // @OneToMany(mappedBy = "fichier", cascade = CascadeType.ALL)
    // private List<Message> messages;

    // @ManyToOne
    // @JoinColumn(name = "entreprise_emettrice_id")
    // private Entreprise entrepriseEmettrice;
    
    public Fichier() {
        // Constructeur par défaut nécessaire pour JPA
    }

    // Autres méthodes si nécessaire
}