package com.alexbelogurow.dbcoursework.Activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.alexbelogurow.dbcoursework.DataBase.DBHandler;
import com.alexbelogurow.dbcoursework.Model.Patient;
import com.alexbelogurow.dbcoursework.Model.Person;
import com.alexbelogurow.dbcoursework.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityAddPatient extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView mBirthDate;
    private List<CircleImageView> mImageViewListGender;
    private EditText mEditTextPatientName,
            mEditTextPatientLocation,
            mEditTextPatientJob,
            mEditTextPatientComments;
    private Spinner mSpinnerBloodType,
            mSpinnerRhFactor;

    private Button mButtonPickBirthDate,
            mButtonAddPatient;

    private CoordinatorLayout mCoordinatorLayout;

    private int numberOfCurImage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        initializeFields();


        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mButtonPickBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dateDialog = new com.alexbelogurow.dbcoursework.Fragment.DatePicker();
                dateDialog.show(getSupportFragmentManager(), "datePicker");
            }
        });

        mButtonAddPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person = new Person(
                        mEditTextPatientName.getText().toString(),
                        mBirthDate.getText().toString(),
                        numberOfCurImage == 0 ? "Male" : "Female");

                Patient patient = new Patient(
                        mSpinnerBloodType.getSelectedItem().toString(),
                        mSpinnerRhFactor.getSelectedItem().toString(),
                        mEditTextPatientLocation.getText().toString(),
                        mEditTextPatientJob.getText().toString(),
                        mEditTextPatientComments.getText().toString());

                Snackbar snackbar = Snackbar
                        .make(mCoordinatorLayout, "New Patient was added", Snackbar.LENGTH_SHORT);
                snackbar.show();

                DBHandler dbHandler = new DBHandler(getApplicationContext());

                dbHandler.addPatient(patient, person);
                Log.d(Log.DEBUG + "", dbHandler.getAllPatients().toString());
                dbHandler.close();
                snackbar.addCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar transientBottomBar, int event) {
                        super.onDismissed(transientBottomBar, event);

                        onBackPressed();
                    }
                });

            }
        });
    }

    private void initializeFields() {
        mBirthDate = (TextView) findViewById(R.id.textViewPatientDate);
        mEditTextPatientName = (EditText) findViewById(R.id.editTextDoctorName);
        mEditTextPatientLocation = (EditText) findViewById(R.id.editTextPatientLocation);
        mEditTextPatientJob = (EditText) findViewById(R.id.editTextPatientJob);
        mEditTextPatientComments = (EditText) findViewById(R.id.editTextPatientComments);

        mButtonPickBirthDate = (Button) findViewById(R.id.buttonDoctorPickDate);
        mButtonAddPatient = (Button) findViewById(R.id.buttonAddPatient);

        mSpinnerBloodType = (Spinner) findViewById(R.id.spinnerBloodType);
        mSpinnerRhFactor = (Spinner) findViewById(R.id.spinnerRhFactor);

        mImageViewListGender = new ArrayList<>();
        mImageViewListGender.add((CircleImageView) findViewById(R.id.imageViewDoctorMale));
        mImageViewListGender.add((CircleImageView) findViewById(R.id.imageViewDoctorFemale));

        mImageViewListGender.get(0).setTag(0);
        mImageViewListGender.get(1).setTag(1);

        mToolbar = (Toolbar) findViewById(R.id.toolbarAddPatient);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordLayoutAddPatient);
    }

    public void onClickImage(View view) {
        int numberOfClicked = (int) view.getTag();
        if (numberOfCurImage != numberOfClicked) {
            numberOfCurImage = numberOfClicked;

            int numberOfSecondImage = Math.abs(numberOfCurImage - 1);

            if (numberOfCurImage == 0) {
                mImageViewListGender.get(0).setImageResource(R.drawable.ic_patient_male_on);
                mImageViewListGender.get(1).setImageResource(R.drawable.ic_patient_female_off);

            }

            if (numberOfCurImage == 1) {
                mImageViewListGender.get(1).setImageResource(R.drawable.ic_patient_female_on);
                mImageViewListGender.get(0).setImageResource(R.drawable.ic_patient_male_off);
            }

            mImageViewListGender.get(numberOfCurImage).
                    setBorderColorResource(R.color.colorBorderOn);
            mImageViewListGender.get(numberOfSecondImage).
                    setBorderColorResource(R.color.colorBorderOff);


        }
    }


}
