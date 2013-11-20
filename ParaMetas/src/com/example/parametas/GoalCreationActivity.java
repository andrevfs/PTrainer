package com.example.parametas;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class GoalCreationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goal_creation);

         final Button button = (Button) findViewById(R.id.button3);
         button.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
            	 Intent changeActivity = new Intent(
							GoalCreationActivity.this,
							MainActivity.class);
					startActivityForResult(changeActivity, 1);
             }
         });
         
	}
	
	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.goal_description, menu);
		return true;
	}*/
	
	/** Called when the user clicks the Send button */
	public void createGoal(View view) {
	    // create at database the goal
	}
}