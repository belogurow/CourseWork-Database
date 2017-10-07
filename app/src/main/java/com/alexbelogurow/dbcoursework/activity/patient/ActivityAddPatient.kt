package com.alexbelogurow.dbcoursework.activity.patient

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.TextInputLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.LayoutInflaterCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.*

import com.alexbelogurow.dbcoursework.util.DBHandler
import com.alexbelogurow.dbcoursework.model.Patient
import com.alexbelogurow.dbcoursework.model.Person
import com.alexbelogurow.dbcoursework.R
import com.mikepenz.fontawesome_typeface_library.FontAwesome
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.context.IconicsLayoutInflater2

import java.util.ArrayList

import de.hdodenhof.circleimageview.CircleImageView

class ActivityAddPatient : AppCompatActivity() {

    private var EXTRA_PATIENT_ID = "PATIENT_ID"

    private var mToolbar: Toolbar? = null
    private var mImageMale: ImageView? = null
    private var mImageFemale: ImageView? = null
    private var mTextInputName: TextInputLayout? = null
    private var mTextInputBirthDate: TextInputLayout? = null
    private var mTextInputAddress: TextInputLayout? = null
    private var mTextInputJob: TextInputLayout? = null
    private var mTextInputComments: TextInputLayout? = null
    private var mSpinnerBloodType: Spinner? = null
    private var mSpinnerRhFactor: Spinner? = null
    private var mButtonPickBirthDate: Button? = null
    private var mButtonAddDoctorForPatient: Button? = null

    private var drawableMale: IconicsDrawable? = null
    private var drawableFemale: IconicsDrawable? = null

    /**
     * 0 - nothing
     * 1 - male
     * 2 - female
     */
    private var numberOfCurImage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        LayoutInflaterCompat.setFactory2(layoutInflater, IconicsLayoutInflater2(delegate));
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_patient)

        initializeFields()
        initializeListeners()
    }


    private fun initializeListeners() {
        mImageMale?.setOnClickListener {
            mImageMale?.setImageDrawable(drawableMale?.color(
                    ContextCompat.getColor(this, R.color.malePick)))
            mImageFemale?.setImageDrawable(drawableFemale?.color(
                    ContextCompat.getColor(this, R.color.defualtPick)))
            numberOfCurImage = 1
        }

        mImageFemale?.setOnClickListener {
            mImageFemale?.setImageDrawable(drawableFemale?.color(
                    ContextCompat.getColor(this, R.color.femalePick)))
            mImageMale?.setImageDrawable(drawableMale?.color(
                    ContextCompat.getColor(this, R.color.defualtPick)))
            numberOfCurImage = 2
        }

        mButtonPickBirthDate?.setOnClickListener {
            val dateDialog = com.alexbelogurow.dbcoursework.fragment.DatePicker()
            dateDialog.show(supportFragmentManager, "datePicker")
        }

        mButtonAddDoctorForPatient?.setOnClickListener {
            if (validateFields()) {
                val person = Person(
                        mTextInputName?.editText?.text?.toString(),
                        mTextInputBirthDate?.editText?.text?.toString(),
                        if (numberOfCurImage == 1) "Male" else "Female")

                val patient = Patient(
                        mSpinnerBloodType?.selectedItem.toString(),
                        mSpinnerRhFactor?.selectedItem.toString(),
                        mTextInputAddress?.editText?.text?.toString(),
                        mTextInputJob?.editText?.text?.toString(),
                        mTextInputComments?.editText?.text?.toString())

                val dbHandler = DBHandler.getInstance(this)
                val patientId = dbHandler.addPatient(patient, person)
                dbHandler.close()

                val intentAddDoctor = Intent(this, ActivityAddDoctorForPatient::class.java)
                intentAddDoctor.putExtra(EXTRA_PATIENT_ID, patientId)
                startActivity(intentAddDoctor)
            }
        }
    }

    private fun initializeFields() {
        mToolbar = findViewById(R.id.toolbar_add_patient)
        mTextInputName = findViewById(R.id.text_input_patient_name)
        mTextInputBirthDate = findViewById(R.id.text_input_patient_birth_date)
        mTextInputAddress = findViewById(R.id.text_input_patient_address)
        mTextInputJob = findViewById(R.id.text_input_patient_job)
        mTextInputComments = findViewById(R.id.text_input_patient_comments)

        mImageMale = findViewById(R.id.image_patient_male)
        mImageFemale = findViewById(R.id.image_patient_female)
        mButtonPickBirthDate = findViewById(R.id.button_patient_pick_date)
        mButtonAddDoctorForPatient = findViewById(R.id.button_add_doctor_for_patient)
        mSpinnerBloodType = findViewById(R.id.spinner_blood_type)
        mSpinnerRhFactor = findViewById(R.id.spinner_rh_factor)

        setSupportActionBar(mToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        drawableMale = IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_male)
                .sizeDp(64)
        drawableFemale = IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_female)
                .sizeDp(64)
    }

    private fun validateFields(): Boolean {
        val EMPTY_FIELD = getString(R.string.empty_field)

        if (numberOfCurImage == 0) {
            Toast.makeText(this, "Не выбран пол пациента", Toast.LENGTH_LONG).show()
            return false
        }

        if (mTextInputName?.editText?.text?.length!! == 0) {
            mTextInputName?.error = EMPTY_FIELD
            return false
        } else {
            mTextInputName?.isErrorEnabled = false
        }

        if (mTextInputBirthDate?.editText?.text?.length!! == 0) {
            mTextInputBirthDate?.error = EMPTY_FIELD
            return false
        } else {
            val regexDate = Regex("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})\$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))\$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})\$")
            if (!mTextInputBirthDate?.editText?.text?.matches(regexDate)!!) {
                mTextInputBirthDate?.error = "Неподходящий формат даты"
                return false
            } else {
                mTextInputBirthDate?.isErrorEnabled = false
            }
        }

        if (mTextInputAddress?.editText?.text?.length!! == 0) {
            mTextInputAddress?.error = EMPTY_FIELD
            return false
        } else {
            mTextInputAddress?.isErrorEnabled = false
        }

        if (mTextInputJob?.editText?.text?.length!! == 0) {
            mTextInputJob?.error = EMPTY_FIELD
            return false
        } else {
            mTextInputJob?.isErrorEnabled = false
        }

        return true
    }
}
