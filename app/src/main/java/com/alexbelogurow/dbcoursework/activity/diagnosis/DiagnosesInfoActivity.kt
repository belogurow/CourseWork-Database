package com.alexbelogurow.dbcoursework.activity.diagnosis

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.TextView
import com.alexbelogurow.dbcoursework.R
import com.alexbelogurow.dbcoursework.adapter.treatment.TreatmentAdapter
import com.alexbelogurow.dbcoursework.util.DBHandler
import kotlinx.android.synthetic.main.activity_diagnoses_info.*

class DiagnosesInfoActivity : AppCompatActivity() {

    private val EXTRA_DIAGNOSIS_ICD = "ICD"

    private val INFO_ABOUT_TREATMENT = 0

    private var mToolbar: Toolbar? = null
    private var mTextViewICD: TextView? = null
    private var mTextViewDiagnosesName: TextView? = null
    private var mRecyclerView: RecyclerView? = null

    private var mAdapter: TreatmentAdapter? = null
    private var dbHandler: DBHandler? = null

    private var icd: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnoses_info)

        initializeViews()
        initializeAdapter()
    }

    private fun initializeViews() {
        icd = intent.getStringExtra(EXTRA_DIAGNOSIS_ICD)

        mToolbar = findViewById(R.id.toolbar_diagnoses_info)
        mTextViewICD = findViewById(R.id.text_diagnoses_info_ICD)
        mTextViewDiagnosesName = findViewById(R.id.text_diagnoses_info_name)
        mRecyclerView = findViewById(R.id.recycler_diagnoses_info_treatments)
        mRecyclerView?.layoutManager = LinearLayoutManager(this)

        setSupportActionBar(mToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        dbHandler = DBHandler.getInstance(this)

        val diagnoses = dbHandler?.getDiagnosis(icd)
        mTextViewICD?.text = diagnoses?.icd
        mTextViewDiagnosesName?.text = diagnoses?.name
    }

    private fun initializeAdapter() {
        val treatmentsList = dbHandler?.getTreatmentsByDiagnosis(
                dbHandler?.getDiagnosis(icd))

        mAdapter = TreatmentAdapter(treatmentsList!!, this, INFO_ABOUT_TREATMENT)
        mRecyclerView?.adapter = mAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
