package com.alexbelogurow.dbcoursework.activity.diagnosis

import android.content.Context
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
import android.view.MenuItem
import android.widget.Toast
import com.alexbelogurow.dbcoursework.R
import com.alexbelogurow.dbcoursework.activity.patient.ActivityPatient
import com.alexbelogurow.dbcoursework.adapter.treatment.TreatmentAdapter
import com.alexbelogurow.dbcoursework.model.Treatment
import com.alexbelogurow.dbcoursework.util.DBHandler

class AddTreatmentsForDiagnosesActivity : AppCompatActivity() {

    private val EXTRA_DIAGNOSIS_ICD = "ICD"
    private val ADD_TREATMENTS_FOR_DIAGNOSIS = 1

    private var mToolbar: Toolbar? = null
    private var mFabAddTreatment: FloatingActionButton? = null
    private var mRecyclerView: RecyclerView? = null
    private var mCoordinatorLayout: CoordinatorLayout? = null

    private var dbHandler: DBHandler? = null
    private var mAdapter: TreatmentAdapter? = null
    private var context: Context? = null

    private var treatmentsList: List<Treatment>? = null
    var icd: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_treatment)

        initializeFields()
        initializeAdapter()
    }

    private fun initializeFields() {
        icd = intent.getStringExtra(EXTRA_DIAGNOSIS_ICD)

        mToolbar = findViewById(R.id.toolbar_treatment)
        mFabAddTreatment = findViewById(R.id.fab_add_treatment)
        mRecyclerView = findViewById(R.id.recycler_treatment)
        mCoordinatorLayout = findViewById(R.id.coordinator_treatment)

        setSupportActionBar(mToolbar)
        supportActionBar?.title = getString(R.string.title_activity_add_treatment_for_diagnoses)

        mFabAddTreatment?.hide()

        dbHandler = DBHandler.getInstance(this)
        context = this

        mRecyclerView?.layoutManager = LinearLayoutManager(this)
    }

    private fun initializeAdapter() {
        treatmentsList = dbHandler?.allTreatments
        mAdapter = TreatmentAdapter(treatmentsList!!, this, ADD_TREATMENTS_FOR_DIAGNOSIS)
        mRecyclerView?.adapter = mAdapter
    }

    override fun onRestart() {
        super.onRestart()
        mAdapter?.updateList(dbHandler?.allTreatments!!)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_add_treatments_for_diagnoses, menu)

        val buttonDone = menu?.findItem(R.id.menu_action_add_treatments)
        mAdapter?.setButtonDone(buttonDone)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        when (id) {
            R.id.menu_action_add_treatments  -> {
                treatmentsList?.forEach {
                    if (it.isSelected) {
                        dbHandler?.addTreatmentForDiagnosis(it.treatmentId, icd)

                        Snackbar.make(mCoordinatorLayout!!,
                                getString(R.string.diagnosis_was_added),
                                Snackbar.LENGTH_SHORT)
                                .addCallback(object : Snackbar.Callback() {
                                    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                                        super.onDismissed(transientBottomBar, event)

                                        startActivity(Intent(context, ActivityDiagnoses::class.java))
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
