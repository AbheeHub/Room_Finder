package com.room.saksham.kamre;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DatePickerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DatePickerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatePickerFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedBundleInstance){
        //use current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener)getActivity(),year,month,day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()); //for setting minimum date as current date
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000); //for setting maximum date upto 14 days more with current date i.e. total 15 days.
        return datePickerDialog;

//        //listener to be implemented by the parent activity
//        DatePickerDialog.OnDateSetListener listener = (DatePickerDialog.OnDateSetListener)getActivity();
//
//        //create and return a new instance of DatePickerDialog
//        return new DatePickerDialog(getActivity(), listener, year, month, day);
    }

}
