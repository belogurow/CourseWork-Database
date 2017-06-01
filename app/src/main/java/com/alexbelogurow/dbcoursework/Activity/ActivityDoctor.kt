package com.alexbelogurow.dbcoursework.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import com.alexbelogurow.dbcoursework.Adapter.DoctorAdapter
import com.alexbelogurow.dbcoursework.DataBase.DBHandler

import com.alexbelogurow.dbcoursework.Drawer.NavigationDrawer
import com.alexbelogurow.dbcoursework.Model.Doctor
import com.alexbelogurow.dbcoursework.Model.Patient
import com.alexbelogurow.dbcoursework.Model.Person
import com.alexbelogurow.dbcoursework.R

class ActivityDoctor : AppCompatActivity() {

    private var mToolbar: Toolbar? = null
    private var mFabAddDoctor: FloatingActionButton? = null
    private var mRecyclerView: RecyclerView? = null

    private var dbHandler: DBHandler? = null
    private var mAdapter: DoctorAdapter? = null

    private var doctorsList: List<Doctor>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor)

        initializeViews()
        initializeListeners()
        initializeAdapter()
        //dbHandler?.addDoctor(Doctor("dentist", "5/3/2012"), Person("Tom", "5/12/1972", "Male"))
    }

    private fun initializeViews() {
        mToolbar = findViewById(R.id.toolbarDoctor) as Toolbar
        mFabAddDoctor = findViewById(R.id.fabAddDoctor) as FloatingActionButton
        mRecyclerView = findViewById(R.id.recyclerViewDcotor) as RecyclerView

        setSupportActionBar(mToolbar)
        supportActionBar?.title = "Doctors"

        NavigationDrawer(this, mToolbar, 1).setNavigationDrawer()

        dbHandler = DBHandler(this)

        mRecyclerView?.layoutManager = LinearLayoutManager(applicationContext)
        mRecyclerView?.setHasFixedSize(true)
    }

    private fun initializeListeners() {
        mFabAddDoctor?.setOnClickListener {
            val addDoctorActivity = Intent(this, ActivityAddDoctor::class.java)
            startActivity(addDoctorActivity)
        }
    }

    private fun initializeAdapter() {
        doctorsList = dbHandler?.allDoctors
        //TODO replace !!
        mAdapter = DoctorAdapter(doctorsList!!, this)
        mRecyclerView?.adapter = mAdapter
    }

    override fun onRestart() {
        super.onRestart()
        //TODO replace !!
        mAdapter?.updateList(dbHandler?.allDoctors!!)
    }
}
