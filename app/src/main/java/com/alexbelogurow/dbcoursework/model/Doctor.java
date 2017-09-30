package com.alexbelogurow.dbcoursework.model;

/**
 * Created by alexbelogurow on 14.05.17.
 */

public class Doctor {
    private Integer doctorID, personID;
    private String specialization, practiseBeganDate;

    public Doctor(Integer doctorID, Integer personID, String specialization, String practiseBeganDate) {
        this.doctorID = doctorID;
        this.personID = personID;
        this.specialization = specialization;
        this.practiseBeganDate = practiseBeganDate;
    }

    public Doctor(String specialization, String practiseBeganDate) {
        this.specialization = specialization;
        this.practiseBeganDate = practiseBeganDate;
    }

    public Integer getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(Integer doctorID) {
        this.doctorID = doctorID;
    }

    public Integer getPersonID() {
        return personID;
    }

    public void setPersonID(Integer personID) {
        this.personID = personID;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getPractiseBeganDate() {
        return practiseBeganDate;
    }

    public void setPractiseBeganDate(String practiseBeganDate) {
        this.practiseBeganDate = practiseBeganDate;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorID=" + doctorID +
                ", personID=" + personID +
                ", specialization='" + specialization + '\'' +
                ", practiceBeganDate='" + practiseBeganDate + '\'' +
                '}';
    }
}
