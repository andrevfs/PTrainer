package com.example.parametas;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class SettingAdapter extends ArrayAdapter<Setting>{
	
	private final LayoutInflater inflater;
	private final int resourceId;
	
	public SettingAdapter(Context context, int resource, List<Setting> itens){
		super(context, resource, itens);
		this.inflater = LayoutInflater.from(context);
        this.resourceId = resource;
    
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
		
		Setting settingObject = getItem(position);
		
		convertView = inflater.inflate(resourceId, parent, false);
		
		CheckBox cbOption = (CheckBox) convertView.findViewById(R.id.cbOption);
		TextView tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
		
		cbOption.setChecked(settingObject.getOption());
		cbOption.setText(settingObject.getOptionText());
		
		tvDescription.setText(settingObject.getDescription());
		
		return convertView;
	}
}
