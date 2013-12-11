package com.example.parametas;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.DatePicker;

public class DatePickerFragment extends DialogFragment
                            implements DatePickerDialog.OnDateSetListener {
	private int id;
    private String initial;
    
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
    	final Button testButton = (Button) getActivity().findViewById(this.id);
		testButton.setText(this.initial+" "+day+"/"+(month+1)+"/"+year);
    }
    
	public void setId(int id) {
		this.id = id;
	}

	public void setInitText(String textInit) {
		this.initial = textInit;
	}
}