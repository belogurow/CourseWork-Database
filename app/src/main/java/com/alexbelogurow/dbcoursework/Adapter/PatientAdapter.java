package com.alexbelogurow.dbcoursework.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alexbelogurow.dbcoursework.Model.Patient;

import java.util.List;

/**
 * Created by alexbelogurow on 07.05.17.
 */

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder> {

    private List<Patient> patientList;
    private Context context;


    public PatientAdapter(List<Patient> patientList, Context context) {
        this.patientList = patientList;
        this.context = context;
    }

    public static class PatientViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewPatientInfo;
        private CardView mCardView;


        public PatientViewHolder(View itemView) {
            super(itemView);

            
        }
    }

    @Override
    public PatientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(PatientViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }



}
