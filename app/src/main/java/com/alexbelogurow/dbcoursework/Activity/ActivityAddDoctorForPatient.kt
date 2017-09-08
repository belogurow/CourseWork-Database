package com.alexbelogurow.dbcoursework.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import com.alexbelogurow.dbcoursework.Adapter.DoctorAdapter
import com.alexbelogurow.dbcoursework.DataBase.DBHandler
import com.alexbelogurow.dbcoursework.Model.Doctor
import com.alexbelogurow.dbcoursework.R

class ActivityAddDoctorForPatient : AppCompatActivity() {

    private val TAG = "ActivityAddDoctorForPatient"
    private var mToolbar: Toolbar? = null
    private var mRecyclerView: RecyclerView? = null

    private var dbHandler: DBHandler? = null
    private var mAdapter: DoctorAdapter? = null

    private var doctorsList: List<Doctor>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor)

        initializeFields()
        initializeAdapter()
    }

    private fun initializeFields() {
        mToolbar = findViewById(R.id.toolbarDoctor)
        mRecyclerView = findViewById(R.id.recyclerViewDcotor)

        setSupportActionBar(mToolbar)
        supportActionBar?.title = "Doctors"

        dbHandler = DBHandler(this)

        mRecyclerView?.layoutManager = LinearLayoutManager(this)
        mRecyclerView?.setHasFixedSize(true)
    }

    private fun initializeAdapter() {
        doctorsList = dbHandler?.allDoctors
        //TODO replace !!
        mAdapter = DoctorAdapter(doctorsList!!, this)
        mRecyclerView?.adapter = mAdapter
    }
}
