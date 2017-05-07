package com.alexbelogurow.dbcoursework.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.alexbelogurow.dbcoursework.Model.Patient;
import com.alexbelogurow.dbcoursework.Model.Person;

/**
 * Created by alexbelogurow on 07.05.17.
 */

public class DBHandler extends SQLiteOpenHelper {

    // DB version
    private static final int DATABASE_VERSION = 1;

    // DB name
    private static final String DATABASE_NAME = "Hospital";

    // Table names
    private static final String TABLE_PERSON = "Person",
            TABLE_PATIENT = "Patient",
            TABLE_TREATMENT = "Treatment";

    // Common column names
    private static final String KEY_PERSON_ID = "personID",
            KEY_PATIENT_ID = "patientID";

    // TABLE_PERSON - column names
    private static final String KEY_FULL_NAME = "fullName",
            KEY_BIRTH_DATE = "birthDate",
            KEY_SEX = "sex";

    // TABLE_PATIENT - column name
    private static final String KEY_BLOOD_TYPE = "bloodType",
            KEY_RH_FACTOR = "rhFactor",
            KEY_LOCATION = "location",
            KEY_JOB = "job",
            KEY_COMMENTS = "comments";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTablePerson = "CREATE TABLE " + TABLE_PERSON + "(" +
                KEY_PERSON_ID + " INTEGER PRIMARY KEY, " +
                KEY_FULL_NAME + " TEXT, " +
                KEY_BIRTH_DATE + " TEXT, " +
                KEY_SEX + " TEXT" + ")";

        String createTablePatient = "CREATE TABLE " + TABLE_PATIENT + "(" +
                KEY_PATIENT_ID + " INTEGER PRIMARY KEY, " +
                KEY_PERSON_ID + " INTEGER, " +
                KEY_BLOOD_TYPE + " TEXT, " +
                KEY_RH_FACTOR + " TEXT, " +
                KEY_LOCATION + " TEXT, " +
                KEY_JOB + " TEXT, " +
                KEY_COMMENTS + " TEXT, " +
                "FOREIGN KEY (" + KEY_PERSON_ID + ") REFERENCES " +
                TABLE_PERSON + "(" + KEY_PERSON_ID + "))";

        // TODO add createTablePatient and other
        db.execSQL(createTablePerson);
        db.execSQL(createTablePatient);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSON);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT);

        onCreate(db);
    }

    // =======================================================================
    // Work with TABLE_PERSON
    // =======================================================================
    private void addPerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues personValues = new ContentValues();
        personValues.put(KEY_FULL_NAME, person.getFullName());
        personValues.put(KEY_BIRTH_DATE, person.getBirthDate());
        personValues.put(KEY_SEX, person.getSex());

        db.insert(TABLE_PERSON, null, personValues);

    }


    // =======================================================================
    // Work with TABLE_PATIENT
    // =======================================================================
    public void addPatient(Patient patient, Person person) {
        addPerson(person);

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues patientValues = new ContentValues();
        // TODO patientValues.put(KEY_PERSON_ID, person.getPersonID());
        // TODO или добавить ID по последнему добавленному в PERSON
        patientValues.put(KEY_BLOOD_TYPE, patient.getBloodType());
        patientValues.put(KEY_RH_FACTOR, patient.getRhFactor());
        patientValues.put(KEY_LOCATION, patient.getLocation());
        patientValues.put(KEY_JOB, patient.getJob());
        patientValues.put(KEY_COMMENTS, patient.getComments());

        db.insert(TABLE_PATIENT, null, patientValues);

    }
}
