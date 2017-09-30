package com.alexbelogurow.dbcoursework.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.alexbelogurow.dbcoursework.model.Diagnosis;
import com.alexbelogurow.dbcoursework.model.Doctor;
import com.alexbelogurow.dbcoursework.model.Patient;
import com.alexbelogurow.dbcoursework.model.Person;
import com.alexbelogurow.dbcoursework.model.Treatment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexbelogurow on 07.05.17.
 */

public class DBHandler extends SQLiteOpenHelper {

    private static DBHandler sInstance;

    // DB version
    private static final int DATABASE_VERSION = 2;

    // DB name
    private static final String DATABASE_NAME = "Hospital";

    // Table names
    private static final String TABLE_PERSON = "Person",
            TABLE_PATIENT = "Patient",
            TABLE_TREATMENT = "Treatment",
            TABLE_SIDE_EFFECT = "SideEffect",
            TABLE_DIAGNOSIS = "Diagnosis",
            TABLE_DOCTOR = "Doctor",
            TABLE_PATIENT_WITH_DIAGNOSIS = "PatientWithDiagnosis",
            TABLE_DIAGNOSIS_WITH_TREATMENTS = "DiagnosisWithTreatments";

    // Common column names
    private static final String KEY_PERSON_ID = "personID",
            KEY_PATIENT_ID = "patientID",
            KEY_DOCTOR_ID = "doctorID",
            KEY_ICD = "ICD",
            KEY_TREATMENT_ID = "treatmentID",
            KEY_COMMENTS = "comments";

    // TABLE_PERSON - column names
    private static final String KEY_FULL_NAME = "fullName",
            KEY_BIRTH_DATE = "birthDate",
            KEY_SEX = "sex";

    // TABLE_PATIENT - column name
    private static final String KEY_BLOOD_TYPE = "bloodType",
            KEY_RH_FACTOR = "rhFactor",
            KEY_LOCATION = "location",
            KEY_JOB = "job";

    // TABLE_DOCTOR
    private static final String KEY_SPECIALIZATION = "specialization",
            KEY_PRACTICE_BEGAN_DATE = "practiceBeganDate";

    // TABLE_DIAGNOSIS
    private static final String KEY_DIAGNOSIS_NAME = "diagnosisName",
            KEY_IS_CONFIRMED = "isConfirmed";

    // TABLE TREATMENT
    private static final String KEY_TREATMENT_NAME = "treatmentName",
            KEY_TREATMENT_TYPE = "treatmentType";

    // TABLE SIDE EFFECT
    private static final String KEY_SIDE_EFFECT_ID = "sideEffectId",
            KEY_SIDE_EFFECT_NAME = "sideEffectName";


    public static synchronized DBHandler getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DBHandler(context.getApplicationContext());
        }
        return sInstance;
    }

    private DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String createTablePerson = "CREATE TABLE " + TABLE_PERSON + "(" +
                KEY_PERSON_ID + " INTEGER PRIMARY KEY, " +
                KEY_FULL_NAME + " TEXT, " +
                KEY_BIRTH_DATE + " TEXT, " +
                KEY_SEX + " TEXT" + ")";

        final String createTablePatient = "CREATE TABLE " + TABLE_PATIENT + "(" +
                KEY_PATIENT_ID + " INTEGER PRIMARY KEY, " +
                KEY_PERSON_ID + " INTEGER, " +
                KEY_DOCTOR_ID + " INTEGER, " +
                KEY_BLOOD_TYPE + " TEXT, " +
                KEY_RH_FACTOR + " TEXT, " +
                KEY_LOCATION + " TEXT, " +
                KEY_JOB + " TEXT, " +
                KEY_COMMENTS + " TEXT, " +
                "FOREIGN KEY (" + KEY_PERSON_ID + ") REFERENCES " +
                TABLE_PERSON + "(" + KEY_PERSON_ID + ") ON DELETE CASCADE, " +
                "FOREIGN KEY (" + KEY_DOCTOR_ID + ") REFERENCES " +
                TABLE_DOCTOR + "(" + KEY_DOCTOR_ID + "))";

        final String createTableDoctor = "CREATE TABLE " + TABLE_DOCTOR + "(" +
                KEY_DOCTOR_ID + " INTEGER PRIMARY KEY, " +
                KEY_PERSON_ID + " INTEGER, " +
                KEY_SPECIALIZATION + " TEXT, " +
                KEY_PRACTICE_BEGAN_DATE + " TEXT, " +
                "FOREIGN KEY (" + KEY_PERSON_ID + ") REFERENCES " +
                TABLE_PERSON + "(" + KEY_PERSON_ID + "))";

        final String createTableDiagnosis = "CREATE TABLE " + TABLE_DIAGNOSIS + "(" +
                KEY_ICD + " TEXT PRIMARY KEY, " +
                KEY_DIAGNOSIS_NAME + " TEXT, " +
                KEY_IS_CONFIRMED + " INTEGER " + ")";

        final String createTablePatientWithDiagnosis = "CREATE TABLE " + TABLE_PATIENT_WITH_DIAGNOSIS + "(" +
                KEY_PATIENT_ID + " INTEGER, " +
                KEY_ICD + " TEXT, " +
                "FOREIGN KEY (" + KEY_PATIENT_ID + ") REFERENCES " +
                TABLE_PATIENT + "(" + KEY_PATIENT_ID + ")," +
                "FOREIGN KEY (" + KEY_ICD + ") REFERENCES " +
                TABLE_DIAGNOSIS + "(" + KEY_ICD + "))";

        final String createTableTreatment = "CREATE TABLE " + TABLE_TREATMENT + "(" +
                KEY_TREATMENT_ID + " INTEGER PRIMARY KEY, " +
                KEY_TREATMENT_NAME + " TEXT, " +
                KEY_TREATMENT_TYPE + " TEXT " + ")";

        final String createTableSideEffect = "CREATE TABLE " + TABLE_SIDE_EFFECT + "(" +
                KEY_SIDE_EFFECT_ID + " INTEGER PRIMARY KEY, " +
                KEY_TREATMENT_ID + " INTEGER, " +
                KEY_SIDE_EFFECT_NAME + " TEXT, " +
                KEY_COMMENTS + " TEXT, " +
                "FOREIGN KEY (" + KEY_TREATMENT_ID  + ") REFERENCES " +
                TABLE_TREATMENT + "(" + KEY_TREATMENT_ID + "))";

        final String createTableDiagnosisWithTreatments = "CREATE TABLE " + TABLE_DIAGNOSIS_WITH_TREATMENTS + "(" +
                KEY_ICD + " TEXT, " +
                KEY_TREATMENT_ID + " INTEGER, " +
                "FOREIGN KEY (" + KEY_ICD + ") REFERENCES " +
                TABLE_DIAGNOSIS + "(" + KEY_ICD + ") " +
                "FOREIGN KEY (" + KEY_TREATMENT_ID + ") REFERENCES " +
                TABLE_TREATMENT + "(" + KEY_TREATMENT_ID + "))";


        // add createTablePatient and other
        db.execSQL(createTablePerson);
        db.execSQL(createTablePatient);
        db.execSQL(createTableDoctor);
        db.execSQL(createTableDiagnosis);
        db.execSQL(createTablePatientWithDiagnosis);
        db.execSQL(createTableTreatment);
        db.execSQL(createTableDiagnosisWithTreatments);
        db.execSQL(createTableSideEffect);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSON);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCTOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DIAGNOSIS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT_WITH_DIAGNOSIS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TREATMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DIAGNOSIS_WITH_TREATMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SIDE_EFFECT);

        onCreate(db);
    }

    // =======================================================================
    // Work with TABLE_PERSON
    // =======================================================================
    private Integer addPerson(Person person, SQLiteDatabase db) {
        ContentValues personValues = new ContentValues();
        personValues.put(KEY_FULL_NAME, person.getFullName());
        personValues.put(KEY_BIRTH_DATE, person.getBirthDate());
        personValues.put(KEY_SEX, person.getSex());

        Log.d("DB Add new person", person.toString());
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
        cursor.close();
        db.close();
        return person;

    }

    // =======================================================================
    // Work with TABLE_PATIENT
    // =======================================================================
    public Integer addPatient(Patient patient, Person person) {
        SQLiteDatabase db = this.getWritableDatabase();

        Integer personId = addPerson(person, db);

        ContentValues patientValues = new ContentValues();
        patientValues.put(KEY_PERSON_ID, personId);
        patientValues.put(KEY_DOCTOR_ID, -1);
        patientValues.put(KEY_BLOOD_TYPE, patient.getBloodType());
        patientValues.put(KEY_RH_FACTOR, patient.getRhFactor());
        patientValues.put(KEY_LOCATION, patient.getLocation());
        patientValues.put(KEY_JOB, patient.getJob());
        patientValues.put(KEY_COMMENTS, patient.getComments());

        Integer patientId = (int) db.insert(TABLE_PATIENT, null, patientValues);
        db.close();
        Log.d("Add new patient", patient.toString());

        return patientId;
    }

    public void addDoctorForPatient(Patient patient, Doctor doctor) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_DOCTOR_ID, doctor.getDoctorID());

        //db.insert(TABLE_PATIENT, null, contentValues);
        db.update(TABLE_PATIENT,
                contentValues,
                KEY_PATIENT_ID + "=?",
                new String[] {String.valueOf(patient.getPatientID())});
        db.close();

        Log.d("Add doctor for patient", patient.toString());
    }

    public Patient getPatient(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PATIENT, new String[] {
                KEY_PATIENT_ID, KEY_PERSON_ID, KEY_DOCTOR_ID, KEY_BLOOD_TYPE, KEY_RH_FACTOR, KEY_LOCATION,
                KEY_JOB, KEY_COMMENTS}, KEY_PATIENT_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Patient patient = new Patient(
                cursor.getInt(0),       // KEY_PATIENT_ID
                cursor.getInt(1),       // KEY_PERSON_ID
                cursor.getInt(2),       // KEY_DOCTOR_ID
                cursor.getString(3),    // KEY_BLOOD_TYPE
                cursor.getString(4),    // KEY_RH_FACTOR
                cursor.getString(5),    // KEY_LOCATION
                cursor.getString(6),    // KEY_JOB
                cursor.getString(7));   // KEY_COMMENTS

        cursor.close();
        db.close();
        return patient;
    }

    public List<Patient> getPatientsByDoctor(Doctor doctor) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Patient> patientList = new ArrayList<>();

        Cursor cursor = db.query(TABLE_PATIENT, new String[] {
                        KEY_PATIENT_ID, KEY_PERSON_ID, KEY_DOCTOR_ID, KEY_BLOOD_TYPE, KEY_RH_FACTOR, KEY_LOCATION,
                        KEY_JOB, KEY_COMMENTS}, KEY_DOCTOR_ID + "=?",
                new String[] { String.valueOf(doctor.getDoctorID()) }, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Patient patient = new Patient(
                        cursor.getInt(0),       // KEY_PATIENT_ID
                        cursor.getInt(1),       // KEY_PERSON_ID
                        cursor.getInt(2),       // KEY_DOCTOR_ID
                        cursor.getString(3),    // KEY_BLOOD_TYPE
                        cursor.getString(4),    // KEY_RH_FACTOR
                        cursor.getString(5),    // KEY_LOCATION
                        cursor.getString(6),    // KEY_JOB
                        cursor.getString(7));   // KEY_COMMENTS
                patientList.add(patient);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return patientList;
    }

    public List<Patient> getAllPatients() {
        List<Patient> patientList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_PATIENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Patient patient = new Patient(
                        cursor.getInt(0),       // KEY_PATIENT_ID
                        cursor.getInt(1),       // KEY_PERSON_ID
                        cursor.getInt(2),       // KEY_DOCTOR_ID
                        cursor.getString(3),    // KEY_BLOOD_TYPE
                        cursor.getString(4),    // KEY_RH_FACTOR
                        cursor.getString(5),    // KEY_LOCATION
                        cursor.getString(6),    // KEY_JOB
                        cursor.getString(7));   // KEY_COMMENTS
                patientList.add(patient);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return patientList;
    }

    public List<Patient> getPatientBySearch(String text) {
        List<Patient> patientList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT " +
                TABLE_PERSON + "." + KEY_PERSON_ID + ", " +
                TABLE_PATIENT + "." + KEY_PATIENT_ID + ", " +
                TABLE_PERSON + "." + KEY_FULL_NAME + " FROM " +
                TABLE_PERSON + " INNER JOIN " + TABLE_PATIENT +
                " WHERE (" +
                TABLE_PERSON + "." + KEY_PERSON_ID + " = " +
                TABLE_PATIENT + "." + KEY_PERSON_ID + " and " +
                TABLE_PERSON + "." + KEY_FULL_NAME + " LIKE \'" + text + "%\')";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                patientList.add(getPatient(cursor.getInt(1)));
                Log.d("DB", cursor.getString(2));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return patientList;
    }

    public void deletePatient(int patientId) {
        SQLiteDatabase db = this.getWritableDatabase();

        int personId = getPatient(patientId).getPersonID();

        this.getWritableDatabase()
                .delete(TABLE_PATIENT, KEY_PATIENT_ID + " =?", new String[]{Integer.toString(patientId)});
        this.getWritableDatabase()
                .delete(TABLE_PERSON, KEY_PERSON_ID + " =?", new String[] {Integer.toString(personId)});
        db.close();
    }

    // =======================================================================
    // Work with TABLE_DOCTOR
    // =======================================================================
    public void addDoctor(Doctor doctor, Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
        Integer idPerson = addPerson(person, db);

        ContentValues doctorValues = new ContentValues();
        doctorValues.put(KEY_PERSON_ID, idPerson);
        doctorValues.put(KEY_SPECIALIZATION, doctor.getSpecialization());
        doctorValues.put(KEY_PRACTICE_BEGAN_DATE, doctor.getPractiseBeganDate());

        db.insert(TABLE_DOCTOR, null, doctorValues);
        db.close();

        Log.d("Add new doctor", doctor.toString());
    }

    public Doctor getDoctor(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DOCTOR, new String[] {
                KEY_DOCTOR_ID, KEY_PERSON_ID, KEY_SPECIALIZATION, KEY_PRACTICE_BEGAN_DATE}, KEY_DOCTOR_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Doctor doctor = new Doctor(
                cursor.getInt(0),       // KEY_DOCTOR_ID
                cursor.getInt(1),       // KEY_PERSON_ID
                cursor.getString(2),    // KET_SPECIALIZATION
                cursor.getString(3));   // KEY_PRACTICE_BEGAN_DATE

        cursor.close();
        db.close();

        return doctor;
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctorList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_DOCTOR;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Doctor doctor = new Doctor(
                        Integer.parseInt(cursor.getString(0)),  // KEY_DOCTOR_ID
                        Integer.parseInt(cursor.getString(1)),  // KEY_PERSON_ID
                        cursor.getString(2),                    // KEY_SPECIALIZATION
                        cursor.getString(3));                   // KEY_PRACTICE_BEGAN_DATE
                doctorList.add(doctor);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return doctorList;
    }

    public List<Doctor> getDoctorBySearch(String text) {
        List<Doctor> doctorsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT " +
                TABLE_PERSON + "." + KEY_PERSON_ID + ", " +
                TABLE_DOCTOR + "." + KEY_DOCTOR_ID + ", " +
                TABLE_PERSON + "." + KEY_FULL_NAME + " FROM " +
                TABLE_PERSON + " INNER JOIN " + TABLE_DOCTOR +
                " WHERE (" +
                TABLE_PERSON + "." + KEY_PERSON_ID + " = " +
                TABLE_DOCTOR + "." + KEY_PERSON_ID + " and " +
                TABLE_PERSON + "." + KEY_FULL_NAME + " LIKE \'" + text + "%\')";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                doctorsList.add(getDoctor(cursor.getInt(1)));
                Log.d("DB", cursor.getString(2));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return doctorsList;
    }

    // =======================================================================
    // Work with TABLE_DIAGNOSIS
    // =======================================================================
    public void addDiagnosis(Diagnosis diagnosis) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ICD, diagnosis.getICD());
        values.put(KEY_DIAGNOSIS_NAME, diagnosis.getName());
        values.put(KEY_IS_CONFIRMED, diagnosis.getIsConfirmed());

        db.insertWithOnConflict(TABLE_DIAGNOSIS, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        db.close();

        Log.d("Add new diagnosis", diagnosis.toString());
    }

    public List<Diagnosis> getAllDiagnosis() {
        List<Diagnosis> diagnosisList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_DIAGNOSIS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Diagnosis diagnosis = new Diagnosis(
                        cursor.getString(0),        // KEY_ICD
                        cursor.getString(1),        // KEY_DIAGNOSIS_NAME
                        cursor.getInt(2));          // KEY_IS_CONFIRMED
                diagnosisList.add(diagnosis);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return diagnosisList;
    }

    public Diagnosis getDiagnosis(String ICD) {
        SQLiteDatabase db = this.getReadableDatabase();
        Diagnosis diagnosis = null;

        Cursor cursor = db.query(TABLE_DIAGNOSIS, new String[] {
                KEY_ICD, KEY_DIAGNOSIS_NAME, KEY_IS_CONFIRMED}, KEY_ICD + "=?",
                new String[] { ICD }, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();

            diagnosis = new Diagnosis(
                    cursor.getString(0),        // KEY_ICD
                    cursor.getString(1),        // KEY_DIAGNOSIS_NAME
                    cursor.getInt(2));          // KEY_IS_CONFIRMED
        }


        cursor.close();
        db.close();
        return diagnosis;
    }

    // =======================================================================
    // Work with TABLE_PATIENT_WITH_DIAGNOSIS
    // =======================================================================
    public void addDiagnosisForPatient(Diagnosis diagnosis, Integer patientId) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PATIENT_ID, patientId);
        values.put(KEY_ICD, diagnosis.getICD());

        db.insertWithOnConflict(TABLE_PATIENT_WITH_DIAGNOSIS, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        db.close();

        Log.d("Add diagnosis patient", diagnosis.getICD() + " " + patientId);
    }

    public List<Diagnosis> getDiagnosisByPatient(Patient patient) {
        List<Diagnosis> diagnosisList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PATIENT_WITH_DIAGNOSIS, new String[] {
                KEY_PATIENT_ID, KEY_ICD}, KEY_PATIENT_ID + "=?",
                new String[] { String.valueOf(patient.getPatientID()) }, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Diagnosis diagnosis = getDiagnosis(cursor.getString(1));
                diagnosisList.add(diagnosis);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return diagnosisList;
    }

    // =======================================================================
    // Work with TABLE_TREATMENT
    // =======================================================================

    public void addTreatment(Treatment treatment) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TREATMENT_ID, treatment.getTreatmentId());
        values.put(KEY_TREATMENT_NAME, treatment.getName());
        values.put(KEY_TREATMENT_TYPE, treatment.getTreatmentType());

        db.insertWithOnConflict(TABLE_TREATMENT, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        db.close();

        Log.d("Add treatment", treatment.toString());
    }

    public Treatment getTreatment(Integer treatmentId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Treatment treatment = null;

        Cursor cursor = db.query(TABLE_TREATMENT, new String[]{
                KEY_TREATMENT_ID, KEY_SIDE_EFFECT_NAME, KEY_TREATMENT_TYPE}, KEY_TREATMENT_ID + "=?",
                new String[]{ String.valueOf(treatmentId) }, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();

            treatment = new Treatment(
                    cursor.getInt(0),       // KEY_TREATMENT_ID
                    cursor.getString(1),    // KEY_TREATMENT_NAME
                    cursor.getString(2));    // KET_TREATMENT_TYPE
        }

        cursor.close();
        db.close();
        return  treatment;
    }

    public List<Treatment> getAllTreatments() {
        List<Treatment> treatmentList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_TREATMENT;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Treatment treatment = new Treatment(
                        cursor.getInt(0),       // KEY_TREATMENT_ID
                        cursor.getString(1),    // KEY_TREATMENT_NAME
                        cursor.getString(2));    // KET_TREATMENT_TYPE
                treatmentList.add(treatment);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return  treatmentList;
    }

    // =======================================================================
    // Work with TABLE_DIAGNOSIS_WITH_TREATMENTS
    // =======================================================================

    public void addTreatmentForDiagnosis(Treatment treatment, String ICD) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ICD, ICD);
        values.put(KEY_TREATMENT_ID, treatment.getTreatmentId());

        db.insertWithOnConflict(TABLE_DIAGNOSIS_WITH_TREATMENTS, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        db.close();

        Log.d("Add treatment for diag", treatment.getTreatmentId() + " " + ICD);
    }

    public List<Treatment> getTreatmentsByDiagnosis(Diagnosis diagnosis) {
        List<Treatment> treatmentList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DIAGNOSIS_WITH_TREATMENTS, new String[] {
                KEY_ICD, KEY_TREATMENT_ID}, KEY_ICD + "=?",
                new String[] { String.valueOf(diagnosis.getICD()) }, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Treatment treatment = getTreatment(cursor.getInt(1));
                treatmentList.add(treatment);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return treatmentList;
    }

}
