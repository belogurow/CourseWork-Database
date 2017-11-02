package com.alexbelogurow.dbcoursework.activity.diagnosis

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.Menu
import android.widget.SearchView
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

        val swipe = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
                Log.d("swiped", viewHolder?.adapterPosition?.toString())
                val position = viewHolder?.adapterPosition!!

                dbHandler?.deleteDiagnosis(diagnosisList?.get(position)?.icd)
                diagnosisList = dbHandler?.allDiagnosis
                mAdapter?.updateList(diagnosisList!!)

                mAdapter?.notifyItemRemoved(position)
                mAdapter?.notifyItemRangeChanged(position, diagnosisList?.size!! - position)
            }

        }
        ItemTouchHelper(swipe).attachToRecyclerView(mRecyclerView)
    }

    private fun initializeListeners() {
        mFabAddDiagnosis?.setOnClickListener {
            val addDiagnosesActivity = Intent(this, AddDiagnosesActivity::class.java)
            startActivity(addDiagnosesActivity)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_diagnoses, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.menu_action_diagnoses_search)?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        searchView.setSearchableInfo(searchManager.
                getSearchableInfo(componentName))
        searchView.isSubmitButtonEnabled = true

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(text: String?): Boolean {
                diagnosisList = dbHandler?.getDiagnosesBySearch(text)
                mAdapter?.updateList(diagnosisList!!)
                return true
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}
