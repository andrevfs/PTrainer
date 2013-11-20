package com.example.parametas;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class GoalsActivity extends ListFragment{
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		String[] goals = {"Meta 1", "Meta 2", "Meta 3"};
		
		ArrayAdapter<String> aa = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1,goals);
		
		/*		ListView lv = (ListView)findViewById(R.id.listView1);*/
		setListAdapter(aa);
	}
}
