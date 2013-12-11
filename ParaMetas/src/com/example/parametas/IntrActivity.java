package com.example.parametas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class IntrActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intr);
		View sv = findViewById(R.id.imageView1);
		View root = sv.getRootView();
		root.setBackgroundColor(0x00AEEF);
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.intr, menu);
//		return true;
//	}

}
