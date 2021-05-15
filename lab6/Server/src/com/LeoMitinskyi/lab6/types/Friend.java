package com.LeoMitinskyi.lab6.types;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Friend {
    String name;

    Friend(){}
    public Friend(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "friend{" +
                ", name='" + getName() + '\n' + "}";
    }
}
