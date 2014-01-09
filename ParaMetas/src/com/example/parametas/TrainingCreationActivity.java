package com.example.parametas;

import java.util.Calendar;

import Database.DbTraining;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public final class TrainingCreationActivity extends Activity {
	protected static int next;

	protected static int posDetailed = -1;

	private String title = " ";
	private String description = " ";
	SQLiteDatabase db;
	DbTraining trainings;
	
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		trainings = new DbTraining(this);
		db = trainings.getWritableDatabase();
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_training_creation);

		final Button button = (Button) findViewById(R.id.newGoal);
		GoalsTempActivity.next = next;

		if(posDetailed != -1){
			String[] columns = {DbTraining.TITLE, DbTraining.DESCRIPTION};

			final Cursor c = db.query(true, DbTraining.TABLE_NAME, columns, null, null,
					null, null, null, null, null);
			c.moveToPosition(posDetailed);
			
			TextView textView = (TextView) findViewById(R.id.textTrainingName);
			title = c.getString(c.getColumnIndex(DbTraining.TITLE));
			textView.setText(title);
			textView = (TextView) findViewById(R.id.textTrainingDescription);
			description = c.getString(c.getColumnIndex(DbTraining.DESCRIPTION));
			textView.setText(description);
			
			button.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					 EditText t = (EditText) findViewById(R.id.textTrainingName);
					 EditText desc = (EditText) findViewById(R.id.textTrainingDescription);
					 if(t!= null)
						 title = t.getText().toString();
					 if(desc!= null)
						 description = desc.getText().toString();
					
						ContentValues cv = new ContentValues();
						cv.put(DbTraining.TITLE, title);
						cv.put(DbTraining.DESCRIPTION, description);
						
						Cursor c = db.query(true,DbTraining.TABLE_NAME,DbTraining.allColumns, null, null, null, null, null, null, null);
						c.moveToPosition(posDetailed);
						String id =  c.getString(c.getColumnIndex(DbTraining.C_ID));
						db.update(DbTraining.TABLE_NAME, cv,DbTraining.C_ID+" = ?", new String[] {id});
						posDetailed = -1;
						c.close();
						db.close();
						GoalsTempActivity.trainingID = Integer.valueOf(id).longValue();
					Intent changeActivity = new Intent(
							TrainingCreationActivity.this, GoalsTempActivity.class);
					startActivityForResult(changeActivity, 1);
				}
			});
		}else{
			button.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					 EditText t = (EditText) findViewById(R.id.textTrainingName);
					 EditText desc = (EditText) findViewById(R.id.textTrainingDescription);
					 if(t!= null)
						 title = t.getText().toString();
					 if(desc!= null)
						 description = desc.getText().toString();
					
					 	// create the training in the database
						ContentValues cv = new ContentValues();
						cv.put(DbTraining.TITLE, title);
						cv.put(DbTraining.DESCRIPTION, description);
						long id = db.insert(DbTraining.TABLE_NAME, null, cv);
					GoalsTempActivity.trainingID = id; 
					Intent changeActivity = new Intent(
							TrainingCreationActivity.this, GoalsTempActivity.class);
					startActivityForResult(changeActivity, 1);
				}
			});
		}
	}
}
