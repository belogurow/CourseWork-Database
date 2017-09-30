package com.alexbelogurow.dbcoursework.activity.diagnosis

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.alexbelogurow.dbcoursework.adapter.diagnosis.DiagnosesAdapter
import com.alexbelogurow.dbcoursework.util.DBHandler
import com.alexbelogurow.dbcoursework.util.NavigationDrawer
import com.alexbelogurow.dbcoursework.model.Diagnosis
import com.alexbelogurow.dbcoursework.R

class ActivityDiagnoses : AppCompatActivity() {

    private val TAG = "ActivityDiagnoses"
    private val INFO_ABOUT_DIAGNOSIS = 0
    private val PATIENT_ID = -1

    private var mToolbar: Toolbar? = null
    private var mFabAddDiagnosis: FloatingActionButton? = null
    private var mRecyclerView: RecyclerView? = null

    private var dbHandler: DBHandler? = null
    private var mAdapter: DiagnosesAdapter? = null

    private var diagnosisList: List<Diagnosis>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnosis)

        initializeFields()
        initializeListeners()
        initializeAdapter()
    }

    private fun initializeFields() {
        mToolbar = findViewById(R.id.toolbar_diagnosis)
        mFabAddDiagnosis = findViewById(R.id.fab_add_diagnosis)
        mRecyclerView = findViewById(R.id.recycler_diagnosis)

        setSupportActionBar(mToolbar)
        supportActionBar?.title = getString(R.string.diagnoses)

        val drawer = NavigationDrawer(this, mToolbar, 2)
        drawer.setNavigationDrawer()

        dbHandler = DBHandler.getInstance(this)

        mRecyclerView?.layoutManager = LinearLayoutManager(this)
        mRecyclerView?.setHasFixedSize(true)
    }

    private fun initializeListeners() {
        // TODO ADD LISTENER
        mFabAddDiagnosis?.setOnClickListener {
            //val addDoctorActivity = Intent(this, ActivityAddDoctor::class.java)
            //startActivity(addDoctorActivity)
        }
    }

    private fun initializeAdapter() {
        diagnosisList = dbHandler?.allDiagnosis
        mAdapter = DiagnosesAdapter(diagnosisList!!, this, INFO_ABOUT_DIAGNOSIS, PATIENT_ID)
        mRecyclerView?.adapter = mAdapter

    }

    override fun onRestart() {
        super.onRestart()
        mAdapter?.updateList(dbHandler?.allDiagnosis!!)
    }
}
