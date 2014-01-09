package com.example.parametas;

import com.example.parametas.HSVColorPickerDialog.OnColorSelectedListener;

import Database.DbGoals;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parametas.DatePickerFragment;

@SuppressLint("NewApi")
public class GoalCreationActivity extends FragmentActivity implements NumberPicker.OnValueChangeListener{
	
	protected static int posDetailed = -1;
	static public String end_time;
	static public String start_time;
	private Integer reached = 0;
	private String milestone;
	private String description;
	private String title;
	
	private int sColor = 0xFF4488CC;
	private String unit;
	
	SQLiteDatabase db;
	DbGoals goals;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//getting db
		goals = new DbGoals(this);
		db = goals.getWritableDatabase();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goal_creation);
		Button btGoal = (Button)findViewById(R.id.button4);
		btGoal.setOnClickListener(onClickListener);
		
		
		final Button confirm = (Button) findViewById(R.id.button3);
		if(posDetailed != -1){
			String[] columns = {DbGoals.TITLE, DbGoals.MILESTONE,
					DbGoals.DESCRIPTION, DbGoals.START_TIME, DbGoals.END_TIME,
					DbGoals.UNIT, DbGoals.REACHED, DbGoals.COLOR};
	
			Cursor c = db.query(true, DbGoals.TABLE_NAME, columns, null, null,
					null, null, null, null, null);
			c.moveToPosition(posDetailed);
			
			TextView textView = (TextView) findViewById(R.id.editText1);
			textView.setText(c.getString(c.getColumnIndex(DbGoals.TITLE)));
			textView = (TextView) findViewById(R.id.editText2);
			textView.setText(c.getString(c.getColumnIndex(DbGoals.DESCRIPTION)));
			
			
			Button b = (Button) findViewById(R.id.button1);
			start_time = c.getString(c.getColumnIndex(DbGoals.START_TIME));
			b.setText("De: "+start_time);
			
			b = (Button) findViewById(R.id.button5);
			end_time = c.getString(c.getColumnIndex(DbGoals.END_TIME));
			b.setText("Até: "+end_time);
			
			b = (Button) findViewById(R.id.button4);
			milestone = c.getString(c.getColumnIndex(DbGoals.MILESTONE));
			b.setText("Meta: "+milestone);
			
			b = (Button) findViewById(R.id.button2);
			unit = c.getString(c.getColumnIndex(DbGoals.UNIT));
			b.setText("Unidade: "+unit);
			
			sColor = c.getInt(c.getColumnIndex(DbGoals.COLOR));
			ImageView v = (ImageView) findViewById(R.id.imageView1);
			v.setColorFilter(sColor);

			reached = new Integer(c.getString(c.getColumnIndex(DbGoals.REACHED)));
			confirm.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					 EditText t = (EditText) findViewById(R.id.editText1);
					 EditText desc = (EditText) findViewById(R.id.editText2);
					 if(t!= null)
						 title = t.getText().toString();
					 if(desc!= null)
						 description = desc.getText().toString();
					
				    // create the goal at database
					ContentValues cv = new ContentValues();
					cv.put(DbGoals.TITLE, title);
					cv.put(DbGoals.DESCRIPTION, description);
					cv.put(DbGoals.MILESTONE, milestone);
					cv.put(DbGoals.UNIT, unit);
					cv.put(DbGoals.START_TIME, start_time);
					cv.put(DbGoals.END_TIME, end_time);
					cv.put(DbGoals.COLOR, Integer.toString(sColor));
					cv.put(DbGoals.REACHED, reached.toString());
					
					Cursor c = db.query(true, DbGoals.TABLE_NAME, DbGoals.allColumns, null, null, null, null, null, null, null);
					c.moveToPosition(posDetailed);
					db.update(DbGoals.TABLE_NAME, cv,DbGoals.C_ID+" = ?", new String[] {c.getString(c.getColumnIndex(DbGoals.C_ID))});
					posDetailed = -1;
					c.close();
					db.close();
					finish();
				}
			});
		}else{
			confirm.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					 EditText t = (EditText) findViewById(R.id.editText1);
					 EditText desc = (EditText) findViewById(R.id.editText2);
					 if(t!= null)
						 title = t.getText().toString();
					 if(desc!= null)
						 description = desc.getText().toString();
					
				    // create the goal at database
					ContentValues cv = new ContentValues();
					cv.put(DbGoals.TITLE, title);
					cv.put(DbGoals.DESCRIPTION, description);
					cv.put(DbGoals.MILESTONE, milestone);
					cv.put(DbGoals.UNIT, unit);
					cv.put(DbGoals.START_TIME, start_time);
					cv.put(DbGoals.END_TIME, end_time);
					cv.put(DbGoals.COLOR, Integer.toString(sColor));
					cv.put(DbGoals.REACHED, reached.toString());
					
					db.insert(DbGoals.TABLE_NAME, null, cv);
					finish();
				}
			});
		}
   }
	
	public void showDatePickerDialog(View v) {
	    DatePickerFragment newFragment = new DatePickerFragment();
	    String textInit = "De:";
	    if(v.getId() == R.id.button5)
	    	textInit = "Até:";
	    newFragment.setId(v.getId());
	    newFragment.setInitText(textInit);
	    newFragment.show(getFragmentManager(), "datePicker");
	}
	
	public void delete(View v){
		
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
	 private View.OnClickListener onClickListener = new View.OnClickListener() {		
	    	
			@Override
			public void onClick(View v) {
				final AlertDialog.Builder d = new AlertDialog.Builder(GoalCreationActivity.this);
				d.setTitle("Informe um número:");
				
				View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.edit_text_dialog, 
						null);
						
				final EditText etMeta = (EditText) view.findViewById(R.id.etMeta);
				
			    d.setView(view)
			    .setPositiveButton(R.string.escolher, new DialogInterface.OnClickListener() {
		               @Override
		               public void onClick(DialogInterface dialog, int id) {
		                   milestone = etMeta.getText().toString(); 
		                   dialog.dismiss();
		                   Button b = (Button) findViewById(R.id.button4);
		                   b.setText("Meta: "+milestone);
		                   
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
}