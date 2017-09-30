package com.alexbelogurow.dbcoursework.model;

/**
 * Created by alexbelogurow on 30.09.2017.
 */

public class SideEffect {
    private Integer sideEffectId, treatmentId;
    private String name, comments;

    public SideEffect(Integer sideEffectId, Integer treatmentId, String name, String comments) {
        this.sideEffectId = sideEffectId;
        this.treatmentId = treatmentId;
        this.name = name;
        this.comments = comments;
    }

    public Integer getSideEffectId() {
        return sideEffectId;
    }

    public void setSideEffectId(Integer sideEffectId) {
        this.sideEffectId = sideEffectId;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "SideEffect{" +
                "sideEffectId=" + sideEffectId +
                ", treatmentId=" + treatmentId +
                ", name='" + name + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
