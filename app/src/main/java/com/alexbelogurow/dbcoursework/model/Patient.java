package com.alexbelogurow.dbcoursework.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by alexbelogurow on 07.05.17.
 */

public class Patient {
    private Integer patientID, personID, doctorID;
    private String location, job, bloodType, rhFactor, comments;

    public Patient(Integer patientID, Integer personID, String bloodType, String rhFactor, String location, String job,  String comments) {
        this.patientID = patientID;
        this.personID = personID;
        this.location = location;
        this.job = job;
        this.bloodType = bloodType;
        this.rhFactor = rhFactor;
        this.comments = comments;
    }

    public Patient(String bloodType, String rhFactor, String location, String job,  String comments) {
        this.location = location;
        this.job = job;
        this.bloodType = bloodType;
        this.rhFactor = rhFactor;
        this.comments = comments;
    }

    public Patient(String location) {
        this.location = location;
        this.job = this.generateJob();
        this.bloodType = this.generateBloodType();
        this.rhFactor = this.generateRhFactor();
        this.comments = "";
    }

    public Patient(Integer patientID, Integer personID, Integer doctorID, String bloodType, String rhFactor, String location, String job, String comments) {
        this.patientID = patientID;
        this.personID = personID;
        this.doctorID = doctorID;
        this.location = location;
        this.job = job;
        this.bloodType = bloodType;
        this.rhFactor = rhFactor;
        this.comments = comments;
    }

    private String generateJob() {
        List<String> jobs = new ArrayList<String>() {{
            add("Художник");
            add("Режиссер");
            add("Водитель");
            add("Учитель начальных классов");
            add("Инженер-электрик");
            add("Агроном");
            add("Механик");
            add("Редактор");
            add("Эколог");
            add("Программист");
            add("Дизайнер");
            add("Флорист");
            add("Аналитик");
        }};

        return jobs.get(new Random().nextInt(jobs.size()));
    }

    private String generateBloodType() {
        List<String> bloodTypes = new ArrayList<String>() {{
           add("0");
           add("A");
           add("B");
           add("AB");
        }};

        return bloodTypes.get(new Random().nextInt(bloodTypes.size()));
    }

    private String generateRhFactor() {
        List<String> rhFactors = new ArrayList<String>() {{
           add("Positive");
           add("Negative");
        }};

        return rhFactors.get(new Random().nextInt(rhFactors.size()));
    }

    public Integer getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(Integer doctorID) {
        this.doctorID = doctorID;
    }

    public Integer getPatientID() {
        return patientID;
    }

    public void setPatientID(Integer patientID) {
        this.patientID = patientID;
    }

    public Integer getPersonID() {
        return personID;
    }

    public void setPersonID(Integer personID) {
        this.personID = personID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getRhFactor() {
        return rhFactor;
    }

    public void setRhFactor(String rhFactor) {
        this.rhFactor = rhFactor;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientID=" + patientID +
                ", personID=" + personID +
                ", location='" + location + '\'' +
                ", job='" + job + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", rhFactor='" + rhFactor + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }

    public String getPatientInfo() {
        return location + "\n" +
                job + "\n" +
                comments;

    }

    public String getPatientMedicalInfo() {
        return "Группа Крови - " + bloodType + "\n" +
                "Резус Фактор - " + rhFactor;
    }
}
