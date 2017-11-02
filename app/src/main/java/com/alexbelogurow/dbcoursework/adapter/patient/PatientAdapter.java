package com.alexbelogurow.dbcoursework.adapter.patient;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.support.v7.widget.helper.ItemTouchHelper.SimpleCallback;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexbelogurow.dbcoursework.activity.patient.ActivityPatientInfo;
import com.alexbelogurow.dbcoursework.util.DBHandler;
import com.alexbelogurow.dbcoursework.model.Patient;
import com.alexbelogurow.dbcoursework.model.Person;
import com.alexbelogurow.dbcoursework.R;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;


import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.text.Regex;

/**
 * Created by alexbelogurow on 07.05.17.
 */

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder>{

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
        private ImageView mImageViewGender;

        public PatientViewHolder(View itemView) {
            super(itemView);

            mCardView = itemView.findViewById(R.id.cardViewPatientInfo);
            mTextViewPatientInfoName = itemView.findViewById(R.id.textViewPatientInfoName);
            mTextViewPatientInfoLocation = itemView.findViewById(R.id.textViewPatientInfoLocation);
            mTextViewPatientInfoBirthDate = itemView.findViewById(R.id.textViewPatientInfoBirthDate);
            mImageViewGender = itemView.findViewById(R.id.imageViewPatientInfoGender);
        }
    }


    @Override
    public PatientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_patient, parent, false);
        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PatientViewHolder holder, int position) {
        DBHandler dbhandler = DBHandler.getInstance(context);
        final Patient patient = patientList.get(position);
        Person person = dbhandler.getPerson(patient.getPersonID());

        holder.mTextViewPatientInfoName.setText(person.getFullName());
        holder.mTextViewPatientInfoLocation.setText(patient.getLocation());
        holder.mTextViewPatientInfoBirthDate.setText(getAge(person.getBirthDate()));
        if (person.getSex().equals("Male")) {
            Drawable drawableMale = new IconicsDrawable(context)
                    .icon(FontAwesome.Icon.faw_male)
                    .color(ContextCompat.getColor(context, R.color.malePick))
                    .sizeDp(64);
            holder.mImageViewGender.setImageDrawable(drawableMale);
        } else {
            Drawable drawableFemale = new IconicsDrawable(context)
                    .icon(FontAwesome.Icon.faw_female)
                    .color(ContextCompat.getColor(context, R.color.femalePick))
                    .sizeDp(64);
            holder.mImageViewGender.setImageDrawable(drawableFemale);
        }
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

    private String getAge(String birthDate) {
        String date[] = birthDate.split("/");
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(Integer.parseInt(date[2]),
                Integer.parseInt(date[1]),
                Integer.parseInt(date[1]));

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        return "Возраст - " + age;
    }

//    private fun getAge(birthDate: String): String {
//        val date = birthDate.split("/".toRegex())
//        val dob = Calendar.getInstance()
//        val today = Calendar.getInstance()
//
//        dob.set(date[2].toInt(), date[1].toInt(), date[1].toInt())
//
//        var age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)
//
//        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
//            age--
//        }
//
//        val ageInt = age
//        return "Возраст - ${ageInt.toString()}"
//    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }
}
