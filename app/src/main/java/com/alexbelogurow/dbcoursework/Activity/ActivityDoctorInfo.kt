package com.alexbelogurow.dbcoursework.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.alexbelogurow.dbcoursework.Adapter.PatientAdapter
import com.alexbelogurow.dbcoursework.DataBase.DBHandler
import com.alexbelogurow.dbcoursework.R
import kotlinx.android.synthetic.main.activity_doctor_info.*
import kotlinx.android.synthetic.main.content_activity_doctor_info.*
import java.util.*

class ActivityDoctorInfo : AppCompatActivity() {

    private val EXTRA_DOCTOR_ID = "DOCTOR_ID"

    private var doctorId: Int? = null
    private var dbHandler: DBHandler? = null
    private var adapter: PatientAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_info)

        initializeViews()
    }

    private fun initializeViews() {
        setSupportActionBar(toolbar_doctor_info)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        recycler_doctor_info_patients.layoutManager = LinearLayoutManager(this)

        doctorId = intent.getIntExtra(EXTRA_DOCTOR_ID, -1)

        dbHandler = DBHandler.getInstance(this)

        val doctor = dbHandler?.getDoctor(doctorId!!)
        val personDoctor = dbHandler?.getPerson(doctor?.personID!!)

        text_doctor_info_name.text = "${personDoctor?.fullName} (${personDoctor?.sex})"
        text_doctor_info_age.text = "${getAge(personDoctor?.birthDate!!)}"
        text_doctor_info_specialization.text = doctor?.specialization
        text_doctor_info_practise_began.text = "Начал работать - ${doctor?.practiseBeganDate}"

        adapter = PatientAdapter(dbHandler?.getPatientsByDoctor(doctor), this)
        recycler_doctor_info_patients.adapter = adapter
    }

    private fun getAge(birthDate: String): String {
        val date = birthDate.split("/".toRegex())
        val dob = Calendar.getInstance()
        val today = Calendar.getInstance()

        dob.set(date[0].toInt(), date[1].toInt(), date[2].toInt())

        var age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--
        }

        val ageInt = age
        return "Возраст - ${ageInt.toString()}"
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


