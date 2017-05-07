package com.alexbelogurow.dbcoursework.Model;

/**
 * Created by alexbelogurow on 07.05.17.
 */

public class Person {
    private Integer personID;
    private String fullName, birthDate, sex;


    public Person(Integer personID, String fullName, String birthDate, String sex) {
        this.personID = personID;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.sex = sex;
    }

    public Integer getPersonID() {
        return personID;
    }

    public void setPersonID(Integer personID) {
        this.personID = personID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personID=" + personID +
                ", fullName='" + fullName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
