package com.example.parametas;

import java.util.ArrayList;
import java.util.List;
import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class SettingsActivity extends ListFragment{

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		List settingList = creatingSettingList();
		ArrayAdapter ad = new SettingAdapter(getActivity(), R.layout.setting_row, settingList);
		
		setListAdapter(ad);
		
	}
	public List creatingSettingList(){
		List l = new ArrayList();
		l.add(new Setting(false,"Interface Simplificada","A inteface conterá mais telas, porém " +
				"com menos menus e ferramentas de tamanho pequeno."));
		l.add(new Setting(false,"Ativar Alerta","Permitir que alerta seja mostrado sempre que a data de uma meta expirar."));
		
		return l;
	}
	
}
