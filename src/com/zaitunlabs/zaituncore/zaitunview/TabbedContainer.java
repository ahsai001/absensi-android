package com.zaitunlabs.zaituncore.zaitunview;

import java.util.Vector;


import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout.LayoutParams;

public class TabbedContainer extends LinearLayout{
	Vector<View> viewList = new Vector<View>();
	OnItemSelectedListener selectedListener = null;
	int weightSum = 0;
	
	TabbedListener tabListener = new TabbedListener() {
		public void selectedView(View v) {
			int size = viewList.size();
			for (int i = 0; i < size; i++) {
				View otherView = viewList.get(i);
				if(v != otherView){
					((ImageTextView)otherView).setSelectedAndRedraw(false);
				}else{
					if(selectedListener != null)
						selectedListener.onItemSelected(null, v, i, 0);
				}
			}
		}
	};
	
	public void setOnItemSelectedListener(OnItemSelectedListener l) {
		selectedListener = l;
	}

	public TabbedContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public TabbedContainer(Context context) {
		super(context);
		init();
	}
	
	@Override
	public void addView(View child) {
		super.addView(child);
		setupView(child);
	}
	
	@Override
	public void addView(View child, int width, int height) {
		super.addView(child, width, height);
		setupView(child);
	}
	
	@Override
	public void addView(View child, android.view.ViewGroup.LayoutParams params) {
		super.addView(child, params);
		setupView(child);
	}
	
	public void AddTab(int selectedImage, int unSelectedImage, String text){
		ImageTextView tab = new ImageTextView(this.getContext());
		tab.setSelectedImage(selectedImage);
		tab.setUnSelectedImage(unSelectedImage);
		tab.setText(text);
		tab.setPadding(3, 3, 3, 3);
		tab.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
		tab.setTextSize(20);
		tab.setTextColor(Color.BLACK);
		weightSum += 1;
		LayoutParams params = new LinearLayout.LayoutParams(0,LayoutParams.FILL_PARENT);
		params.weight = 1;
		tab.setLayoutParams(params);
		addView(tab);
		setWeightSum(weightSum);
	}
	
	private void init(){
		setOrientation(LinearLayout.HORIZONTAL);
	}
	private void setupView(View v){
		viewList.add(v);
		((ImageTextView)v).tabListener = tabListener;
		if(viewList.size() == 1){
			((ImageTextView)v).setAsSelected();
		}
	}
}
