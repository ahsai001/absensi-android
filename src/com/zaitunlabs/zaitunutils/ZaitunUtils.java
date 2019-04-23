package com.zaitunlabs.zaitunutils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

public class ZaitunUtils {

	public static String getPackageName(Context ctx) {
		return ctx.getPackageName();
	}

	public static int getIDResource(Context ctx, String folder, String filename) {
		String fn = null;
		if (filename.contains(".")) {
			fn = filename.substring(0, filename.lastIndexOf('.'));
		} else {
			fn = filename;
		}
		return ctx.getResources().getIdentifier(fn, folder,
				getPackageName(ctx));
	}

	public static int[] getIntArrayResource(Context context, String folder,
			String filename) {
		int[] x = null;
		try {
			Class clazz = Class.forName(getPackageName(context) + ".R$"
					+ folder);
			try {
				String fn = null;
				if (filename.contains(".")) {
					fn = filename.substring(0, filename.lastIndexOf('.'));
				} else {
					fn = filename;
				}
				if (clazz != null)
					x = (int[]) clazz.getDeclaredField(fn).get(null);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return x;
	}

	public static int getIntResource(Context context, String folder,
			String filename) {
		int x = 0;
		try {
			Class clazz = Class.forName(getPackageName(context) + ".R$"
					+ folder);
			try {
				String fn = null;
				if (filename.contains(".")) {
					fn = filename.substring(0, filename.lastIndexOf('.'));
				} else {
					fn = filename;
				}
				if (clazz != null)
					x = (int) clazz.getDeclaredField(fn).getInt(null);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return x;
	}

	public static boolean getBooleanResource(Context context, String folder,
			String filename) {
		boolean x = false;
		try {
			Class clazz = Class.forName(getPackageName(context) + ".R$"
					+ folder);
			try {
				String fn = null;
				if (filename.contains(".")) {
					fn = filename.substring(0, filename.lastIndexOf('.'));
				} else {
					fn = filename;
				}
				if (clazz != null)
					x = (boolean) clazz.getDeclaredField(fn).getBoolean(null);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return x;
	}

	public static AlertDialog showInfo(Context context, String title, String msg) {
		AlertDialog alert = null;
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(msg).setCancelable(false).setNegativeButton("Close",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		alert = builder.create();
		alert.setTitle(title);
		alert.show();
		return alert;
	}

	// get String from intent
	public static String getStringIntent(Intent intent, String name,
			String defaultvalue) {
		String retval = defaultvalue;
		if (intent != null) {
			if (intent.hasExtra(name))
				retval = intent.getStringExtra(name);
		}
		return retval;
	}

	// get Array String from intent
	public static String[] getArrayStringIntent(Intent intent, String name) {
		String[] retval = null;
		if (intent != null) {
			if (intent.hasExtra(name))
				retval = intent.getStringArrayExtra(name);
		}
		return retval;
	}

	// get int from intent
	public static int getIntIntent(Intent intent, String name, int defaultvalue) {
		int retval = defaultvalue;
		if (intent != null) {
			if (intent.hasExtra(name))
				retval = intent.getIntExtra(name, 0);
		}
		return retval;
	}

	// get int from intent
	public static Bundle getBundleIntent(Intent intent, String name) {
		Bundle retval = null;
		if (intent != null) {
			if (intent.hasExtra(name))
				retval = intent.getBundleExtra(name);
		}
		return retval;
	}

	public static int getScreenWidth(Context context) {
		Display display = ((Activity) context).getWindowManager()
				.getDefaultDisplay();
		return display.getWidth();
	}

	public static int getScreenHeight(Context context) {
		Display display = ((Activity) context).getWindowManager()
				.getDefaultDisplay();
		return display.getHeight();
	}

	public static int getAppHeight(Context context) {
		return (getScreenHeight(context) - getStatusBarHeight(context));
	}

	public static int getStatusBarHeight(Context ctx) {
		int result = 0;
		int resourceId = ctx.getResources().getIdentifier("status_bar_height",
				"dimen", "android");
		if (resourceId > 0) {
			result = ctx.getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}
	
	public static boolean isActivityFullScreen(Context ctx){
		boolean isfullScreen = (((Activity)ctx).getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) != 0;
		return isfullScreen;
	}

	public static float getFontHeight(String text) {
		/*
		 * Paint tp = new Paint(); Rect bounds = new Rect(); //this will just
		 * retrieve the bounding rect for text tp.getTextBounds(text, 0,
		 * text.length(), bounds); int textHeight = bounds.height();
		 * Paint.FontMetrics metrics = tp.getFontMetrics(); int totalHeight =
		 * (int) (metrics.top - metrics.bottom);
		 */
		return 40;
	}

	public static float getTextLength(String text, float fontsize, Typeface tf) {
		Paint p = new Paint();
		p.setTextSize(fontsize);
		p.setTypeface(tf);
		return p.measureText(text);
	}

	public static int getHeightSPercent(Context ctx, float percent) {
		float pixel = 0;
		int screenheight = getScreenHeight(ctx);
		pixel = (screenheight * percent) / 100;
		return (int) pixel;
	}

	public static int getWidthSPercent(Context ctx, float percent) {
		float pixel = 0;
		int screenwidth = getScreenWidth(ctx);
		pixel = (screenwidth * percent) / 100;
		return (int) pixel;
	}

	public static int getHeightPercent(Context ctx, int height, float percent) {
		float pixel = 0;
		pixel = (height * percent) / 100;
		return (int) pixel;
	}

	public static int getWidthPercent(Context ctx, int width, float percent) {
		float pixel = 0;
		pixel = (width * percent) / 100;
		return (int) pixel;
	}
	
	public static int getDipFromPixel(Context context, int sizeInPixel){
	    float scale = context.getResources().getDisplayMetrics().density;
	    return (int) (sizeInPixel * scale + 0.5f);
	}
	
	public static int getPixelFromDip(Context context, float sizeInDip){
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sizeInDip,context.getResources().getDisplayMetrics());
	}
	
	public static double getWidthRatio(Context context){
		return (double)(getScreenWidth(context) / 100);
	}
	
	public static double getHeightRatio(Context context){
		boolean isFullScreen = isActivityFullScreen(context);
		return (double)((getScreenHeight(context) - (isFullScreen ? 0 : getStatusBarHeight(context)))/ 100);
	}
	
	public static int getPercentWidthFromPixel(Context context, float pixelWidth){
		double widthRatio = getWidthRatio(context);
		return (int)(pixelWidth/widthRatio);
	}
	
	public static int getPercentHeightFromPixel(Context context, float pixelHeight){
		double heightRatio = getHeightRatio(context);
		return (int)(pixelHeight/heightRatio);
	}
}
