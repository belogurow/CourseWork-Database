package com.alexbelogurow.dbcoursework.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import com.alexbelogurow.dbcoursework.DataBase.DBHandler
import com.alexbelogurow.dbcoursework.R
import kotlinx.android.synthetic.main.activity_doctor_info.*

class ActivityDoctorInfo : AppCompatActivity() {

    private val EXTRA_DOCTOR_ID = "DOCTOR_ID"

    private var doctorId: Int? = null
    private var dbHandler: DBHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_info)

        initializeViews()
    }

    private fun initializeViews() {
        setSupportActionBar(toolbar_doctor_info)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        doctorId = intent.getIntExtra(EXTRA_DOCTOR_ID, -1)
        Toast.makeText(this, doctorId.toString(), Toast.LENGTH_LONG).show()

        dbHandler = DBHandler(this)
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


