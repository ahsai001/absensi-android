package com.zaitunlabs.zaituncore;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import com.androidquery.util.AQUtility;


public class ZaitunActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		Log.w("Activity", this.getClass().getSimpleName()+":onCreate");
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.w("Activity", this.getClass().getSimpleName()+":onStart");
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.w("Activity", this.getClass().getSimpleName()+":onResume");
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.w("Activity", this.getClass().getSimpleName()+":onPause");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.w("Activity", this.getClass().getSimpleName()+":onStop");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(isTaskRoot()){
            AQUtility.cleanCacheAsync(this);
		}

		Log.w("Activity", this.getClass().getSimpleName()+":onDestroy");
	}
	//***********************************************************
	
	//****************event activity******************************
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.w("Activity", this.getClass().getSimpleName()+":onActivityResult");
	}
	//***********************************************************
	
	//****************cycle window and content******************************
	/*@Override
	public void onAttachedToWindow() {
		// TODO Auto-generated method stub
		super.onAttachedToWindow();
		Log.w("Activity", this.getClass().getSimpleName()+":onAttachedToWindow");
	}
	
	@Override
	public void onDetachedFromWindow() {
		// TODO Auto-generated method stub
		super.onDetachedFromWindow();
		Log.w("Activity", this.getClass().getSimpleName()+":onDetachedFromWindow");
	}
	*/
	@Override
	public void onContentChanged() {
		// TODO Auto-generated method stub
		super.onContentChanged();
		Log.w("Activity", this.getClass().getSimpleName()+":onContentChanged");
	}
	//***********************************************************

	//****************key mapping******************************
/*	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Log.w("Activity", this.getClass().getSimpleName()+":onBackPressed");
	}*/
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		Log.w("Activity", this.getClass().getSimpleName()+":onKeyDown");
		return super.onKeyDown(keyCode, event);
	}

/*	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		Log.w("Activity", this.getClass().getSimpleName()+":onKeyLongPress");
		return super.onKeyLongPress(keyCode, event);
	}*/

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		Log.w("Activity", this.getClass().getSimpleName()+":onKeyUp");
		return super.onKeyUp(keyCode, event);
	}
	//***********************************************************

	//****************configuration and save-restore instance**********************
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		Log.w("Activity", this.getClass().getSimpleName()+":onConfigurationChanged");
	}

	@Override
	public Object onRetainNonConfigurationInstance() {
		// TODO Auto-generated method stub
		Log.w("Activity", this.getClass().getSimpleName()+":onRetainNonConfigurationInstance");
		return super.onRetainNonConfigurationInstance();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		Log.w("Activity", this.getClass().getSimpleName()+":onSaveInstanceState");
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		Log.w("Activity", this.getClass().getSimpleName()+":onRestoreInstanceState");
	}
	//***********************************************************

}
