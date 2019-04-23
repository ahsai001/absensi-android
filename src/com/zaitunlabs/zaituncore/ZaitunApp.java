package com.zaitunlabs.zaituncore;

import com.androidquery.callback.BitmapAjaxCallback;

import android.app.Application;
import android.util.Log;

public class ZaitunApp extends Application{

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onLowMemory() {
		Log.e("LOW_MEMORY", "low memory occured");
		BitmapAjaxCallback.clearCache();
		super.onLowMemory();
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}

}
