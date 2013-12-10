package com.example.parametas;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GoalDetailedActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goal_detailed);
		TextView textView = (TextView) findViewById(R.id.editText1);
		textView.setKeyListener(null);
		textView = (TextView) findViewById(R.id.editText2);
		textView.setKeyListener(null);
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