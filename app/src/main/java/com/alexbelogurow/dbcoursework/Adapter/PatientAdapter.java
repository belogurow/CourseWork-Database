package com.alexbelogurow.dbcoursework.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alexbelogurow.dbcoursework.Model.Patient;
import com.alexbelogurow.dbcoursework.R;


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
        private CardView mCardView;
        private TextView mTextViewPatientInfo;

        public PatientViewHolder(View itemView) {
            super(itemView);

            mCardView = (CardView) itemView.findViewById(R.id.cardViewPatientInfo);
            mTextViewPatientInfo = (TextView) itemView.findViewById(R.id.textViewPatientInfo);
        }
    }

    @Override
    public PatientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_item, parent, false);
        PatientViewHolder patientViewHolder = new PatientViewHolder(view);

        return patientViewHolder;
    }

    @Override
    public void onBindViewHolder(PatientViewHolder holder, int position) {
        holder.mTextViewPatientInfo.setText(patientList.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }



}
