package com.example.parametas;

import java.util.ArrayList;

import Database.DbGoals;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class GoalDetailedActivity extends Activity {
	static int posDetailed = -1;
	SQLiteDatabase db;
	DbGoals goals;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		goals = new DbGoals(this);
		db = goals.getWritableDatabase();

		String[] columns = { DbGoals.TITLE, DbGoals.MILESTONE,
				DbGoals.DESCRIPTION, DbGoals.START_TIME, DbGoals.END_TIME,
				DbGoals.UNIT, DbGoals.REACHED, DbGoals.COLOR };

		Cursor c = db.query(true, DbGoals.TABLE_NAME, columns, null, null,
				null, null, null, null, null);
		c.moveToPosition(posDetailed);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goal_detailed);
		
		TextView textView = (TextView) findViewById(R.id.editText1);
		textView.setText(c.getString(c.getColumnIndex(DbGoals.TITLE)));
		textView.setKeyListener(null);
		textView = (TextView) findViewById(R.id.editText2);
		textView.setText(c.getString(c.getColumnIndex(DbGoals.DESCRIPTION)));
		textView.setKeyListener(null);
		
		Button b = (Button) findViewById(R.id.button1);
		b.setText("De: "+c.getString(c.getColumnIndex(DbGoals.START_TIME)));
		b = (Button) findViewById(R.id.button2);
		b.setText("Até: "+c.getString(c.getColumnIndex(DbGoals.END_TIME)));
		b = (Button) findViewById(R.id.button3);
		b.setText("Meta: "+c.getString(c.getColumnIndex(DbGoals.MILESTONE)));
		b = (Button) findViewById(R.id.button4);
		b.setText("Unidade: "+c.getString(c.getColumnIndex(DbGoals.UNIT)));
		b = (Button) findViewById(R.id.button5);
		b.setText("Alcancado: "+c.getString(c.getColumnIndex(DbGoals.REACHED)));
		ImageView v = (ImageView) findViewById(R.id.imageView1);
		v.setColorFilter(c.getInt(c.getColumnIndex(DbGoals.COLOR)));
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
							public void onClick(DialogInterface dialog, int id) {
//								query();	//TEST
								String[] columns = {DbGoals.C_ID};
								Cursor c = db.query(true,DbGoals.TABLE_NAME,columns, null, null, null, null, null, null, null);
								c.moveToPosition(posDetailed);
								db.delete(DbGoals.TABLE_NAME, DbGoals.C_ID +" = ?", new String[] {c.getString(c.getColumnIndex(DbGoals.C_ID))});
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
	@SuppressLint("NewApi")
	public void query(){
		String[] columns = {DbGoals.C_ID,DbGoals.TITLE, DbGoals.MILESTONE, DbGoals.DESCRIPTION, DbGoals.START_TIME, DbGoals.END_TIME, DbGoals.UNIT, DbGoals.COLOR, DbGoals.REACHED};
		Cursor c = db.query(true,DbGoals.TABLE_NAME,columns, null, null, null, null, null, null, null);
		c.moveToFirst();
		while(c.moveToNext()){
			String id = c.getString(c.getColumnIndex(DbGoals.C_ID));
			String title = c.getString(c.getColumnIndex(DbGoals.TITLE));
			String description = c.getString(c.getColumnIndex(DbGoals.DESCRIPTION));
			String stime = c.getString(c.getColumnIndex(DbGoals.START_TIME));
			String etime = c.getString(c.getColumnIndex(DbGoals.END_TIME));
			String milst = c.getString(c.getColumnIndex(DbGoals.MILESTONE));
			String reac = c.getString(c.getColumnIndex(DbGoals.REACHED));
			String unit = c.getString(c.getColumnIndex(DbGoals.UNIT));
			Toast.makeText(this,"ID = "+id+"\n"+
								"Title = "+title+"\n"+
								"Description = "+title+"\n"+
								"Start time= "+stime+"\n"+
								"End time= "+etime+"\n"+
								"Milestone= "+milst+"\n"+
								"Reached = "+reac+"\n"+
								"Unit = "+unit,Toast.LENGTH_SHORT).show();
		}
		c.close();
		
	}
}