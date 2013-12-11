package com.example.parametas;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

public class TrainingDescriptionActivity extends Activity{
	ActionBar ab;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_training_desription);
         
		List goalList = createListGoalObjects();
        ArrayAdapter ad = new GoalListAdapter(this, R.layout.goal_row, goalList);
        ListView lv = (ListView) findViewById(R.id.lvGoalList);
        lv.setAdapter(ad);
        TextView tv1 = (TextView)findViewById(R.id.tvTrainingDescription);
        tv1.setKeyListener(null);
         
	}
	
	private List createListGoalObjects(){
		List l = new ArrayList();
		l.add(new Goal("Correr 100km","10km por dia","10/12/2013","20/12/2013"
				,100,0,"km"));
		l.add(new Goal("Nadar 20km","1km por dia","01/01/2014","20/01/2014"
				,20,3,"km"));
		l.add(new Goal("Fazer flexões","30 por dia","15/12/2013","30/01/2014"
				,40,12,"dias"));
		
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
							public void onClick(DialogInterface dialog, int id) {
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
