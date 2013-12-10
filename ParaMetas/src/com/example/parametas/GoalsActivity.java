package com.example.parametas;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class GoalsActivity extends ListFragment {
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		String[] goals = { "Correr 3000m", "Nadar 2000m", "Andar 1h" };

		ArrayAdapter<String> aa = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, goals);

		/* ListView lv = (ListView)findViewById(R.id.listView1); */
		setListAdapter(aa);

		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {

				switch (position) {
				case 0:
					Intent changeActivity = new Intent(getActivity(),
							GoalDetailedActivity.class);
					startActivityForResult(changeActivity, 1);
					break;
				case 1:
					Intent changeActivity1 = new Intent(getActivity(),
							GoalDetailedActivity.class);
					startActivityForResult(changeActivity1, 1);
					break;

				case 2:
					Intent changeActivity2 = new Intent(getActivity(),
							GoalDetailedActivity.class);
					startActivityForResult(changeActivity2, 1);
					break;
				}

			}

		});
		
		getListView().setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View view,
					int position, long id) {
				Intent changeActivity;
				switch (position) {
				case 0:
					changeActivity = new Intent(getActivity(),
							GoalCreationActivity.class);
					startActivityForResult(changeActivity, 1);
					break;
				case 1:
					changeActivity = new Intent(getActivity(),
							GoalCreationActivity.class);
					startActivityForResult(changeActivity, 1);
					break;
				case 2:
					changeActivity = new Intent(getActivity(),
							GoalCreationActivity.class);
					startActivityForResult(changeActivity, 1);
					break;
				}

				return false;
			}
			
		});
		
	}
}
