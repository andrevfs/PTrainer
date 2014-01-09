package com.example.parametas;

import java.util.ArrayList;

import Database.DbGoals;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

@SuppressLint("NewApi")
public class GoalsActivity extends ListFragment {

	SQLiteDatabase db;
	DbGoals goals;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		goals = new DbGoals(getActivity());
		db = goals.getWritableDatabase();

		super.onActivityCreated(savedInstanceState);

		String[] columns = { DbGoals.TITLE, DbGoals.MILESTONE,
				DbGoals.DESCRIPTION, DbGoals.START_TIME, DbGoals.END_TIME,
				DbGoals.UNIT, DbGoals.REACHED };

		Cursor c = db.query(true, DbGoals.TABLE_NAME, columns, null, null,
				null, null, null, null, null);
		ArrayList<String> arrGoals = new ArrayList<String>();
		if(c.moveToFirst())
			arrGoals.add(c.getString(c.getColumnIndex(DbGoals.TITLE)));
		while (c.moveToNext()) {
			String title = c.getString(c.getColumnIndex(DbGoals.TITLE));
			if (title != null)
				arrGoals.add(title);
			else
				arrGoals.add("");
		}
		c.close();

		ArrayAdapter<String> aa = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, arrGoals);

		/* ListView lv = (ListView)findViewById(R.id.listView1); */
		setListAdapter(aa);

		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {
				GoalDetailedActivity.posDetailed = position;
					Intent changeActivity = new Intent(getActivity(),
							GoalDetailedActivity.class);
					startActivityForResult(changeActivity, 1);
			}
		});

		getListView().setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View view,
					int position, long id) {
				GoalCreationActivity.posDetailed = position;
				Intent changeActivity = new Intent(getActivity(),
							GoalCreationActivity.class);
					startActivityForResult(changeActivity, 1);
				return false;
			}
		});
	}
}
