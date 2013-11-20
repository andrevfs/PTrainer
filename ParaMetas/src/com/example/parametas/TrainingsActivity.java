package com.example.parametas;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListFragment;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TrainingsActivity extends ListFragment {

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		/*setContentView(R.layout.training_list_activity);*/
		
		String[] trainings = {"Treino 1", "Treino 2", "Treino 3"};
		
		ArrayAdapter<String> aa = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1,trainings);
		
/*		ListView lv = (ListView)findViewById(R.id.listView1);*/
		setListAdapter(aa);
		
	}

}
