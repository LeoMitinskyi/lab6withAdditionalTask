package com.LeoMitinskyi.lab6.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Friends {
    @XmlElement(name = "friend")
    public static LinkedList<Friend> friends = new LinkedList<>();

    public Friends(){}

    public void AddFriend(String name){
        Friend friend = new Friend(name);
        friends.add(friend);
    }

    @Override
    public String toString() {
        return friends.toString();
    }
}
