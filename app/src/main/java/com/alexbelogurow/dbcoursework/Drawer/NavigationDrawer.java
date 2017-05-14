package com.alexbelogurow.dbcoursework.Drawer;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.alexbelogurow.dbcoursework.Activity.ActivityDoctor;
import com.alexbelogurow.dbcoursework.Activity.ActivityPatient;
import com.alexbelogurow.dbcoursework.R;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

/**
 * Created by alexbelogurow on 14.05.17.
 */

public class NavigationDrawer {
    private Activity mActivity;
    private Toolbar mToolbar;
    private int currentPosition;

    public NavigationDrawer(Activity activity, Toolbar toolbar, int currentPosition) {
        mActivity = activity;
        mToolbar = toolbar;
        this.currentPosition = currentPosition;
    }

    public void setNavigationDrawer() {
            AccountHeader headerResult = new AccountHeaderBuilder()
                    .withActivity(mActivity)
                    .withHeaderBackground(R.drawable.hospital)
                /*.addProfiles(
                        new ProfileDrawerItem()
                                .withName("Hospital")
                                //.withIcon(R.mipmap.ic_launcher_app)
                                .withDisabledTextColor(7)
                )
                /*.withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                }) */
                    .withSelectionListEnabledForSingleProfile(false)
                    .build();

            PrimaryDrawerItem patients = new PrimaryDrawerItem()
                    .withIdentifier(0)
                    .withName(R.string.patients)
                    .withIcon(R.drawable.ic_patient)
                    .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                            Intent intentPatients = new Intent(
                                    mActivity.getApplicationContext(),
                                    ActivityPatient.class);
                            mActivity.startActivity(intentPatients);
                            return true;
                        }
                    });

            PrimaryDrawerItem doctors = new PrimaryDrawerItem()
                    .withIdentifier(1)
                    .withName(R.string.doctors)
                    .withIcon(R.drawable.ic_doctors)
                    .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                            Intent intentDoctors = new Intent(
                                    mActivity.getApplicationContext(),
                                    ActivityDoctor.class);
                            mActivity.startActivity(intentDoctors);
                            return true;
                        }
                    });

            final Drawer drawerResult = new DrawerBuilder()
                    .withActivity(mActivity)
                    .withToolbar(mToolbar)
                    .withAccountHeader(headerResult)
                    .addDrawerItems(
                            patients,
                            doctors,
                            new DividerDrawerItem()
                            //item2,
                            //item3
                    )
                    .withSelectedItem(currentPosition)
                    .build();
        }
}
