package com.alexbelogurow.dbcoursework.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog;
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

        picker.setTitle(getString(R.string.birth_date));
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
        TextInputLayout textInputDate =  getActivity().findViewById(R.id.text_input_patient_birth_date);
        textInputDate.getEditText().setText(dayOfMonth + "/" + (month+1) + "/" + year);
    }


    public String getDate() {
        return date;
    }
}
