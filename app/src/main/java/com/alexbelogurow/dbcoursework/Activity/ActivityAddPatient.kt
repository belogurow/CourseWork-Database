package com.alexbelogurow.dbcoursework.Activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

import com.alexbelogurow.dbcoursework.DataBase.DBHandler
import com.alexbelogurow.dbcoursework.Model.Patient
import com.alexbelogurow.dbcoursework.Model.Person
import com.alexbelogurow.dbcoursework.R

import java.util.ArrayList

import de.hdodenhof.circleimageview.CircleImageView

class ActivityAddPatient : AppCompatActivity() {

    private var EXTRA_PATIENT_ID = "PATIENT_ID"

    private var mToolbar: Toolbar? = null
    private var mBirthDate: TextView? = null
    private var mImageViewListGender: MutableList<CircleImageView>? = null
    private var mEditTextPatientName: EditText? = null
    private var mEditTextPatientLocation: EditText? = null
    private var mEditTextPatientJob: EditText? = null
    private var mEditTextPatientComments: EditText? = null
    private var mSpinnerBloodType: Spinner? = null
    private var mSpinnerRhFactor: Spinner? = null

    private var mButtonPickBirthDate: Button? = null
    private var mButtonAddDoctorForPatient: Button? = null

    private var mCoordinatorLayout: CoordinatorLayout? = null

    private var numberOfCurImage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_patient)

        initializeFields()

        setSupportActionBar(mToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mButtonPickBirthDate?.setOnClickListener {
            val dateDialog = com.alexbelogurow.dbcoursework.Fragment.DatePicker()
            dateDialog.show(supportFragmentManager, "datePicker")
        }

        mButtonAddDoctorForPatient?.setOnClickListener {
            val person = Person(
                    mEditTextPatientName?.text.toString(),
                    mBirthDate?.text.toString(),
                    if (numberOfCurImage == 0) "Male" else "Female")

            val patient = Patient(
                    mSpinnerBloodType?.selectedItem.toString(),
                    mSpinnerRhFactor?.selectedItem.toString(),
                    mEditTextPatientLocation?.text.toString(),
                    mEditTextPatientJob?.text.toString(),
                    mEditTextPatientComments?.text.toString())

            val dbHandler = DBHandler(applicationContext)
            val patientID = dbHandler.addPatient(patient, person)
            dbHandler.close();

            val intentAddDoctor = Intent(this, ActivityAddDoctorForPatient::class.java)
            intentAddDoctor.putExtra(EXTRA_PATIENT_ID, patientID)
            startActivity(intentAddDoctor)
        }
        /*
        mButtonAddPatient!!.setOnClickListener {
            val person = Person(
                    mEditTextPatientName!!.text.toString(),
                    mBirthDate!!.text.toString(),
                    if (numberOfCurImage == 0) "Male" else "Female")

            val patient = Patient(
                    mSpinnerBloodType!!.selectedItem.toString(),
                    mSpinnerRhFactor!!.selectedItem.toString(),
                    mEditTextPatientLocation!!.text.toString(),
                    mEditTextPatientJob!!.text.toString(),
                    mEditTextPatientComments!!.text.toString())

            val snackbar = Snackbar
                    .make(mCoordinatorLayout!!, "New Patient was added", Snackbar.LENGTH_SHORT)
            snackbar.show()

            val dbHandler = DBHandler(applicationContext)

            dbHandler.addPatient(patient, person)
            Log.d(Log.DEBUG.toString() + "", dbHandler.allPatients.toString())
            dbHandler.close()
            snackbar.addCallback(object : Snackbar.Callback() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    super.onDismissed(transientBottomBar, event)

                    onBackPressed()
                }
            })
        }
        */

    }

    private fun initializeFields() {
        mBirthDate = findViewById<View>(R.id.textViewPatientDate) as TextView
        mEditTextPatientName = findViewById<View>(R.id.editTextDoctorName) as EditText
        mEditTextPatientLocation = findViewById<View>(R.id.editTextPatientLocation) as EditText
        mEditTextPatientJob = findViewById<View>(R.id.editTextPatientJob) as EditText
        mEditTextPatientComments = findViewById<View>(R.id.editTextPatientComments) as EditText

        mButtonPickBirthDate = findViewById<View>(R.id.buttonDoctorPickDate) as Button
        mButtonAddDoctorForPatient = findViewById(R.id.button_add_doctor_for_patient)

        mSpinnerBloodType = findViewById<View>(R.id.spinnerBloodType) as Spinner
        mSpinnerRhFactor = findViewById<View>(R.id.spinnerRhFactor) as Spinner

        mImageViewListGender = ArrayList()
        mImageViewListGender!!.add(findViewById<View>(R.id.imageViewDoctorMale) as CircleImageView)
        mImageViewListGender!!.add(findViewById<View>(R.id.imageViewDoctorFemale) as CircleImageView)

        mImageViewListGender!![0].tag = 0
        mImageViewListGender!![1].tag = 1

        mToolbar = findViewById<View>(R.id.toolbarAddPatient) as Toolbar
        mCoordinatorLayout = findViewById<View>(R.id.coordLayoutAddPatient) as CoordinatorLayout
    }

    fun onClickImage(view: View) {
        val numberOfClicked = view.tag as Int
        if (numberOfCurImage != numberOfClicked) {
            numberOfCurImage = numberOfClicked

            val numberOfSecondImage = Math.abs(numberOfCurImage - 1)

            if (numberOfCurImage == 0) {
                mImageViewListGender!![0].setImageResource(R.drawable.ic_patient_male_on)
                mImageViewListGender!![1].setImageResource(R.drawable.ic_patient_female_off)

            }

            if (numberOfCurImage == 1) {
                mImageViewListGender!![1].setImageResource(R.drawable.ic_patient_female_on)
                mImageViewListGender!![0].setImageResource(R.drawable.ic_patient_male_off)
            }

            mImageViewListGender!![numberOfCurImage].setBorderColorResource(R.color.colorBorderOn)
            mImageViewListGender!![numberOfSecondImage].setBorderColorResource(R.color.colorBorderOff)


        }
    }

}
