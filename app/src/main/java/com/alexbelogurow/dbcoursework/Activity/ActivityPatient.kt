package com.alexbelogurow.dbcoursework.Activity

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import android.widget.SearchView

import com.alexbelogurow.dbcoursework.Adapter.PatientAdapter
import com.alexbelogurow.dbcoursework.DataBase.DBHandler
import com.alexbelogurow.dbcoursework.Drawer.NavigationDrawer
import com.alexbelogurow.dbcoursework.Model.Patient
import com.alexbelogurow.dbcoursework.R
import com.facebook.stetho.Stetho

class ActivityPatient : AppCompatActivity() {

    private var mRecyclerView: RecyclerView? = null
    private var mFabAddPatient: FloatingActionButton? = null

    private var dbHandler: DBHandler? = null
    private var mAdapter: PatientAdapter? = null

    private var mToolbar: Toolbar? = null

    private var patientList: List<Patient>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient)
        initializeStetho()

        mRecyclerView = findViewById<View>(R.id.recyclerViewPatient) as RecyclerView
        mFabAddPatient = findViewById<View>(R.id.fabAddPatient) as FloatingActionButton
        mToolbar = findViewById<View>(R.id.toolbarPatient) as Toolbar

        setSupportActionBar(mToolbar)
        if (supportActionBar != null) {
            supportActionBar!!.title = resources.getString(R.string.patients)
        }

        dbHandler = DBHandler(applicationContext)

        mRecyclerView?.layoutManager = LinearLayoutManager(applicationContext)
        mRecyclerView?.setHasFixedSize(true)

        mFabAddPatient?.setOnClickListener {
            val addPatientActivity = Intent(applicationContext, ActivityAddPatient::class.java)
            startActivity(addPatientActivity)
        }

        initializeAdapter()
        NavigationDrawer(this, mToolbar, 0).setNavigationDrawer()
    }


    private fun initializeAdapter() {
        patientList = dbHandler?.allPatients
        mAdapter = PatientAdapter(patientList, applicationContext)
        mRecyclerView?.adapter = mAdapter
    }

    private fun initializeStetho() {
        // Create an InitializerBuilder
        val initializerBuilder = Stetho.newInitializerBuilder(this)

        // Enable Chrome DevTools
        initializerBuilder.enableWebKitInspector(
                Stetho.defaultInspectorModulesProvider(this)
        )

        // Enable command line interface
        initializerBuilder.enableDumpapp(
                Stetho.defaultDumperPluginsProvider(this)
        )

        // Use the InitializerBuilder to generate an Initializer
        val initializer = initializerBuilder.build()

        // Initialize Stetho with the Initializer
        Stetho.initialize(initializer)
    }

    override fun onRestart() {
        super.onRestart()

        mAdapter!!.updateList(dbHandler!!.allPatients)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_patient, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.menu_action_patient_search)?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        searchView.setSearchableInfo(searchManager.
                getSearchableInfo(componentName))
        searchView.isSubmitButtonEnabled = true

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(text: String?): Boolean {
                // TODO patient list
                patientList = dbHandler?.getPatientBySearch(text)
                mAdapter?.updateList(patientList)
                return true
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }


}
