package com.alexbelogurow.dbcoursework.activity.doctor

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.app.DatePickerDialog;
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import com.alexbelogurow.dbcoursework.util.DBHandler
import com.alexbelogurow.dbcoursework.model.Doctor
import com.alexbelogurow.dbcoursework.model.Person
import kotlinx.android.synthetic.main.activity_add_doctor.*
import kotlinx.android.synthetic.main.content_activity_add_doctor.*

import com.alexbelogurow.dbcoursework.R
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*


class ActivityAddDoctor : AppCompatActivity() {

    private var arrayOfGender: Array<CircleImageView>? = null
    private var numberOfCurImage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_doctor)

        initializeFields()
        initializeListeners()
    }

    private fun initializeFields() {
        setSupportActionBar(toolbarAddDoctor)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initializeListeners() {
        imageViewDoctorMale.tag = 0
        imageViewDoctorFemale.tag = 1
        arrayOfGender?.plus(imageViewDoctorMale)
        arrayOfGender?.plus(imageViewDoctorFemale)

        buttonDoctorPickDate.setOnClickListener {
            val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                textViewDoctorDate.text = "$year/$month/$dayOfMonth"
            }
            DatePickerDialog(this, dateListener, 1990, Calendar.MONTH, Calendar.DAY_OF_MONTH).show()
        }

        buttonDoctorPractiseDate.setOnClickListener {
            val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                textViewDoctorPractiseDate.text = "$year/$month/$dayOfMonth"
            }
            DatePickerDialog(this, dateListener, 2005, Calendar.MONTH, Calendar.DAY_OF_MONTH).show()
        }

        buttonAddDoctor.setOnClickListener {
            val person = Person(
                    editTextDoctorName.text.toString(),
                    textViewDoctorDate.text.toString(),
                    if (numberOfCurImage == 0) "Male" else "Female")
            val doctor = Doctor(
                    editTextDoctorSpecialization.text.toString(),
                    textViewDoctorPractiseDate.text.toString())

            val dbHandler = DBHandler.getInstance(this)
            dbHandler.addDoctor(doctor, person)
            dbHandler.close()

            Snackbar.make(coordinator_add_doctor,
                        getString(R.string.doctor_was_added),
                        Snackbar.LENGTH_SHORT)
                    .addCallback(object: Snackbar.Callback() {
                        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                            super.onDismissed(transientBottomBar, event)

                            finish()
                        }
                    })
                    .show()
        }
    }

    fun onClickImage(view: View) {
        val numberOfClicked = view.tag as Int
        if (numberOfCurImage != numberOfClicked) {
            val numberOfSecondImage = numberOfCurImage
            numberOfCurImage = numberOfClicked

            if (numberOfCurImage == 0) {
                imageViewDoctorMale.setImageResource(R.drawable.ic_patient_male_on)
                imageViewDoctorFemale.setImageResource(R.drawable.ic_patient_female_off)
            } else {
                imageViewDoctorMale.setImageResource(R.drawable.ic_patient_male_off)
                imageViewDoctorFemale.setImageResource(R.drawable.ic_patient_female_on)
            }

            Log.d("DEBUG", "$numberOfCurImage : $numberOfSecondImage")
            arrayOfGender?.get(numberOfCurImage)?.borderColor = ContextCompat.getColor(this, R.color.colorBorderOn)
            arrayOfGender?.get(numberOfSecondImage)?.borderColor = ContextCompat.getColor(this, R.color.colorBorderOff)
        }
    }

}
