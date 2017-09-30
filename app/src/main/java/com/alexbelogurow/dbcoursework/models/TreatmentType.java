package com.alexbelogurow.dbcoursework.models;

import com.alexbelogurow.dbcoursework.R;

/**
 * Created by alexbelogurow on 30.09.2017.
 */

public enum TreatmentType {
    Medication ("Медикаментозный метод лечения"),
    Physiotherapeutic ("Физиотерапевтическое лечение"),
    Psychotherapeutic ("Психотерапевтическое лечение"),
    Surgical ("Хирургический метод лечения");

    private final String type;

    TreatmentType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return this.getType();
    }
}
