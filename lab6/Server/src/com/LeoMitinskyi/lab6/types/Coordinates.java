package com.LeoMitinskyi.lab6.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "coordinates")
@XmlAccessorType(XmlAccessType.FIELD)
public class Coordinates implements Serializable {
    private float x;
    private float y;
    public Coordinates(float x, float y){
        this.x = x;
        this.y = y;
    }
    Coordinates(){}
    public float getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + getX() +
                ", y=" + getY() +
                '}';
    }
}
