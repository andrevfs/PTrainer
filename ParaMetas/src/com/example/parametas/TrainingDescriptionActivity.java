package com.example.parametas;

import java.util.ArrayList;
import java.util.List;

import Database.DbGoals;
import Database.DbTraining;
import Database.DbTrainingsMapGoals;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TrainingDescriptionActivity extends Activity{
	protected static int posDetailed = -1;
	ActionBar ab;
	
	SQLiteDatabase db;
	DbTraining trainings;
	
	//
	SQLiteDatabase db2;
	SQLiteDatabase db3;

	DbGoals goals;
	DbTrainingsMapGoals map;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		trainings = new DbTraining(this);
		db = trainings.getWritableDatabase();

		goals = new DbGoals(this);
		db2 = goals.getWritableDatabase();

		map = new DbTrainingsMapGoals(this);
		db3 = map.getWritableDatabase();

		String[] columns = {DbTraining.TITLE, DbTraining.DESCRIPTION};

		Cursor c = db.query(true, DbTraining.TABLE_NAME, columns, null, null,
				null, null, null, null, null);
		c.moveToPosition(posDetailed);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_training_desription);

		TextView tv1 = (TextView)findViewById(R.id.tvTrainingDescription);
		tv1.setText(c.getString(c.getColumnIndex(DbTraining.TITLE)));
		tv1.setKeyListener(null);
		List goalList = createListGoalObjects();
        ArrayAdapter ad = new GoalListAdapter(this, R.layout.goal_row, goalList);
        ListView lv = (ListView) findViewById(R.id.lvGoalList);
        lv.setAdapter(ad);
        c.close();
	}
	
	@SuppressLint("NewApi")
	private List createListGoalObjects(){
		List l = new ArrayList();
		
		String[] columns0 = {DbTraining.C_ID};
		Cursor c = db.query(true, DbTraining.TABLE_NAME, columns0, null, null,
				null, null, null, null, null);
		c.moveToPosition(posDetailed);
		String id = c.getString(c.getColumnIndex(DbTraining.C_ID));
		c.close();
		
		String[] columns = {DbTrainingsMapGoals.C_ID, DbTrainingsMapGoals.ID_GOAL,
				DbTrainingsMapGoals.ID_TRAINING};

		Cursor c1 = db3.query(true, DbTrainingsMapGoals.TABLE_NAME, columns, null, null,
				null, null, null, null, null);
		
		Cursor c2 = db2.query(true, DbGoals.TABLE_NAME, DbGoals.allColumns, null, null,
				null, null, null, null, null);
		
		if(c1.moveToFirst()){
			do{
				if(!id.equals(c1.getString(c1.getColumnIndex(DbTrainingsMapGoals.ID_TRAINING)))) 
					continue;
					if(c2.moveToFirst()){
					do{
						if(c2.getString(c2.getColumnIndex(DbGoals.C_ID)).equals(c1.getString(c1.getColumnIndex(DbTrainingsMapGoals.ID_GOAL)))){
							l.add(new Goal(c2.getString(c2.getColumnIndex(DbGoals.TITLE)),
									c2.getString(c2.getColumnIndex(DbGoals.DESCRIPTION)),
									c2.getString(c2.getColumnIndex(DbGoals.START_TIME)),
									c2.getString(c2.getColumnIndex(DbGoals.END_TIME)),
									Integer.valueOf(c2.getString(c2.getColumnIndex(DbGoals.MILESTONE))),
									Integer.valueOf(c2.getString(c2.getColumnIndex(DbGoals.REACHED))),
									c2.getString(c2.getColumnIndex(DbGoals.UNIT))
									));
						}
					}while(c2.moveToNext());
					}
			}while(c1.moveToNext());
		}
		return l;
	}	
	
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.actionbar2, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
		    // Handle presses on the action bar items
		    switch (item.getItemId()) {
		    case R.id.discard_sign:
		    	delete();
		        	
		    }
		    return false;
	}
	
	public void delete(){

		Context context = this;
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);
		alertDialogBuilder.setTitle("Deseja excluir?");
		alertDialogBuilder
				.setMessage("Clique em 'Sim' para remover")
				.setCancelable(false)
				.setPositiveButton("Sim",
						new DialogInterface.OnClickListener() {
							@SuppressLint("NewApi")
							public void onClick(DialogInterface dialog, int id) {
								String[] columns = {DbTraining.C_ID};
								Cursor c = db.query(true,DbTraining.TABLE_NAME,columns, null, null, null, null, null, null, null);
								c.moveToPosition(posDetailed);
								db.delete(DbTraining.TABLE_NAME, DbTraining.C_ID +" = ?", new String[] {c.getString(c.getColumnIndex(DbTraining.C_ID))});
								c.close();
								db.close();
								finish();
							}
						})
				.setNegativeButton("Não",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
							}
						});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
		
	}
}
