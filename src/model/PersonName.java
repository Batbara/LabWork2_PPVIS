package model;

/**
 * Created by Batbara on 04.04.2017.
 */
public class PersonName{
    private String personName;
    private String personSurname;
    private String personFatherName;

    public PersonName(String name, String surname, String fatherName){
        personName = name;
        personSurname = surname;
        personFatherName = fatherName;
    }
    public PersonName(){
        personName="";
        personSurname="";
        personFatherName="";
    }
    public PersonName(String rawName){
        String []personNameArray = rawName.split("\\w", 3);
        personSurname = personNameArray[0];
        personName = personNameArray[1];
        personFatherName = personNameArray[3];
    }
    public void setPersonName (String name) {
        personName = name;
    }
    public void setPersonSurname (String surname){
        personSurname = surname;
    }
    public void setPersonFatherName(String fatherName){
        personFatherName = fatherName;
    }
    public String getPersonName(){
        return personName;
    }
    public String getPersonSurname(){
        return personSurname;
    }
    public String getPersonFatherName(){
        return personFatherName;
    }
    public String getShortName(){
        String shortName;
        shortName = getPersonSurname()+" "+getPersonName().charAt(0)+". "+getPersonFatherName().charAt(0)+".";
        return shortName;
    }

}
