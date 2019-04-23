package com.ahsai001.absensi.activity;

import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zaitunlabs.zaituncore.ZaitunActivity;
import com.zaitunlabs.zaituncore.zaitunview.CanvasLayout;

public class homepage extends ZaitunActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		CanvasLayout canvas = new CanvasLayout(this);
		setContentView(canvas.getFillParentView());
		
		
		TextView successMessage = new TextView(this);
		successMessage.setText("Welcome "+login.userName);
		
		Button todayList = new Button(this);
		todayList.setText("today absensi list");
		
		Button youAbsence = new Button(this);
		youAbsence.setText("monthly absences");
		
		canvas.addViewWithFrame(successMessage, 5, 10, 90, 10);
		canvas.addViewWithFrame(todayList, 1, 30, 48, 20);
		canvas.addViewWithFrame(youAbsence, 51, 30, 48, 20);
		
		todayList.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(homepage.this,todaylist.class));
			}
		});
		youAbsence.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(homepage.this,monthlyabsence.class));
			}
		});
		
	}
}
