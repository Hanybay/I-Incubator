package com.iincubator.ParserRestaurant;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement(name = "Contenu")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Contenu {

    @XmlElement(name = "Marque_de_politesses")
    private String marqueDePolitesses;

    @XmlElement(name = "Description_entreprise")
    private String descriptionEntreprise;

    @XmlElement(name = "Presentation_prestation")
    private PresentationPrestation presentationPrestation;

    @XmlElement(name = "Formule_politesse")
    private String formulePolitesses;

    @XmlElement(name = "Signature")
    private Signature signature;

}