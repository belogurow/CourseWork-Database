package com.alexbelogurow.dbcoursework.activity.patient

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.alexbelogurow.dbcoursework.adapter.diagnosis.DiagnosesAdapter
import com.alexbelogurow.dbcoursework.util.DBHandler
import com.alexbelogurow.dbcoursework.R
import com.alexbelogurow.dbcoursework.activity.doctor.ActivityDoctorInfo
import kotlinx.android.synthetic.main.activity_patient_info.*
import kotlinx.android.synthetic.main.content_activity_patient_info.*
import kotlinx.android.synthetic.main.item_doctor.*
import java.util.*

class ActivityPatientInfo : AppCompatActivity() {

    private val TAG = "ActivityPatientInfo"
    private val EXTRA_PATIENT_ID = "PATIENT_ID"
    private val EXTRA_DOCTOR_ID = "DOCTOR_ID"

    private val INFO_ABOUT_DIAGNOSIS = 0

    private var patientId: Int? = null
    private var dbHandler: DBHandler? = null
    private var adapter: DiagnosesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_info)

        initializeViews()
    }

    private fun initializeViews() {
        setSupportActionBar(toolbar_patient_info)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        recycler_patient_info_diagnosis.layoutManager = LinearLayoutManager(this)


        patientId = intent.getIntExtra(EXTRA_PATIENT_ID, -1);

        dbHandler = DBHandler.getInstance(this)

        val patient = dbHandler?.getPatient(patientId!!)
        val personPatient = dbHandler?.getPerson(patient?.personID!!)
        val doctor = dbHandler?.getDoctor(patient?.doctorID!!)
        val personDoctor = dbHandler?.getPerson(doctor?.personID!!)

        text_patient_info_name.text = "${personPatient?.fullName} (${personPatient?.sex})"
        text_patient_info_age.text = "${getAge(personPatient?.birthDate!!)}"
        text_patient_info_data.text = "${patient?.patientInfo}"
        text_patient_info_medical_data.text = "${patient?.patientMedicalInfo}"

        text_doctor_card_name.text = personDoctor?.fullName
        text_doctor_card_age.text = getAge(personDoctor?.birthDate!!)
        text_doctor_card_specialization.text = doctor?.specialization

        adapter = DiagnosesAdapter(dbHandler?.getDiagnosisByPatient(patient)!!, this, INFO_ABOUT_DIAGNOSIS, -1)
        recycler_patient_info_diagnosis.adapter = adapter

        layout_patient_info_doctor.setOnClickListener {
            val doctorInfoIntent = Intent(this, ActivityDoctorInfo::class.java)
            doctorInfoIntent.putExtra(EXTRA_DOCTOR_ID, doctor?.doctorID)
            startActivity(doctorInfoIntent)
        }
    }

    private fun getAge(birthDate: String): String {
        val date = birthDate.split("/".toRegex())
        val dob = Calendar.getInstance()
        val today = Calendar.getInstance()

        dob.set(date[2].toInt(), date[1].toInt(), date[0].toInt())

        var age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--
        }

        val ageInt = age
        return "Возраст - ${ageInt.toString()}"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_patient_info, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.action_menu_patient_delete     -> {
                dbHandler?.deletePatient(patientId!!)
                // TODO ADD ALERT DIALOG
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
