package com.alexbelogurow.dbcoursework.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.alexbelogurow.dbcoursework.Adapter.PatientAdapter;
import com.alexbelogurow.dbcoursework.DataBase.DBHandler;
import com.alexbelogurow.dbcoursework.Drawer.NavigationDrawer;
import com.alexbelogurow.dbcoursework.Model.Patient;
import com.alexbelogurow.dbcoursework.R;
import com.facebook.stetho.Stetho;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.List;

public class ActivityPatient extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private FloatingActionButton mFabAddPatient;

    private DBHandler dbHandler;
    private PatientAdapter mAdapter;

    private Toolbar mToolbar;

    private List<Patient> patientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        initializeStetho();

        // FOR UPDATE DATABASE
        // getApplicationContext().deleteDatabase("Hospital");

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewPatient);
        mFabAddPatient = (FloatingActionButton) findViewById(R.id.fabAddPatient);
        mToolbar = (Toolbar) findViewById(R.id.toolbarPatient);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.patients));
        }

        dbHandler = new DBHandler(getApplicationContext());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setHasFixedSize(true);

        mFabAddPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addPatientActivity = new Intent(getApplicationContext(), ActivityAddPatient.class);
                startActivity(addPatientActivity);
            }
        });

        initializeAdapter();
        new NavigationDrawer(this, mToolbar, 0).setNavigationDrawer();
    }



    private void initializeAdapter() {
        patientList = dbHandler.getAllPatients();
        mAdapter = new PatientAdapter(patientList, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initializeStetho() {
        // Create an InitializerBuilder
        Stetho.InitializerBuilder initializerBuilder =
                Stetho.newInitializerBuilder(this);

        // Enable Chrome DevTools
        initializerBuilder.enableWebKitInspector(
                Stetho.defaultInspectorModulesProvider(this)
        );

        // Enable command line interface
        initializerBuilder.enableDumpapp(
                Stetho.defaultDumperPluginsProvider(this)
        );

        // Use the InitializerBuilder to generate an Initializer
        Stetho.Initializer initializer = initializerBuilder.build();

        // Initialize Stetho with the Initializer
        Stetho.initialize(initializer);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        mAdapter.updateList(dbHandler.getAllPatients());
    }
}
