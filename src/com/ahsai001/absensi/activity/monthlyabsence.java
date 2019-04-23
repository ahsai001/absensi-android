package com.ahsai001.absensi.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.zaitunlabs.zaituncore.ZaitunActivity;
import com.zaitunlabs.zaituncore.zaitunview.CanvasLayout;
import com.zaitunlabs.zaitunutils.ZaitunUtils;

public class monthlyabsence extends ZaitunActivity{
	ListView list;
	AQuery aq = null;
	private int year;
	private int month;
	private int day;
	TextView monthString;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		CanvasLayout canvas = new CanvasLayout(this);
		setContentView(canvas.getFillParentView());

		Button setMonth = new Button(this);
		setMonth.setText("Choose Month and year");
		canvas.addViewWithFrame(setMonth, 5, 5, 50, 20);

		monthString = new TextView(this);
		monthString.setText("");

		canvas.addViewWithFrame(monthString, 60, 5, 35, 10);

		setMonth.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				showDialog(999);
			}
		});
		list = new ListView(this);
		canvas.addViewWithFrame(list, 5, 20, 90, 75);

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 999:
			final Calendar c = Calendar.getInstance();
			year = c.get(Calendar.YEAR);
			month = c.get(Calendar.MONTH);
			day = c.get(Calendar.DAY_OF_MONTH);
			return new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker arg0, int selectedYear, int selectedMonth, int selectedDay) {
					monthString.setText(""+selectedDay+"-"+(selectedMonth+1)+"-"+selectedYear);
					String month = String.valueOf(selectedMonth+1);
					String year = String.valueOf(selectedYear);

					if(month.length() == 1){
						month = "0"+month;
					}
					if(aq == null)
						aq = new AQuery(monthlyabsence.this);
					
					ProgressDialog dialog = new ProgressDialog(monthlyabsence.this);
					dialog.setIndeterminate(true);
					dialog.setCancelable(true);
					dialog.setInverseBackgroundForced(false);
					dialog.setCanceledOnTouchOutside(true);
					dialog.setMessage("Getting list...");

					Map<String, String> params = new HashMap<String, String>();
					params.put("cookies", login.cookies);
					params.put("tahun", year);
					params.put("bulan", month);

					AjaxCallback<JSONObject> cb = new AjaxCallback<JSONObject>();
					//cb.setTimeout(15000);
					cb.url("http://202.61.124.20:8270/tes/absen/api/list.php")
					.type(JSONObject.class)
					.weakHandler(monthlyabsence.this,
							"monthlyCallback")
							.params(params);
					aq.progress(dialog).ajax(cb);
				}
			}, year, month,day);
		}
		return null;
	}
	public void monthlyCallback(String url, JSONObject json, AjaxStatus connstatus) {
		if (connstatus.getCode() < 200 || connstatus.getCode() > 299) {
			ZaitunUtils.showInfo(monthlyabsence.this, "Sorry!!!", "Conection Failed");
		} else {
			if (json != null) {
				try {
					JSONObject data = json.getJSONObject("data");
					JSONArray fields = data.getJSONArray("field");

					JSONArray items = data.getJSONArray("item");

					ArrayList<String> ket = new ArrayList<String>();
					ket.add(fields.getString(0));
					ket.add(fields.getString(1));
					ket.add(fields.getString(2));

					ArrayList<String> remote = new ArrayList<String>();
					ArrayList<String> datetime = new ArrayList<String>();
					ArrayList<String> status = new ArrayList<String>();
					for(int i = 0; i < items.length(); i++){
						JSONArray item = items.getJSONArray(i);
						remote.add(item.getString(0));
						datetime.add(item.getString(1));
						status.add(item.getString(2));
					}
					monthadapter adapter = new monthadapter(monthlyabsence.this,ket,remote,datetime,status);
					list.setAdapter(adapter);
				} catch (JSONException e) {
					ZaitunUtils.showInfo(monthlyabsence.this, "Sorry!!!", "Error Occured");
					e.printStackTrace();
				}
			} else {
				ZaitunUtils.showInfo(monthlyabsence.this, "Sorry!!!", "Response Error");
			}
		}
	}
}
