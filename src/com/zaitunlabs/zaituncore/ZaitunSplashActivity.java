package com.zaitunlabs.zaituncore;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class ZaitunSplashActivity extends ZaitunActivity {
	CountDownTimer timer;
	RelativeLayout rellay;
	TextView tv;

	// setting timer splash, next page class, code to run before start next activity
	int milisInFuture = Constant.splashtime;
	int milisInterval = Constant.splashtime;
	Class nextPageClass = null;
	Runnable codeToRunAfterSplashExpired = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		rellay = new RelativeLayout(this);
		rellay.setBackgroundColor(Color.GRAY);
		rellay.setLayoutParams(new RelativeLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		tv = new TextView(this);
		tv.setText("This is splash screen");
		tv.setTextColor(Color.BLACK);
		RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		tv.setLayoutParams(param);
		rellay.addView(tv);
		setContentView(rellay);
	}

	@Override
    protected void onResume() {
    	timer = new CountDownTimer(milisInFuture,milisInterval){
			@Override
			public void onFinish() {
				if(codeToRunAfterSplashExpired != null){
					try {
						codeToRunAfterSplashExpired.run();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				try {
					if(nextPageClass != null){
						Intent nextPageIntent = new Intent(ZaitunSplashActivity.this, nextPageClass);
						ZaitunSplashActivity.this.startActivity(nextPageIntent);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				finish();
			}

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}
        };
        timer.start();
    	super.onResume();
    }

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		timer.cancel();
		super.onPause();
	}

	// Zaitun Splash API methods
	protected void setImageSplash(int resid) {
		tv.setVisibility(View.GONE);
		rellay.setBackgroundResource(resid);
	}

	protected void setRunnableCodeAfterSplashExpired(Runnable codeToRun) {
		codeToRunAfterSplashExpired = codeToRun;
	}
	
	protected void setNextPageClass(Class nextPageClass) {
		this.nextPageClass = nextPageClass;
	}
	
	protected void setMilisInFuture(int milisInFuture) {
		this.milisInFuture = milisInFuture;
	}
	
	protected void setMilisInterval(int milisInterval) {
		this.milisInterval = milisInterval;
	}
}