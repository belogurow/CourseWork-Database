package com.alexbelogurow.dbcoursework.activity.patient

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.support.v7.widget.helper.ItemTouchHelper
import android.support.v7.widget.helper.ItemTouchHelper.*
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.SearchView

import com.alexbelogurow.dbcoursework.adapter.patient.PatientAdapter
import com.alexbelogurow.dbcoursework.util.DBHandler
import com.alexbelogurow.dbcoursework.util.NavigationDrawer
import com.alexbelogurow.dbcoursework.model.Patient
import com.alexbelogurow.dbcoursework.R

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

        initializeViews()
        initializeAdapter()
    }

    private fun initializeViews() {
        mRecyclerView = findViewById<View>(R.id.recyclerViewPatient) as RecyclerView
        mFabAddPatient = findViewById<View>(R.id.fabAddPatient) as FloatingActionButton
        mToolbar = findViewById<View>(R.id.toolbarPatient) as Toolbar

        setSupportActionBar(mToolbar)
        if (supportActionBar != null) {
            supportActionBar!!.title = resources.getString(R.string.patients)
        }

        dbHandler = DBHandler.getInstance(this)

        mRecyclerView?.layoutManager = LinearLayoutManager(this)
        mRecyclerView?.setHasFixedSize(true)

        mFabAddPatient?.setOnClickListener {
            val addPatientActivity = Intent(this, ActivityAddPatient::class.java)
            startActivity(addPatientActivity)
        }
        NavigationDrawer(this, mToolbar, 0).setNavigationDrawer()

//        val swipe = object : SimpleCallback(0, LEFT or RIGHT) {
//            override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
//                return false
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
//                Log.d("swiped", viewHolder?.adapterPosition?.toString())
//                val position = viewHolder?.adapterPosition!!
//
//                dbHandler?.deletePatientById(patientList?.get(position)?.patientID!!)
//                //patientList?.drop(position)
//                //mAdapter?.updateList(patientList?.)
//                mAdapter?.notifyItemRemoved(position)
//                //mAdapter?.on
//
//                //mAdapter?.updateList(patientList?.drop(viewHolder?.adapterPosition!!))
//            }
//
//        }
//        ItemTouchHelper(swipe).attachToRecyclerView(mRecyclerView)
    }

    private fun initializeAdapter() {
        patientList = dbHandler?.allPatients
        mAdapter = PatientAdapter(patientList, applicationContext)
        mRecyclerView?.adapter = mAdapter
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
