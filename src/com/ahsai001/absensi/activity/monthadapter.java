package com.ahsai001.absensi.activity;

import java.util.ArrayList;

import com.zaitunlabs.zaitunutils.ZaitunUtils;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;



public class monthadapter extends BaseAdapter{
	Context ctx;
	ArrayList<String> ket;
	ArrayList<String> remote;
	ArrayList<String> datetime;
	ArrayList<String> status;
	public monthadapter(Context ctx, ArrayList<String> ket, ArrayList<String> remote, ArrayList<String> datetime, ArrayList<String> status) {
		this.ctx = ctx;
		this.ket = ket;
		this.remote = remote;
		this.datetime = datetime;
		this.status = status;
		Log.e("AHMAD", "count adapter:"+getCount());
	}
	@Override
	public int getCount() {
		return this.remote.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		if(convertView == null){
			convertView = new LinearLayout(ctx);
			((LinearLayout)convertView).setOrientation(LinearLayout.VERTICAL);
			
			TextView remoteView = new TextView(ctx);
			remoteView.setId(0);
			((LinearLayout)convertView).addView(remoteView);

			TextView datetimeView = new TextView(ctx);
			datetimeView.setId(1);
			((LinearLayout)convertView).addView(datetimeView);

			
			TextView statusView = new TextView(ctx);
			statusView.setId(2);
			((LinearLayout)convertView).addView(statusView);

			
			View line = new View(ctx);
			line.setBackgroundColor(Color.GREEN);
			((LinearLayout)convertView).addView(line, ZaitunUtils.getScreenWidth(ctx), 3);
		}
		
		TextView remoteView = (TextView)convertView.findViewById(0);
		remoteView.setText(ket.get(0)+" : "+remote.get(position));
		Log.e("AHMAD", "adapter name ke-"+position+" : "+ket.get(0)+" : "+remote.get(position));
		
		TextView datetimeView = (TextView)convertView.findViewById(1);
		datetimeView.setText(ket.get(1)+" : "+datetime.get(position));
		Log.e("AHMAD", "adapter datetime ke-"+position+" : "+ket.get(1)+" : "+datetime.get(position));

		TextView statusView = (TextView)convertView.findViewById(2);
		statusView.setText(ket.get(2)+" : "+status.get(position));
		Log.e("AHMAD", "adapter status ke-"+position+" : "+ket.get(2)+" : "+status.get(position));
		
		if(status.get(position).contains("IN") && status.get(position).contains("YES") && status.get(position).contains("X")){
			((LinearLayout)convertView).setBackgroundColor(Color.argb(150, 255, 50, 50));
		}else{
			((LinearLayout)convertView).setBackgroundColor(Color.argb(150, 50, 255, 50));
		}
		
		return convertView;
	}
}

