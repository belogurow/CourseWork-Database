package com.alexbelogurow.dbcoursework.adapter.patient;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alexbelogurow.dbcoursework.activity.patient.ActivityPatientInfo;
import com.alexbelogurow.dbcoursework.util.DBHandler;
import com.alexbelogurow.dbcoursework.model.Patient;
import com.alexbelogurow.dbcoursework.model.Person;
import com.alexbelogurow.dbcoursework.R;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by alexbelogurow on 07.05.17.
 */

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder> {

    // EXTRA
    private final String EXTRA_PATIENT_ID = "PATIENT_ID";

    private List<Patient> patientList;
    private Context context;


    public PatientAdapter(List<Patient> patientList, Context context) {
        this.patientList = patientList;
        this.context = context;
    }

    public void updateList(List<Patient> newPatientList) {
        patientList = newPatientList;
        notifyDataSetChanged();
    }

    public static class PatientViewHolder extends RecyclerView.ViewHolder {
        private CardView mCardView;
        private TextView mTextViewPatientInfoName,
                mTextViewPatientInfoLocation,
                mTextViewPatientInfoBirthDate;
        private CircleImageView mCircleImageViewGender;

        public PatientViewHolder(View itemView) {
            super(itemView);

            mCardView = (CardView) itemView.findViewById(R.id.cardViewPatientInfo);
            mTextViewPatientInfoName = (TextView) itemView.findViewById(R.id.textViewPatientInfoName);
            mTextViewPatientInfoLocation = (TextView) itemView.findViewById(R.id.textViewPatientInfoLocation);
            mTextViewPatientInfoBirthDate = (TextView) itemView.findViewById(R.id.textViewPatientInfoBirthDate);
            mCircleImageViewGender = (CircleImageView) itemView.findViewById(R.id.imageViewPatientInfoGender);
        }
    }


    @Override
    public PatientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_item, parent, false);
        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PatientViewHolder holder, int position) {
        DBHandler dbhandler = DBHandler.getInstance(context);
        final Patient patient = patientList.get(position);
        Person person = dbhandler.getPerson(patient.getPersonID());

        holder.mTextViewPatientInfoName.setText(person.getFullName());
        holder.mTextViewPatientInfoLocation.setText(patient.getLocation());
        holder.mTextViewPatientInfoBirthDate.setText(person.getBirthDate());
        holder.mCircleImageViewGender.setImageDrawable(
                ContextCompat.getDrawable(context, person.getGenderPhotoID()));
        dbhandler.close();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent patientInfoActivity = new Intent(context, ActivityPatientInfo.class);
                patientInfoActivity.putExtra(EXTRA_PATIENT_ID, patient.getPatientID());
                patientInfoActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(patientInfoActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }



}
