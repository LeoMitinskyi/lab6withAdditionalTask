package com.LeoMitinskyi.lab6.types;

import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class People {
    @XmlElement(name = "person")
    public static LinkedList<Person> persons = new LinkedList<>();
    private final LocalDate initializationDate;

    public People() {
        initializationDate = LocalDate.now();
    }

    public void clear() {
        persons.clear();
    }

    public LocalDate getInitializationDate() {
        return initializationDate;
    }

    public int getSize() {
        return persons.size();
    }

    People people;

    public void loadFromFile(String filename) throws JAXBException {
        try {
            File file = new File(filename);
            JAXBContext context = JAXBContext.newInstance(People.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            people = (People) unmarshaller.unmarshal(file);
        } catch (UnmarshalException e){
            System.out.println("Invalid file");
            System.exit(0);
        }finally {
            CorrectDataReader();
        }
    }

    public void CorrectDataReader(){
        for(Person person: persons){
            if(!(person.getHeight() > 0) || (person.getName() == null) || (person.getName().equals("")) || person.getEyeColor() == null
                    || person.getHairColor() == null || person.getLocation() == null || person.getNationality() == null || person.getCoordinates().getX() > 962
                    || person.getCoordinates().getY() > 214 || person.getLocation().getX() == null || person.getLocation().getZ() == null){
                System.out.println("Invalid values in file");
                System.exit(0);
            }
        }
    }

    public void add(Person element) {
        persons.add(element);
    }

    public void save(){
        StringWriter sw = new StringWriter();
        try {
            People people = new People();
            JAXBContext context = JAXBContext.newInstance(People.class);
            Marshaller jaxbMarshaller = context.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.marshal(people, new File("Data.xml"));
            jaxbMarshaller.marshal(people, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void remove(long key) {
        Person element = get(key);
        persons.remove(element);
    }

    public Person get(long id) {
        try{
            return persons.stream().filter(entry -> entry.getId() == id).findFirst().get();
        }catch (NoSuchElementException exception){
            return null;
        }
    }

    public int findMaxHeight(){
        Person max = persons.stream().max(Comparator.comparing(Person::getHeight)).get();
        return max.getHeight();
    }

    public int findMinHeight(){
        Person min = persons.stream().min(Comparator.comparing(Person::getHeight)).get();
        return min.getHeight();
    }

    public String subStringSearcher(String subString){

        return persons
                .stream()
                .filter(entry -> entry.getName().startsWith(subString))
                .map(Person::toString)
                .reduce("", (a, b) -> a + b + "\n");
    }

    public int LocationCounter(Long X, double Y, Float Z){
        int count = 0;
        for (Person person : persons) {
            if (person.getLocation().getX().equals(X) && person.getLocation().getY() == Y && person.getLocation().getZ().equals(Z)){
                count = count + 1;
            }
        }
        return count;
    }

    public String show(){
        if (persons.isEmpty()) return "Коллекция пуста!";
        return persons.stream().reduce("",(sum,m)->sum += m, (a, b) -> a + b).trim();
    }

    public Person MaxName(){
        String minString = "";
        Person p = new Person();
        for (Person person : persons) {
            if(person.getName().compareToIgnoreCase(minString) > 0){
                minString = person.getName();
                p = person;
            }
        }
        return p;
    }

    public void sort() {
        persons.sort(Comparator.comparing(Person::getX));
    }
}
