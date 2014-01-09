package com.example.parametas;

import java.util.ArrayList;
import Database.DbTraining;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ListFragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class TrainingsActivity extends ListFragment {

	SQLiteDatabase db;
	DbTraining trainings;
	
	@SuppressLint("NewApi")
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		trainings = new DbTraining(getActivity());
		db = trainings.getWritableDatabase();
		
		super.onActivityCreated(savedInstanceState);
		/*setContentView(R.layout.training_list_activity);*/
		
		String[] columns = {DbTraining.TITLE};
		
		Cursor c = db.query(true,DbTraining.TABLE_NAME,columns, null, null, null, null, null, null, null);
		ArrayList<String> arrTrainings = new ArrayList<String>();
		if(c.moveToFirst())
			arrTrainings.add(c.getString(c.getColumnIndex(DbTraining.TITLE)));
		while(c.moveToNext()){
			String title = c.getString(c.getColumnIndex(DbTraining.TITLE));
			if(title != null)
				arrTrainings.add(title);
			else arrTrainings.add(" ");
		}
		c.close();
		
		ArrayAdapter<String> aa = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1,arrTrainings);
		
		setListAdapter(aa);
		
		//TrainingsActivity
		getListView().setOnItemLongClickListener(new OnItemLongClickListener() {
				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View view,
						int position, long id) {
					TrainingCreationActivity.next = 1;
					Intent changeActivity = new Intent(getActivity(),
								TrainingCreationActivity.class);
						startActivityForResult(changeActivity, 1);
					TrainingCreationActivity.posDetailed = position;
					return false;
				}
				
			});

		getListView().setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {
				TrainingDescriptionActivity.posDetailed = position;
				Intent changeActivity = new Intent(getActivity(),
							TrainingDescriptionActivity.class);
					startActivityForResult(changeActivity, 1);
			}
		});
	}

}
