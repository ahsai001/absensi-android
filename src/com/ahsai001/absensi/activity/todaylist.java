package com.ahsai001.absensi.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.array;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.zaitunlabs.zaituncore.ZaitunActivity;
import com.zaitunlabs.zaituncore.zaitunview.CanvasLayout;
import com.zaitunlabs.zaitunutils.ZaitunUtils;

public class todaylist extends ZaitunActivity{
	ListView list;
	AQuery aq = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		CanvasLayout canvas = new CanvasLayout(this);
		setContentView(canvas.getFillParentView());

		list = new ListView(this);

		canvas.addViewWithFrame(list, 5, 5, 90, 90);

		aq = new AQuery(todaylist.this);
		ProgressDialog dialog = new ProgressDialog(todaylist.this);
		dialog.setIndeterminate(true);
		dialog.setCancelable(true);
		dialog.setInverseBackgroundForced(false);
		dialog.setCanceledOnTouchOutside(true);
		dialog.setMessage("Getting list...");

		Map<String, String> params = new HashMap<String, String>();
		params.put("cookies", login.cookies);

		AjaxCallback<JSONObject> cb = new AjaxCallback<JSONObject>();
		//cb.setTimeout(15000);
		cb.url("http://202.61.124.20:8270/tes/absen/api/absen.php")
				.type(JSONObject.class)
				.weakHandler(todaylist.this,
						"listCallback")
				.params(params);
		aq.progress(dialog).ajax(cb);
	}
	public void listCallback(String url, JSONObject json, AjaxStatus connstatus) {
		if (connstatus.getCode() < 200 || connstatus.getCode() > 299) {
			ZaitunUtils.showInfo(todaylist.this, "Sorry!!!", "Conection Failed");
		} else {
			if (json != null) {
				try {
					JSONObject data = json.getJSONObject("data");
					JSONArray fields = data.getJSONArray("field");
					
					JSONArray items = data.getJSONArray("item");
					
					ArrayList<String> ket = new ArrayList<String>();
					ket.add(fields.getString(1));
					ket.add(fields.getString(2));
					ket.add(fields.getString(3));
					ket.add(fields.getString(4));
					
					ArrayList<String> name = new ArrayList<String>();
					ArrayList<String> datetime = new ArrayList<String>();
					ArrayList<String> status = new ArrayList<String>();
					ArrayList<String> late = new ArrayList<String>();
					for(int i = 0; i < items.length(); i++){
						JSONArray item = items.getJSONArray(i);
						name.add(item.getString(1));
						datetime.add(item.getString(2));
						status.add(item.getString(3));
						late.add(item.getString(4));
					}					
					
					listadapter adapter = new listadapter(todaylist.this,ket,name,datetime,status,late);
					list.setAdapter(adapter);
				} catch (JSONException e) {

					ZaitunUtils.showInfo(todaylist.this, "Sorry!!!", "Error Occured");
					e.printStackTrace();
				}
			} else {
				ZaitunUtils.showInfo(todaylist.this, "Sorry!!!", "Response Error");
			}
		}
	}
}
