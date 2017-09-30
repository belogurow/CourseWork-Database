package com.alexbelogurow.dbcoursework.model;

/**
 * Created by alexbelogurow on 30.09.2017.
 */

public class Treatment {
    private Integer treatmentId;
    private String name, treatmentType;


    public Treatment(String name, String treatmentType) {
        this.name = name;
        this.treatmentType = treatmentType;
    }

    public Treatment(Integer treatmentId, String name, String treatmentType) {
        this.treatmentId = treatmentId;
        this.name = name;
        this.treatmentType = treatmentType;
    }

    public Integer getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(Integer treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTreatmentType() {
        return treatmentType;
    }

    public void setTreatmentType(String treatmentType) {
        this.treatmentType = treatmentType;
    }

    @Override
    public String toString() {
        return "Treatment{" +
                "treatmentId=" + treatmentId +
                ", name='" + name + '\'' +
                ", treatmentType='" + treatmentType + '\'' +
                '}';
    }
}
