package com.alexbelogurow.dbcoursework.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import com.alexbelogurow.dbcoursework.Adapter.DoctorAdapter
import com.alexbelogurow.dbcoursework.DataBase.DBHandler

import com.alexbelogurow.dbcoursework.Drawer.NavigationDrawer
import com.alexbelogurow.dbcoursework.Model.Doctor
import com.alexbelogurow.dbcoursework.R
import android.app.SearchManager
import android.content.Context
import android.widget.SearchView
import android.support.v4.widget.SearchViewCompat.setOnQueryTextListener




class ActivityDoctor : AppCompatActivity() {

    private val TAG = "ActivityDoctor"
    private val INFO_ABOUT_DOCTOR = 0
    private val PATIENT_ID = -1

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
        mToolbar = findViewById(R.id.toolbarDoctor)
        mFabAddDoctor = findViewById(R.id.fabAddDoctor)
        mRecyclerView = findViewById(R.id.recyclerViewDcotor)

        setSupportActionBar(mToolbar)
        supportActionBar?.title = getString(R.string.doctors)

        if (supportActionBar == null) {
            Log.d(TAG, "null")
        } else {
            Log.d(TAG, "not null")
        }

        val drawer = NavigationDrawer(this, mToolbar, 1)
        drawer.setNavigationDrawer()

        dbHandler = DBHandler.getInstance(this)

        mRecyclerView?.layoutManager = LinearLayoutManager(this)
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
        mAdapter = DoctorAdapter(doctorsList!!, this, INFO_ABOUT_DOCTOR, PATIENT_ID)
        mRecyclerView?.adapter = mAdapter
    }


    override fun onRestart() {
        super.onRestart()
        mAdapter?.updateList(dbHandler?.allDoctors!!)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_doctor, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.menu_action_doctor_search)?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        searchView.setSearchableInfo(searchManager.
                getSearchableInfo(componentName))
        searchView.isSubmitButtonEnabled = true

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(text: String?): Boolean {
                doctorsList = dbHandler?.getDoctorBySearch(text)
                mAdapter?.updateList(doctorsList!!)
                return true
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
        })


        return super.onCreateOptionsMenu(menu)
    }



}

