package com.iincubator.ParserRestaurant;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class AdressePostale {

    @XmlElement(name = "Numero_rue")
    private String numeroRue;

    @XmlElement(name = "Rue")
    private String rue;

    @XmlElement(name = "Code_postal")
    private String codePostal;
}
