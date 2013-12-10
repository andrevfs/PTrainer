package com.example.parametas;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class TrainingsActivity extends ListFragment {

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		/*setContentView(R.layout.training_list_activity);*/
		
		String[] trainings = {"Academia", "Treino Intenso Judo", "Corridas"};
		
		ArrayAdapter<String> aa = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1,trainings);
		
		setListAdapter(aa);
		//TrainingsActivity
		getListView().setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View view,
						int position, long id) {
					TrainingCreationActivity.next = 1;
					Intent changeActivity;
					switch (position) {
					case 0:
						changeActivity = new Intent(getActivity(),
								TrainingCreationActivity.class);
						startActivityForResult(changeActivity, 1);
						break;
					case 1:
						changeActivity = new Intent(getActivity(),
								TrainingCreationActivity.class);
						startActivityForResult(changeActivity, 1);
						break;
					case 2:
						changeActivity = new Intent(getActivity(),
								TrainingCreationActivity.class);
						startActivityForResult(changeActivity, 1);
						break;
					}

					return false;
				}
				
			});
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {
				Intent changeActivity;
				switch (position) {
				case 0:
					changeActivity = new Intent(getActivity(),
							TrainingDescriptionActivity.class);
					startActivityForResult(changeActivity, 1);
					break;
				case 1:
					changeActivity = new Intent(getActivity(),
							TrainingDescriptionActivity.class);
					startActivityForResult(changeActivity, 1);
					break;
				case 2:
					changeActivity = new Intent(getActivity(),
							TrainingDescriptionActivity.class);
					startActivityForResult(changeActivity, 1);
					break;
				}

			}

		});
	}

}
