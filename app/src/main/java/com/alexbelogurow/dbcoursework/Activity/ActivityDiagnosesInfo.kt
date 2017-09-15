package com.alexbelogurow.dbcoursework.Activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.alexbelogurow.dbcoursework.R
import kotlinx.android.synthetic.main.activity_diagnoses_info.*

class ActivityDiagnosesInfo : AppCompatActivity() {

    private val EXTRA_ICD = "ICD"

    private var icd: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnoses_info)
        setSupportActionBar(toolbar)


        initializeViews()
    }

    private fun initializeViews() {
        icd = intent.getStringExtra(EXTRA_ICD)
    }
}
