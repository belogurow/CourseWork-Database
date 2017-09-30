package com.alexbelogurow.dbcoursework.activity.patient

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import com.alexbelogurow.dbcoursework.adapter.diagnosis.DiagnosesAdapter
import com.alexbelogurow.dbcoursework.util.DBHandler
import com.alexbelogurow.dbcoursework.model.Diagnosis
import com.alexbelogurow.dbcoursework.R
import android.view.MenuItem


class ActivityAddDiagnosesForPatient : AppCompatActivity() {

    private val TAG = "ActivityDiagnoses"
    private val ADD_DIAGNOSES_TO_PATIENT = 1;
    private var EXTRA_PATIENT_ID = "PATIENT_ID"

    private var mToolbar: Toolbar? = null
    private var mFabAddDiagnosis: FloatingActionButton? = null
    private var mRecyclerView: RecyclerView? = null
    private var mCoordinatorLayout: CoordinatorLayout? = null

    private var dbHandler: DBHandler? = null
    private var mAdapter: DiagnosesAdapter? = null

    private var diagnosisList: List<Diagnosis>? = null
    private var patientId: Int? = null

    private var context = this

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
        mCoordinatorLayout = findViewById(R.id.coordinator_diagnosis)

        setSupportActionBar(mToolbar)
        supportActionBar?.title = getString(R.string.add_diagnoses_for_patient)

        mFabAddDiagnosis?.hide()

        dbHandler = DBHandler.getInstance(this)

        mRecyclerView?.layoutManager = LinearLayoutManager(this)
        mRecyclerView?.setHasFixedSize(true)
    }



    private fun initializeAdapter() {
        diagnosisList = dbHandler?.allDiagnosis
        mAdapter = DiagnosesAdapter(diagnosisList!!, this, ADD_DIAGNOSES_TO_PATIENT, patientId!!)
        mRecyclerView?.adapter = mAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_activity_add_diagnoses, menu)

        val buttonDone = menu?.findItem(R.id.menu_action_add_diagnoses)
        mAdapter?.setButtonDone(buttonDone)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        when (id) {
            R.id.menu_action_add_diagnoses  -> {
                diagnosisList?.forEach {
                    if (it.isSelected) {
                        dbHandler?.addDiagnosisForPatient(it, patientId)

                        Snackbar.make(mCoordinatorLayout!!,
                                        getString(R.string.patient_was_added),
                                        Snackbar.LENGTH_SHORT)
                                .addCallback(object: Snackbar.Callback() {
                                    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                                        super.onDismissed(transientBottomBar, event)

                                        startActivity(Intent(context, ActivityPatient::class.java))
                                    }
                                })
                                .show()

                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
