package com.example.parametas;

import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;

public class GoalListAdapter extends ArrayAdapter<Goal> implements NumberPicker.OnValueChangeListener{

	private final LayoutInflater inflater;
	private final int resourceId;
		
	public GoalListAdapter(Context context, int resource, List<Goal> itens){
		super(context, resource, itens);
		this.inflater = LayoutInflater.from(context);
        this.resourceId = resource;
    
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
		
		Goal goalObject = getItem(position);
		
		convertView = inflater.inflate(resourceId, parent, false);
		
		int type = getItemViewType(position);
		
/*		if(type == 0){*/
			TextView completeDescription = (TextView) 
					convertView.findViewById(R.id.tvGoalDescription);       
			SeekBar goalProgress = (SeekBar) convertView.findViewById(R.id.sbGoal);
			ImageButton editGoal = (ImageButton) convertView.findViewById(R.id.ibUpdateGoal);
			TextView goalProgressHint = (TextView) convertView.
					findViewById(R.id.tvSbHint);
			
			completeDescription.setText(goalObject.getName()+" "+
					goalObject.getDescription()+" "+goalObject.getStartDate()+" - "+
					goalObject.getGoalDate());
	        
			goalProgress.setMax(goalObject.getGoalNum());
	        goalProgress.setProgress(goalObject.getGoalState());
	        
	        goalProgressHint.setText(goalObject.getGoalState()+"/"+goalObject.getGoalNum());
	        
	        
	        editGoal.setTag(R.string.uid1,goalProgressHint);
	        editGoal.setTag(R.string.uid2,goalProgress);
			editGoal.setOnClickListener(onClickListener);
	        
			goalProgress.setTag(goalProgressHint);
			goalProgress.setOnSeekBarChangeListener(onSeekBarChangeListener);
/*		}
		
		if(type == 1){
			TextView completeDescription = (TextView) 
					convertView.findViewById(R.id.tvGoalDescription);       
			SeekBar goalProgress = (SeekBar) convertView.findViewById(R.id.sbGoal);
			ImageButton editGoal = (ImageButton) convertView.findViewById(R.id.ibUpdateGoal);
			TextView goalProgressHint = (TextView) convertView.
					findViewById(R.id.tvSbHint);
			
			completeDescription.setVisibility(View.INVISIBLE);
			goalProgress.setVisibility(View.INVISIBLE);
			editGoal.setVisibility(View.INVISIBLE);
			goalProgressHint.setText("teste");
		}
		*/
		
        return convertView;
	}
	
	private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = 
			new SeekBar.OnSeekBarChangeListener() {
		
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			TextView tvAux = (TextView)seekBar.getTag();
			String parser = (String)tvAux.getText();
			String itens[] = parser.split("/");
			tvAux.setText(progress+"/"+itens[1]);
			
		}
	};
	
	private View.OnClickListener onClickListener = new View.OnClickListener() {		
    	
		@Override
		public void onClick(View v) {
			final AlertDialog.Builder d = new AlertDialog.Builder(getContext());
			d.setTitle("Selecione um número:");
			
			View view = LayoutInflater.from(getContext()).inflate(R.layout.picker_number, 
					null);
			
			final TextView tvAux = (TextView)v.getTag(R.string.uid1);
			String parser = (String)tvAux.getText();
			final String itens[] = parser.split("/");
			final SeekBar sbAux = (SeekBar)v.getTag(R.string.uid2);
					
			final NumberPicker np = (NumberPicker) view.findViewById(R.id.numberPicker1);
			 
			np.setMaxValue(Integer.parseInt(itens[1]));
			np.setValue(Integer.parseInt(itens[0]));
			np.setMinValue(0);
			np.setWrapSelectorWheel(false);
			
		    d.setView(view)
		    .setPositiveButton(R.string.escolher, new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	                   tvAux.setText(np.getValue()+"/"+itens[1]);
	                   sbAux.setProgress(np.getValue());
	               }
	        })
	        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int id) {
                   /*LoginDialogFragment.this.getDialog().cancel();*/
               }
	        });
					
	        
			d.show();
		}
		
	};
	
	@Override
	public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int getViewTypeCount() {
	   return 2; //return 2, you have two types that the getView() method will return, normal(0) and for the last row(1)
	}

	@Override
	public int getItemViewType(int position) {
	    return (position == this.getCount() - 1) ? 1 : 0; //if we are at the last position then return 1, for any other position return 0
	}
	
}
