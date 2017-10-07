package com.alexbelogurow.dbcoursework.activity.diagnosis

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.Button
import com.alexbelogurow.dbcoursework.R
import com.alexbelogurow.dbcoursework.model.Diagnosis
import com.alexbelogurow.dbcoursework.util.DBHandler

class AddDiagnosesActivity : AppCompatActivity() {

    private val EMPTY_TEXT = ""
    private val EXTRA_DIAGNOSIS_ICD = "ICD"

    private var mToolbar: Toolbar? = null
    private var mTextInputIcd: TextInputLayout? = null
    private var mEditTextIcd: TextInputEditText? = null
    private var mTextInputDiagnosisName: TextInputLayout? = null
    private var mEditTextDiagnosisName: TextInputEditText? = null
    private var mButtonAddTreatmentForDiagnoses: Button? = null

    private var dbHandler: DBHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_diagnoses)

        initializeFields()
        initializeListeners()

    }

    private fun initializeFields() {
        mToolbar = findViewById(R.id.toolbar_add_diagnoses)
        mTextInputIcd = findViewById(R.id.text_input_icd)
        mEditTextIcd = findViewById(R.id.text_edit_icd)
        mTextInputDiagnosisName = findViewById(R.id.text_input_diagnosis_name)
        mEditTextDiagnosisName = findViewById(R.id.text_edit_diagnosis_name)
        mButtonAddTreatmentForDiagnoses = findViewById(R.id.button_add_treatment_for_diagnosis)

        dbHandler = DBHandler.getInstance(this)

        setSupportActionBar(mToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initializeListeners() {
        mButtonAddTreatmentForDiagnoses?.setOnClickListener {

            if (validateFields()) {
                val addTreatmentsForDiagnosesActivity = Intent(this, AddTreatmentsForDiagnosesActivity::class.java)
                val diagnosis = Diagnosis(
                        mTextInputIcd?.editText?.text?.toString(),
                        mTextInputDiagnosisName?.editText?.text?.toString()
                )
                dbHandler?.addDiagnosis(diagnosis)
                addTreatmentsForDiagnosesActivity.putExtra(EXTRA_DIAGNOSIS_ICD, mTextInputIcd?.editText?.text?.toString())
                startActivity(addTreatmentsForDiagnosesActivity)
            }
        }
    }

    private fun validateFields(): Boolean {
        val icd = mTextInputIcd?.editText?.text?.toString()

        if (mTextInputIcd?.editText?.text?.length!! > 0) {
            val diagnosisFromDataBase = dbHandler?.getDiagnosis(icd)
            if (diagnosisFromDataBase == null) {
                mTextInputIcd?.error = EMPTY_TEXT
            } else {
                mTextInputIcd?.error = "Нельзя добавить уже существующий ICD"
                return false
            }
        } else {
            mTextInputIcd?.error = "Пустое поле"
            return false
        }

        if (mTextInputDiagnosisName?.editText?.text?.length!! == 0) {
            mTextInputDiagnosisName?.error = "Пустое поле"
            return false
        } else {
            mTextInputDiagnosisName?.error = EMPTY_TEXT
        }
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
