package com.example.parametas;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity{
	Bundle savedInstanceState2;
	  ActionBar ab;
	  private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";

	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.capa_activity);
	    
	    savedInstanceState2 = savedInstanceState;
	    new CountDownTimer(2000, 1000) {

	    	   public void onTick(long millisUntilFinished) {
	    	   }

	    	   public void onFinish() {
	    	       setContentView(R.layout.main_layout);
	    	       teste(savedInstanceState2);
	    	   }

	    	}.start();
	    
	  }
	  public void teste(Bundle savedInstanceState){
		    final ActionBar actionBar = getActionBar();
		    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		    // for each of the sections in the app, add a tab to the action bar.
		    actionBar.addTab(actionBar.newTab().setText(R.string.title_section1)
		        .setTabListener(new TabListener<TrainingsActivity>(this,"trainings",TrainingsActivity.class)));
		    actionBar.addTab(actionBar.newTab().setText(R.string.title_section2)
		        .setTabListener(new TabListener<GoalsActivity>(this,"goals",GoalsActivity.class)));
		    actionBar.addTab(actionBar.newTab().setText(R.string.title_section3)
		        .setTabListener(new TabListener<SettingsActivity>(this,"settings",SettingsActivity.class)));
		    
		    if (savedInstanceState != null) {
	            actionBar.setSelectedNavigationItem(savedInstanceState.getInt("tab", 0));
	        }
		    
		    ab = actionBar;
	  }

	  @Override
	  public void onRestoreInstanceState(Bundle savedInstanceState) {
	    // Restore the previously serialized current tab position.
	    if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
	      getActionBar().setSelectedNavigationItem(savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
	    }
	  }

	  @Override
	  public void onSaveInstanceState(Bundle outState) {
	    // Serialize the current tab position.
	    outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar()
	        .getSelectedNavigationIndex());
	  }
	  
	  public boolean onCreateOptionsMenu(Menu menu) {
		    // Inflate the menu items for use in the action bar
		    MenuInflater inflater = getMenuInflater();
		    inflater.inflate(R.menu.actionbar, menu);
		    return super.onCreateOptionsMenu(menu);
		}
	  
	  @Override
	  public boolean onOptionsItemSelected(MenuItem item) {
		    // Handle presses on the action bar items
		    switch (item.getItemId()) {
		        case R.id.plus_sign:
		        	if(ab.getSelectedTab().getPosition() == 0){
		        		TrainingCreationActivity.next = 0;
			        	Intent changeActivity = new Intent(MainActivity.this, TrainingCreationActivity.class);
	          	 		startActivityForResult(changeActivity, 1);
			            return true;
		        	}
		        	if(ab.getSelectedTab().getPosition() == 1){
			        	Intent changeActivity = new Intent(MainActivity.this, GoalCreationActivity.class);
	          	 		startActivityForResult(changeActivity, 1);
			            return true;
		        	}
		    }
			return false;
		}
	  

	
	  public static class TabListener<T> implements ActionBar.TabListener {
	        private final Activity mActivity;
	        private final String mTag;
	        private final Class<T> mClass;
	        private final Bundle mArgs;
	        private Fragment mFragment;

	        public TabListener(Activity activity, String tag, Class<T> clz) {
	            this(activity, tag, clz, null);
	        }

	        public TabListener(Activity activity, String tag, Class<T> clz, Bundle args) {
	            mActivity = activity;
	            mTag = tag;
	            mClass = clz;
	            mArgs = args;

	            // Check to see if we already have a fragment for this tab, probably
	            // from a previously saved state.  If so, deactivate it, because our
	            // initial state is that a tab isn't shown.
	            mFragment = mActivity.getFragmentManager().findFragmentByTag(mTag);
	            if (mFragment != null && !mFragment.isDetached()) {
	                FragmentTransaction ft = mActivity.getFragmentManager().beginTransaction();
	                ft.detach(mFragment);
	                ft.commit();
	            }
	        }

	        public void onTabSelected(Tab tab, FragmentTransaction ft) {
	            if (mFragment == null) {
	                mFragment = Fragment.instantiate(mActivity, mClass.getName(), mArgs);
	                ft.add(android.R.id.content, mFragment, mTag);
	            } else {
	                ft.attach(mFragment);
	            }
	        }

	        public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	            if (mFragment != null) {
	                ft.detach(mFragment);
	            }
	        }

	        public void onTabReselected(Tab tab, FragmentTransaction ft) {
	            Toast.makeText(mActivity, "Reselected!", Toast.LENGTH_SHORT).show();
	        }
	    }
	}
