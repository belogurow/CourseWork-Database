package com.alexbelogurow.dbcoursework.Model;

/**
 * Created by alexbelogurow on 08.09.17.
 */

public class Diagnosis {
    private String ICD, name;
    private Integer isConfirmed;

    public Diagnosis(String ICD, String name) {
        this.ICD = ICD;
        this.name = name;
        this.isConfirmed = 0;
    }

    public Diagnosis(String ICD, String name, Integer isConfirmed) {
        this.ICD = ICD;
        this.name = name;
        this.isConfirmed = isConfirmed;
    }

    public String getICD() {
        return ICD;
    }

    public void setICD(String ICD) {
        this.ICD = ICD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(Integer isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    @Override
    public String toString() {
        return "Diagnosis{" +
                "ICD='" + ICD + '\'' +
                ", name='" + name + '\'' +
                ", isConfirmed=" + isConfirmed +
                '}';
    }
}
