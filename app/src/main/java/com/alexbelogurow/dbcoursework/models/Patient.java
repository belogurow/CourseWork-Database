package com.alexbelogurow.dbcoursework.models;

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

    public Patient(Integer patientID, Integer personID, Integer doctorID, String bloodType, String rhFactor, String location, String job,  String comments) {
        this.patientID = patientID;
        this.personID = personID;
        this.doctorID = doctorID;
        this.location = location;
        this.job = job;
        this.bloodType = bloodType;
        this.rhFactor = rhFactor;
        this.comments = comments;
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
