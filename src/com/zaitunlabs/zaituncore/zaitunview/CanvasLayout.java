package com.zaitunlabs.zaituncore.zaitunview;

import com.zaitunlabs.zaitunutils.ZaitunUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

public class CanvasLayout extends RelativeLayout{
	double widthRatio;
	double heightRatio;
	public CanvasLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	public CanvasLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	public CanvasLayout(Context context) {
		super(context);
		init(context);
	}
	
	public View getFillParentView(){
		this.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		return this;
	}
	
	private void init(Context context){
		boolean isFullScreen = ZaitunUtils.isActivityFullScreen(context);
		widthRatio = (double)ZaitunUtils.getScreenWidth(context) / 100;
		heightRatio = (double)(ZaitunUtils.getScreenHeight(context) - (isFullScreen ? 0 : ZaitunUtils.getStatusBarHeight(context)))/ 100;
	}
	
	public void addViewWithFrame(View v,int left, int top, int width, int height){
		int viewWidth = (int) (widthRatio * width);
		int viewHeight = (int) (heightRatio * height);
		int leftX = (int) (widthRatio * left);
		int topY = (int) (heightRatio * top);
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(viewWidth, viewHeight);
		params.leftMargin = leftX;
		params.topMargin = topY;
		addView(v, params);
	}

}
