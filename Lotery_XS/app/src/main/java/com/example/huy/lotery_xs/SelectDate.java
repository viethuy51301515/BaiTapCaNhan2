package com.example.huy.lotery_xs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Viethuy_Iris on 4/28/2017.
 */

public class SelectDate extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    public SelectDate() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH)+1;
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, yy, mm, dd);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        TextView textView = (TextView)getActivity().findViewById(R.id.showDate);
        if(month < 9)
        {
            textView.setText(dayOfMonth+"-"+"0"+(month+1));
        }
        else {
        textView.setText(dayOfMonth+"-"+(month+1));}
    }
}
