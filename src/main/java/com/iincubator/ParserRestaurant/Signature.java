package com.iincubator.ParserRestaurant;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
// import jakarta.xml.bind.annotation.XmlAccessType;
// import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
// import jakarta.xml.bind.annotation.XmlElementWrapper;
// import jakarta.xml.bind.annotation.XmlRootElement;


import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Signature {

    @XmlElement(name = "Nom_entreprise")
    private String nomEntreprise;

    @XmlElement(name = "Email")
    private String email;

    @XmlElement(name = "Num_tel")
    private String numTel;

    @XmlElement(name = "Adresse_postale")
    private AdressePostale adresse;
}