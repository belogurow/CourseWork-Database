package com.alexbelogurow.dbcoursework.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import com.alexbelogurow.dbcoursework.Adapter.DiagnosesAdapter
import com.alexbelogurow.dbcoursework.Adapter.DoctorAdapter
import com.alexbelogurow.dbcoursework.DataBase.DBHandler
import com.alexbelogurow.dbcoursework.Drawer.NavigationDrawer
import com.alexbelogurow.dbcoursework.Model.Diagnosis
import com.alexbelogurow.dbcoursework.Model.Doctor
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

        initializeViews()
        initializeListeners()
        initializeAdapter()
    }

    private fun initializeViews() {
        mToolbar = findViewById(R.id.toolbar_diagnosis)
        mFabAddDiagnosis = findViewById(R.id.fab_add_diagnosis)
        mRecyclerView = findViewById(R.id.recycler_diagnosis)

        setSupportActionBar(mToolbar)
        supportActionBar?.title = getString(R.string.diagnoses)

        val drawer = NavigationDrawer(this, mToolbar, 2)
        drawer.setNavigationDrawer()

        dbHandler = DBHandler(this)

        mRecyclerView?.layoutManager = LinearLayoutManager(this)
        mRecyclerView?.setHasFixedSize(true)
    }

    private fun initializeListeners() {
        mFabAddDiagnosis?.setOnClickListener {
            //val addDoctorActivity = Intent(this, ActivityAddDoctor::class.java)
            //startActivity(addDoctorActivity)
        }
    }

    private fun initializeAdapter() {
        addDiagnoses()
        diagnosisList = dbHandler?.allDiagnosis

        mAdapter = DiagnosesAdapter(diagnosisList!!, this, INFO_ABOUT_DIAGNOSIS, PATIENT_ID)
        mRecyclerView?.adapter = mAdapter

    }

    private fun addDiagnoses() {
        dbHandler?.addDiagnosis(Diagnosis(
                "C16.0",
                "Злокачественное новообразование кардии желудка"
        ))

        dbHandler?.addDiagnosis(Diagnosis(
                "C69.6",
                "Злокачественное новообразование глазницы"
        ))
        dbHandler?.addDiagnosis(Diagnosis(
                "A15.2",
                "Туберкулез лёгких, подтвержденный гистологически"
        ))
        dbHandler?.addDiagnosis(Diagnosis(
                "A16.2",
                "Туберкулез лёгких без упоминания о бактериологическом или гистологическом подтверждении"
        ))
        dbHandler?.addDiagnosis(Diagnosis(
                "A17.8",
                "Туберкулез нервной системы других локализаций"
        ))
        dbHandler?.addDiagnosis(Diagnosis(
                "A83.6",
                "Болезнь, вызванная вирусом Роцио"
        ))
        dbHandler?.addDiagnosis(Diagnosis(
                "A84.1",
                "Центральноевропейский клещевой энцефалит"
        ))
        dbHandler?.addDiagnosis(Diagnosis(
                "B01.9",
                "Ветряная оспа без осложнений"
        ))
        dbHandler?.addDiagnosis(Diagnosis(
                "J20.0",
                "Острых бронхит, вызванный Mycoplasma pneumoniae"
        ))
        dbHandler?.addDiagnosis(Diagnosis(
                "Z01.0",
                "Обследование глаз и зрения"
        ))
        dbHandler?.addDiagnosis(Diagnosis(
                "Z03.4",
                "Наблюдение при подозрении на инфаркт миокарда"
        ))
        dbHandler?.addDiagnosis(Diagnosis(
                "Z04.6",
                "Общее психиатрическое обследование по запросу учреждения"
        ))
        dbHandler?.addDiagnosis(Diagnosis(
                "Z10.0",
                "Профессиональное медицинское обследование"
        ))
        dbHandler?.addDiagnosis(Diagnosis(
                "Z13.1",
                "Специальное скрининговое обследование с целью выявления сахарного диабета"
        ))

    }

    override fun onRestart() {
        super.onRestart()
        //TODO replace !!
        mAdapter?.updateList(dbHandler?.allDiagnosis!!)
    }
}
