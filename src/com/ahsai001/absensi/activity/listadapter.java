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



public class listadapter extends BaseAdapter{
	Context ctx;
	ArrayList<String> ket;
	ArrayList<String> name;
	ArrayList<String> datetime;
	ArrayList<String> status;
	ArrayList<String> late;
	public listadapter(Context ctx, ArrayList<String> ket, ArrayList<String> name, ArrayList<String> datetime, ArrayList<String> status, ArrayList<String> late) {
		this.ctx = ctx;
		this.ket = ket;
		this.name = name;
		this.datetime = datetime;
		this.status = status;
		this.late = late;
		Log.e("AHMAD", "count adapter:"+getCount());
	}
	@Override
	public int getCount() {
		return this.name.size();
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
			
			TextView nameView = new TextView(ctx);
			nameView.setId(0);
			((LinearLayout)convertView).addView(nameView);

			TextView datetimeView = new TextView(ctx);
			datetimeView.setId(1);
			((LinearLayout)convertView).addView(datetimeView);

			
			TextView statusView = new TextView(ctx);
			statusView.setId(2);
			((LinearLayout)convertView).addView(statusView);
			
			
			TextView lateView = new TextView(ctx);
			lateView.setId(3);
			((LinearLayout)convertView).addView(lateView);

			
			View line = new View(ctx);
			line.setBackgroundColor(Color.GREEN);
			((LinearLayout)convertView).addView(line, ZaitunUtils.getScreenWidth(ctx), 3);
		}
		
		TextView nameView = (TextView)convertView.findViewById(0);
		nameView.setText(ket.get(0)+" : "+name.get(position));
		Log.e("AHMAD", "adapter name ke-"+position+" : "+ket.get(0)+" : "+name.get(position));
		
		TextView datetimeView = (TextView)convertView.findViewById(1);
		datetimeView.setText(ket.get(1)+" : "+datetime.get(position));
		Log.e("AHMAD", "adapter datetime ke-"+position+" : "+ket.get(1)+" : "+datetime.get(position));

		TextView statusView = (TextView)convertView.findViewById(2);
		statusView.setText(ket.get(2)+" : "+status.get(position));
		Log.e("AHMAD", "adapter status ke-"+position+" : "+ket.get(2)+" : "+status.get(position));
		
		TextView lateView = (TextView)convertView.findViewById(3);
		lateView.setText(ket.get(3)+" : "+late.get(position));
		Log.e("AHMAD", "adapter late ke-"+position+" : "+ket.get(3)+" : "+late.get(position));
		
		if(late.get(position).equals("NO") || late.get(position).equals(" NO ")){
			((LinearLayout)convertView).setBackgroundColor(Color.argb(150, 50, 255, 50));
		}else{
			((LinearLayout)convertView).setBackgroundColor(Color.argb(150, 50, 50, 255));
		}
		return convertView;
	}
}

