package com.alexbelogurow.dbcoursework.Activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.alexbelogurow.dbcoursework.R;

public class ActivityAddPatient extends AppCompatActivity {

    private TextView mBirthDate;

    private Button mPickBirthDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        mBirthDate = (TextView) findViewById(R.id.textViewPatientDate);
        mPickBirthDate = (Button) findViewById(R.id.buttonPatientPickDate);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAddPatient);
        setSupportActionBar(toolbar);
        
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPickBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dateDialog = new com.alexbelogurow.dbcoursework.Fragment.DatePicker();
                dateDialog.show(getSupportFragmentManager(), "datePicker");
            }
        });
    }

}
