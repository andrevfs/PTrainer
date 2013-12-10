package com.example.parametas;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public final class TrainingCreationActivity extends Activity {
	protected static int next;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_training_creation);

		final Button button = (Button) findViewById(R.id.newGoal);
		GoalsTempActivity.next = next;
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent changeActivity = new Intent(
						TrainingCreationActivity.this, GoalsTempActivity.class);
				startActivityForResult(changeActivity, 1);
			}
		});
	}

}
