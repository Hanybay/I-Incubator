package com.iincubator.ParserRestaurant;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
//import jakarta.xml.bind.annotation.XmlElementWrapper;
//import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Messages {

    @XmlElement(name = "message")
    private List<MessageRestau> messageList;
}