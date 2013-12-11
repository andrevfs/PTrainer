package com.example.parametas;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GoalsTempActivity extends Activity {
	public static int next;
	ListView listView1;

	String[] goals = { "Meta 1", "Meta 2", "Meta 3" };
	ListView l;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goals_temp);

		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, goals);

		l = (ListView) findViewById(R.id.listView1);
		l.setAdapter(aa);
		l.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {
				switch (position) {
				case 0:
					//put the item in the array
					break;
				case 1:
					//put the item in the array
					break;
				case 2:
					//put the item in the array
					break;
				}

			}

		});
	}
	
	public void selectedGoals(View v){
		// To insert selected items in the BD
		if(next == 0){
			Intent intent = new Intent(this, MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		}else{
			finish();
		}
	}
}
