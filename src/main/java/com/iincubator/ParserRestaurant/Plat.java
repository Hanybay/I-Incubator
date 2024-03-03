package com.iincubator.ParserRestaurant;
import java.util.List;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
// import jakarta.xml.bind.annotation.XmlRootElement;


import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Plat {

    @XmlElement(name = "Nom_plat")
    private String nomPlat;

    @XmlElementWrapper(name = "Liste_ingredients")
    @XmlElement(name = "Ingredient")
    private List<String> listeIngredients;

    @XmlElement(name = "Tarif")
    private String tarif;

}

