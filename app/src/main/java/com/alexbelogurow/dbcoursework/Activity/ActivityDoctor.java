package com.alexbelogurow.dbcoursework.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.alexbelogurow.dbcoursework.Drawer.NavigationDrawer;
import com.alexbelogurow.dbcoursework.R;

public class ActivityDoctor extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        mToolbar = (Toolbar) findViewById(R.id.toolbarDoctor);
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.doctors));
        }

        new NavigationDrawer(this, mToolbar, 1).setNavigationDrawer();
    }
}
