package com.example.parametas;

import com.example.parametas.HSVColorPickerDialog.OnColorSelectedListener;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import com.example.parametas.DatePickerFragment;

public class GoalCreationActivity extends FragmentActivity implements NumberPicker.OnValueChangeListener{
	private int sColor = 0xFF4488CC;
	private String unit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goal_creation);

   }
	
	public void showDatePickerDialog(View v) {
	    DatePickerFragment newFragment = new DatePickerFragment();
	    String textInit = "De:";
	    if(v.getId() == R.id.button2)
	    	textInit = "Até:";
	    newFragment.setId(v.getId());
	    newFragment.setInitText(textInit);
	    newFragment.show(getFragmentManager(), "datePicker");
	}
	
	/** Called when the user clicks the Send button */
	public void createGoal(View view) {
	    // create the goal at database
		finish();
	}
	
	public void delete(View v){
		
	}
	
	 public void showNumberDialog(View v){
		 	 final Dialog d = new Dialog(GoalCreationActivity.this);
	         d.setTitle("Selecione um número:");
	         d.setContentView(R.layout.picker_number);
	         final Button b1 = (Button) d.findViewById(R.id.btnCancel);
	         final Button b2 = (Button) d.findViewById(R.id.btnOk);
	         final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
	         np.setMaxValue(100);
	         np.setMinValue(0);
	         np.setWrapSelectorWheel(false);
	         np.setOnValueChangedListener(this);
	         b1.setOnClickListener(new OnClickListener()
	         {
	          @Override
	          public void onClick(View v) {
	              d.dismiss();
	           }    
	          });
	         b2.setOnClickListener(new OnClickListener()
	         {
	          @Override
	          public void onClick(View v) {
	              d.dismiss(); 
	              final Button testButton = (Button) findViewById(R.id.button4);
	 			  testButton.setText("Meta: "+String.valueOf(np.getValue()));
	           }    
	          });
	       d.show();
	    }

	@Override
	public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
		// TODO Auto-generated method stub
		
	}
	
	public void colorPicker(View v){
		HSVColorPickerDialog cpd = new HSVColorPickerDialog(GoalCreationActivity.this, sColor, new OnColorSelectedListener() {
		    @Override
		    public void colorSelected(Integer color) {
		    	sColor = color;
		        ImageView v = (ImageView) findViewById(R.id.imageView1);
		        v.setColorFilter(color);
		    }
		});
		cpd.setTitle( "Selecione uma cor:" );
		cpd.show();
	}
	public void clearUnit(){
		unit = "";
	}
	public void createUnit(View v){
		clearUnit();
		Context context = this;
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);
		alertDialogBuilder.setTitle("Escolha a unidade:");
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("Metros",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								unit = "Metros";
								final Button testButton = (Button) findViewById(R.id.button2);
								testButton.setText("Unidade: "+unit);
							}
						})
				.setNegativeButton("Horas",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								unit = "Horas";
								final Button testButton = (Button) findViewById(R.id.button2);
								testButton.setText("Unidade: Horas");
							}
						});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
	
}