package com.alexbelogurow.dbcoursework.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.alexbelogurow.dbcoursework.Adapter.DiagnosesAdapter
import com.alexbelogurow.dbcoursework.Adapter.DiagnosesAdapter2
import com.alexbelogurow.dbcoursework.DataBase.DBHandler
import com.alexbelogurow.dbcoursework.Model.Diagnosis
import com.alexbelogurow.dbcoursework.R

class ActivityAddDiagnosesForPatient : AppCompatActivity() {

    private val TAG = "ActivityDiagnoses"
    private val ADD_DIAGNOSES_TO_PATIENT = 1;
    private var EXTRA_PATIENT_ID = "PATIENT_ID"

    private var mToolbar: Toolbar? = null
    private var mFabAddDiagnosis: FloatingActionButton? = null
    private var mRecyclerView: RecyclerView? = null

    private var dbHandler: DBHandler? = null
    private var mAdapter: DiagnosesAdapter? = null

    private var diagnosisList: List<Diagnosis>? = null
    private var patientId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnosis)

        initializeFields()
        initializeAdapter()
    }

    private fun initializeFields() {
        patientId = intent.getIntExtra(EXTRA_PATIENT_ID, -1)

        mToolbar = findViewById(R.id.toolbar_diagnosis)
        mFabAddDiagnosis = findViewById(R.id.fab_add_diagnosis)
        mRecyclerView = findViewById(R.id.recycler_diagnosis)

        setSupportActionBar(mToolbar)
        supportActionBar?.title = getString(R.string.add_diagnoses_for_patient)

        mFabAddDiagnosis?.hide()

        dbHandler = DBHandler(this)

        mRecyclerView?.layoutManager = LinearLayoutManager(this)
        mRecyclerView?.setHasFixedSize(true)
    }



    private fun initializeAdapter() {
        diagnosisList = dbHandler?.allDiagnosis
        mAdapter = DiagnosesAdapter(diagnosisList!!, this, ADD_DIAGNOSES_TO_PATIENT, patientId!!)
        mRecyclerView?.adapter = mAdapter

    }
}
