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
public class Menu {

    @XmlElementWrapper(name = "Liste_entree")
    @XmlElement(name = "Entree")
    private List<Entree> listeEntree;

    @XmlElementWrapper(name = "Liste_plat")
    @XmlElement(name = "Plat")
    private List<Plat> listePlat;

    @XmlElementWrapper(name = "Liste_dessert")
    @XmlElement(name = "Dessert")
    private List<Dessert> listeDessert;
}
