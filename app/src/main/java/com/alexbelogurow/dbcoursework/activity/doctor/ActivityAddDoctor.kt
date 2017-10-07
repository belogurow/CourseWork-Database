package com.alexbelogurow.dbcoursework.activity.doctor

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.app.DatePickerDialog;
import android.support.design.widget.TextInputLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.LayoutInflaterCompat
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.alexbelogurow.dbcoursework.util.DBHandler
import com.alexbelogurow.dbcoursework.model.Doctor
import com.alexbelogurow.dbcoursework.model.Person
import kotlinx.android.synthetic.main.activity_add_doctor.*
import kotlinx.android.synthetic.main.content_activity_add_doctor.*

import com.alexbelogurow.dbcoursework.R
import com.mikepenz.fontawesome_typeface_library.FontAwesome
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.context.IconicsLayoutInflater2
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*


class ActivityAddDoctor : AppCompatActivity() {

    private var mToolbar: Toolbar? = null
    private var mImageMale: ImageView? = null
    private var mImageFemale: ImageView? = null
    private var mTextInputName: TextInputLayout? = null
    private var mTextInputBirthDate: TextInputLayout? = null
    private var mTextInputPractiseBeganDate: TextInputLayout? = null
    private var mTextInputSpecialization: TextInputLayout? = null
    private var mButtonPickBirthDate: Button? = null
    private var mButtonPractiseBeganDate: Button? = null
    private var mButtonAddDoctor: Button? = null

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
        setContentView(R.layout.activity_add_doctor)

        initializeFields()
        initializeListeners()
    }

    private fun initializeFields() {
        mToolbar = findViewById(R.id.toolbar_add_doctor)
        mTextInputName = findViewById(R.id.text_input_doctor_name)
        mTextInputBirthDate = findViewById(R.id.text_input_doctor_birth_date)
        mTextInputPractiseBeganDate = findViewById(R.id.text_input_doctor_practise_date)
        mTextInputSpecialization = findViewById(R.id.text_input_doctor_specialization)

        mButtonPickBirthDate = findViewById(R.id.button_doctor_pick_date)
        mButtonPractiseBeganDate = findViewById(R.id.button_doctor_pick_practise_date)
        mButtonAddDoctor = findViewById(R.id.button_add_doctor)

        mImageMale = findViewById(R.id.image_doctor_male)
        mImageFemale = findViewById(R.id.image_doctor_female)

        setSupportActionBar(mToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        drawableMale = IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_male)
                .sizeDp(64)
        drawableFemale = IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_female)
                .sizeDp(64)


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
            val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                mTextInputBirthDate?.editText?.text = SpannableStringBuilder("$dayOfMonth/${month+1}/$year")
            }
            DatePickerDialog(this, dateListener, 1990, Calendar.MONTH, Calendar.DAY_OF_MONTH).show()
        }

        mButtonPractiseBeganDate?.setOnClickListener {
            val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                mTextInputPractiseBeganDate?.editText?.text = SpannableStringBuilder("$dayOfMonth/${month+1}/$year")
            }
            DatePickerDialog(this, dateListener, 1990, Calendar.MONTH, Calendar.DAY_OF_MONTH).show()
        }

        mButtonAddDoctor?.setOnClickListener {
            if (validateFields()) {
                val person = Person(
                        mTextInputName?.editText?.text.toString(),
                        mTextInputBirthDate?.editText?.text?.toString(),
                        if (numberOfCurImage == 0) "Male" else "Female")
                val doctor = Doctor(
                        mTextInputSpecialization?.editText?.text.toString(),
                        mTextInputPractiseBeganDate?.editText?.text.toString())

                val dbHandler = DBHandler.getInstance(this)
                dbHandler.addDoctor(doctor, person)
                dbHandler.close()

                Snackbar.make(coordinator_add_doctor, getString(R.string.doctor_was_added), Snackbar.LENGTH_SHORT)
                        .addCallback(object : Snackbar.Callback() {
                            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                                super.onDismissed(transientBottomBar, event)
                                finish()
                            }
                        }).show()
            }
        }
        }

    private fun validateFields(): Boolean {
        val EMPTY_FIELD = getString(R.string.empty_field)

        if (numberOfCurImage == 0) {
            Toast.makeText(this, "Не выбран пол врача", Toast.LENGTH_LONG).show()
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

        if (mTextInputPractiseBeganDate?.editText?.text?.length!! == 0) {
            mTextInputPractiseBeganDate?.error = EMPTY_FIELD
            return false
        } else {
            val regexDate = Regex("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})\$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))\$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})\$")
            if (!mTextInputPractiseBeganDate?.editText?.text?.matches(regexDate)!!) {
                mTextInputPractiseBeganDate?.error = "Неподходящий формат даты"
                return false
            } else {
                mTextInputPractiseBeganDate?.isErrorEnabled = false
            }
        }

        if (mTextInputSpecialization?.editText?.text?.length!! == 0) {
            mTextInputSpecialization?.error = EMPTY_FIELD
            return false
        } else {
            mTextInputSpecialization?.isErrorEnabled = false
        }

        return true
    }
}



