package com.iincubator.ParserRestaurant;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
// import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Dessert {

    @XmlElement(name = "Nom_dessert")
    private String nomDessert;

    @XmlElementWrapper(name = "Liste_ingredients")
    @XmlElement(name = "Ingredient")
    private List<String> listeIngredients;

    @XmlElement(name = "Tarif")
    private String tarif;
}