package com.zaitunlabs.zaituncore.zaitunview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

public class ImageTextView extends TextView {
	int imageSelected = 0;
	int imageUnSelected = 0;
	boolean isSelected = false;
	boolean alreadyOutSideView = false;
	TabbedListener tabListener = null;
	Paint p = null;

	public ImageTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public ImageTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ImageTextView(Context context) {
		super(context);
		init();
	}
	
	private void init(){
		p = new Paint();
	}

	public void setSelectedImage(int resourceImage) {
		imageSelected = resourceImage;
	}

	public void setUnSelectedImage(int resourceImage) {
		imageUnSelected = resourceImage;
	}

	public void setSelectedAndRedraw(boolean isSelected) {
		this.isSelected = isSelected;
		postInvalidate();
	}
	
	public void setAsSelected(){
		this.isSelected = true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		ImageView img = new ImageView(this.getContext());
		img.setScaleType(ScaleType.FIT_XY);
		img.setDrawingCacheEnabled(true);
		img.measure(MeasureSpec.makeMeasureSpec(this.getWidth(),
				MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(this
				.getHeight(), MeasureSpec.EXACTLY));
		img.layout(0, 0, img.getMeasuredWidth(), img.getMeasuredHeight());
		if (isSelected) {
			img.setImageResource(imageSelected);
			canvas.drawBitmap(img.getDrawingCache(), 0, 0, p);
		} else {
			img.setImageResource(imageUnSelected);
			canvas.drawBitmap(img.getDrawingCache(), 0, 0, p);
		}
		img.setDrawingCacheEnabled(false);
		super.onDraw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			if (isSelected == false) {
				isSelected = !isSelected;
				postInvalidate();
				alreadyOutSideView = true;
				if (isSelected) {
					if (tabListener != null)
						tabListener.selectedView(this);
				}
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if ((event.getX() > this.getWidth() || event.getX() < 0)
					|| (event.getY() > this.getHeight() || event.getY() < 0)) {
				/*
				 * if (alreadyOutSideView) { isSelected = !isSelected;
				 * postInvalidate(); alreadyOutSideView = false; if(isSelected){
				 * if(tabListener != null) tabListener.selectedView(this); } }
				 */
			}
			break;
		case MotionEvent.ACTION_UP:
			break;
		}
		return true;
	}
}
