package com.alexbelogurow.dbcoursework.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog;
import android.widget.Button;
import android.widget.TextView;

import com.alexbelogurow.dbcoursework.R;

import java.util.Calendar;

/**
 * Created by alexbelogurow on 07.05.17.
 */

public class DatePicker extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private String date;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar calendar = Calendar.getInstance();
        Dialog picker = new DatePickerDialog(getActivity(), this,
                1990, Calendar.MONTH, Calendar.DAY_OF_MONTH);

        picker.setTitle("Choose Birth Date");
        return picker;
    }

    @Override
    public void onStart() {
        super.onStart();

        /*
        Button nButton =  ((AlertDialog) getDialog())
                .getButton(DialogInterface.BUTTON_POSITIVE);
        nButton.setText("Pick");
        */
    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        TextView textViewDate = (TextView) getActivity().findViewById(R.id.textViewPatientDate);
        textViewDate.setText(dayOfMonth + "/" + month + "/" + year);
        date = dayOfMonth + "/" + month + "/" + year;
    }


    public String getDate() {
        return date;
    }
}
