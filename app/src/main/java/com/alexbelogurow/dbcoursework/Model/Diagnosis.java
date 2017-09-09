package com.alexbelogurow.dbcoursework.Model;

/**
 * Created by alexbelogurow on 08.09.17.
 */

public class Diagnosis {
    private String ICD, name;
    private Integer isConfirmed;
    private boolean isSelected;

    public Diagnosis(String ICD, String name) {
        this.ICD = ICD;
        this.name = name;
        this.isConfirmed = 0;
        this.isSelected = false;
    }

    public Diagnosis(String ICD, String name, Integer isConfirmed) {
        this.ICD = ICD;
        this.name = name;
        this.isConfirmed = isConfirmed;
        this.isSelected = false;
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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public String toString() {
        return "Diagnosis{" +
                "ICD='" + ICD + '\'' +
                ", isSelected=" + isSelected +
                "}\n";
    }
}
