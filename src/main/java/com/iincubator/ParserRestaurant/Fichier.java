package com.iincubator.ParserRestaurant;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
//import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "fichier")
@Data
public class Fichier {

    @XmlElement(name = "messages")
    private Messages messages;

}