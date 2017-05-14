package com.alexbelogurow.dbcoursework.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.alexbelogurow.dbcoursework.Model.Patient;
import com.alexbelogurow.dbcoursework.Model.Person;

import java.util.ArrayList;
import java.util.List;

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
    private Integer addPerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues personValues = new ContentValues();
        personValues.put(KEY_FULL_NAME, person.getFullName());
        personValues.put(KEY_BIRTH_DATE, person.getBirthDate());
        personValues.put(KEY_SEX, person.getSex());

        return (int) db.insert(TABLE_PERSON, null, personValues);
    }

    public Person getPerson(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PERSON, new String[] {
                KEY_PERSON_ID, KEY_FULL_NAME, KEY_BIRTH_DATE, KEY_SEX}, KEY_PERSON_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Person person = new Person(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));

        return person;

    }





    // =======================================================================
    // Work with TABLE_PATIENT
    // =======================================================================
    public void addPatient(Patient patient, Person person) {
        Integer idPerson = addPerson(person);

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues patientValues = new ContentValues();
        patientValues.put(KEY_PERSON_ID, idPerson);
        patientValues.put(KEY_BLOOD_TYPE, patient.getBloodType());
        patientValues.put(KEY_RH_FACTOR, patient.getRhFactor());
        patientValues.put(KEY_LOCATION, patient.getLocation());
        patientValues.put(KEY_JOB, patient.getJob());
        patientValues.put(KEY_COMMENTS, patient.getComments());

        db.insert(TABLE_PATIENT, null, patientValues);
        db.close();

        Log.d("ADD", patient.toString());
    }

    public List<Patient> getAllPatients() {
        List<Patient> patientList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_PATIENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Patient patient = new Patient(
                        Integer.parseInt(cursor.getString(0)),  // KEY_PATIENT_ID
                        Integer.parseInt(cursor.getString(1)),  // KEY_PERSON_ID
                        cursor.getString(2),                    // KEY_BLOOD_TYPE
                        cursor.getString(3),                    // KEY_RH_FACTOR
                        cursor.getString(4),                    // KEY_LOCATION
                        cursor.getString(5),                    // KEY_JOB
                        cursor.getString(6));                   // KEY_COMMENTS
                patientList.add(patient);

                //Log.d("ADD", patient.toString());
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return patientList;
    }
}
