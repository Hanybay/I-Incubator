package com.iincubator.ParserRestaurant;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;


@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class MessageRestau {
    
    @XmlElement(name = "Contenu")
    private Contenu contenu;
    
}