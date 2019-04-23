package com.ahsai001.absensi.activity;

import com.ahsai001.absensi.R;
import com.zaitunlabs.zaituncore.ZaitunSplashActivity;

import android.app.Activity;
import android.os.Bundle;

public class initapp extends ZaitunSplashActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setImageSplash(R.drawable.splash);
		setNextPageClass(login.class);
	}
}