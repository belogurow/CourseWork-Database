package com.alexbelogurow.dbcoursework.activity.treatment

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.alexbelogurow.dbcoursework.R
import com.alexbelogurow.dbcoursework.adapter.diagnosis.DiagnosesAdapter
import com.alexbelogurow.dbcoursework.adapter.treatment.TreatmentAdapter
import com.alexbelogurow.dbcoursework.model.Diagnosis
import com.alexbelogurow.dbcoursework.model.Treatment
import com.alexbelogurow.dbcoursework.util.DBHandler
import com.alexbelogurow.dbcoursework.util.NavigationDrawer

class ActivityTreatment : AppCompatActivity() {

    private val TAG = "TreatmentActvity"
    private val INFO_ABOUT_TREATMENT = 0

    private var mToolbar: Toolbar? = null
    private var mFabAddTreatment: FloatingActionButton? = null
    private var mRecyclerView: RecyclerView? = null

    private var dbHandler: DBHandler? = null
    private var mAdapter: TreatmentAdapter? = null

    private var treatmentsList: List<Treatment>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_treatment)

        initializeFields()
        initializeListeners()
        initializeAdapter()
    }

    private fun initializeFields() {
        mToolbar = findViewById(R.id.toolbar_treatment)
        mFabAddTreatment = findViewById(R.id.fab_add_treatment)
        mRecyclerView = findViewById(R.id.recycler_treatment)

        mFabAddTreatment?.hide()

        setSupportActionBar(mToolbar)
        supportActionBar?.title = getString(R.string.treatment)

        val drawer = NavigationDrawer(this, mToolbar, 3)
        drawer.setNavigationDrawer()

        dbHandler = DBHandler.getInstance(this)

        mRecyclerView?.layoutManager = LinearLayoutManager(this)
        //mRecyclerView?.setHasFixedSize(true)
    }

    private fun initializeListeners() {
        // TODO ADD Listener
        mFabAddTreatment?.setOnClickListener {
            //val addDoctorActivity = Intent(this, ActivityAddDoctor::class.java)
            //startActivity(addDoctorActivity)
        }
    }

    private fun initializeAdapter() {
        treatmentsList = dbHandler?.allTreatments
        mAdapter = TreatmentAdapter(treatmentsList!!, this, INFO_ABOUT_TREATMENT)
        mRecyclerView?.adapter = mAdapter
    }

    override fun onRestart() {
        super.onRestart()
        mAdapter?.updateList(dbHandler?.allTreatments!!)
    }
}
