package com.LeoMitinskyi.lab6.types;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.time.LocalDate;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Person implements Serializable {
    private long id;
    private String name;
    private Coordinates coordinates;
    @XmlElement(name = "creationDate")
    private String dateTimeString;
    @XmlTransient
    private LocalDate creationDate;
    private Integer height;
    private Color eyeColor;
    private Color hairColor;
    private Country nationality;
    private Location location;
    private Friends friends;

    public Person(){}
    public Person(long id, String name, Coordinates coordinates, LocalDate creationDate, Integer height, Color eyeColor, Color hairColor, Country nationality, Location location, Friends friends){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.height = height;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;
        this.friends = friends;
    }

    public long getId() {
        return id;
    }

    public Friends getFriends(){
        return friends;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public float getX(){
        return getCoordinates().getX();
    }

    public LocalDate getCreationDate() {
        return LocalDate.now();
    }

    public Integer getHeight() {
        return height;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public Country getNationality() {
        return nationality;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", coordinates=" + getCoordinates() +
                ", creationDate=" + getCreationDate() +
                ", height=" + getHeight() +
                ", eyeColor=" + getEyeColor() +
                ", hairColor=" + getHairColor() +
                ", nationality=" + getNationality() +
                ", location=" + getLocation() +
                ", friends=" + getFriends()+
                '}' + '\n';
    }
}
