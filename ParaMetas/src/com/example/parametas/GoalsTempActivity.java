package com.example.parametas;

import java.util.ArrayList;
import java.util.HashMap;

import Database.DbGoals;
import Database.DbTraining;
import Database.DbTrainingsMapGoals;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

@SuppressLint("NewApi")
public class GoalsTempActivity extends Activity {
	public static int next;
	public static long trainingID;
	ListView listView1;
	ListView l;
	SQLiteDatabase db;
	DbGoals goals;
	boolean[] selecteds;
	
	//TODO
	DbTrainingsMapGoals map;
	SQLiteDatabase db2;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		goals = new DbGoals(this);
		map = new DbTrainingsMapGoals(this);
		db = goals.getWritableDatabase();
		db2 = map.getWritableDatabase();//TODO
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goals_temp);


		String[] columns = { DbGoals.TITLE, DbGoals.MILESTONE,
				DbGoals.DESCRIPTION, DbGoals.START_TIME, DbGoals.END_TIME,
				DbGoals.UNIT, DbGoals.REACHED };

		Cursor c = db.query(true, DbGoals.TABLE_NAME, columns, null, null,
				null, null, null, null, null);
		ArrayList<String> arrGoals = new ArrayList<String>();
		
		int count = 1;
		if(c.moveToFirst())
			arrGoals.add(c.getString(c.getColumnIndex(DbGoals.TITLE)));
	
		
		while (c.moveToNext()) {
			String title = c.getString(c.getColumnIndex(DbGoals.TITLE));
			if (title != null)
				arrGoals.add(title);
			else
				arrGoals.add("");
			count++;
		}
		c.close();
		selecteds = new boolean[count];
		
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, arrGoals);

		l = (ListView) findViewById(R.id.listView1);
		l.setAdapter(aa);
		l.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {
				//mark the items selected
				if(selecteds[position] == false)
					selecteds[position] = true;
				else
					selecteds[position] = false;
			}
		});
	}
	
	public void selectedGoals(View v){
		if(next == 0){//inserting
			Cursor c = null;
			for(int i=0;i<selecteds.length;i++)
				if(selecteds[i] == true){
					ContentValues cv = new ContentValues();
					String[] columns = {DbGoals.C_ID};
					c = db.query(true, DbGoals.TABLE_NAME, columns, null, null,
							null, null, null, null, null);
					c.moveToPosition(i);
					cv.put(DbTrainingsMapGoals.ID_GOAL,c.getString(c.getColumnIndex(DbGoals.C_ID)));
					
					cv.put(DbTrainingsMapGoals.ID_TRAINING, (int) trainingID);
					db2.insert(DbTrainingsMapGoals.TABLE_NAME, null, cv);
				}
			if(c!=null)
				c.close();
			db.close();
			db2.close();
			Intent intent = new Intent(this, MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		}else{//editing
			finish();
		}
	}
}
