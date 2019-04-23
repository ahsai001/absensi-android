package com.ahsai001.absensi.activity;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.zaitunlabs.zaituncore.ZaitunActivity;
import com.zaitunlabs.zaituncore.zaitunview.CanvasLayout;
import com.zaitunlabs.zaitunutils.ZaitunUtils;

public class login extends ZaitunActivity{
	private AQuery aq = null;
	static public String cookies  = null; 
	static public String userName = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		CanvasLayout canvas = new CanvasLayout(this);
		canvas.setBackgroundColor(Color.GREEN);
		setContentView(canvas.getFillParentView());

		final EditText username = new EditText(this);
		username.setHint("firstname.lastname");

		final EditText password = new EditText(this);
		password.setHint("password");
		password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

		Button login = new Button(this);
		login.setText("Login");
		
		CheckedTextView rememberme = new CheckedTextView(this);
		rememberme.setText("Remember me");
		rememberme.setChecked(false);
		rememberme.setCheckMarkDrawable(android.R.drawable.checkbox_off_background);
		rememberme.setClickable(true);
		rememberme.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				CheckedTextView view = (CheckedTextView)arg0;
				if(!view.isChecked()){
					view.setChecked(true);
					view.setCheckMarkDrawable(android.R.drawable.checkbox_on_background);
					//save username password
					SharedPreferences prefs = login.this.getSharedPreferences("Absensijatis", Context.MODE_PRIVATE);
					Editor editor = prefs.edit();
					editor.putString("username", username.getEditableText().toString());
					editor.putString("password", password.getEditableText().toString());
					editor.commit();
				}else{
					view.setChecked(false);
					view.setCheckMarkDrawable(android.R.drawable.checkbox_off_background);
					SharedPreferences prefs = login.this.getSharedPreferences("Absensijatis", Context.MODE_PRIVATE);
					Editor editor = prefs.edit();
					editor.remove("username");
					editor.remove("password");
					editor.commit();
				}
			}
		});

		canvas.addViewWithFrame(username, 5, 10, 80, 10);
		canvas.addViewWithFrame(password, 5, 30, 80, 10);
		canvas.addViewWithFrame(login, 5, 50, 40, 10);
		canvas.addViewWithFrame(rememberme, 5, 65, 40, 10);
		
		SharedPreferences prefs = login.this.getSharedPreferences("Absensijatis", Context.MODE_PRIVATE);
		if(prefs.contains("username")){
			rememberme.setChecked(true);
			rememberme.setCheckMarkDrawable(android.R.drawable.checkbox_on_background);
			username.setText(prefs.getString("username", ""));
		}
		if(prefs.contains("password")){
			rememberme.setChecked(true);
			rememberme.setCheckMarkDrawable(android.R.drawable.checkbox_on_background);
			password.setText(prefs.getString("password", ""));
		}

		login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String user = username.getText().toString();
				String pass = password.getText().toString();
				if(user != null && pass != null){
					userName = user;
					aq = new AQuery(login.this);
					ProgressDialog dialog = new ProgressDialog(login.this);
					dialog.setIndeterminate(true);
					dialog.setCancelable(true);
					dialog.setInverseBackgroundForced(false);
					dialog.setCanceledOnTouchOutside(true);
					dialog.setMessage("Logging in...");

					Map<String, String> params = new HashMap<String, String>();
					params.put("username", user);
					params.put("password", pass);

					AjaxCallback<JSONObject> cb = new AjaxCallback<JSONObject>();
					cb.setTimeout(60000);
					cb.url("http://202.61.124.20:8270/tes/absen/api/login.php")
							.type(JSONObject.class)
							.weakHandler(login.this,"loginCallback")
							.params(params);
					//AjaxCallback.setAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0");
					aq.progress(dialog).ajax(cb);
				}else{
					ZaitunUtils.showInfo(login.this, "Need Credentials", "Please fill username and password correctly");
				}
			}
		});
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		cookies = null;
		userName = null;
	}
	
	public void loginCallback(String url, JSONObject json, AjaxStatus connstatus) {
		if (connstatus.getCode() < 200 || connstatus.getCode() > 299) {
			ZaitunUtils.showInfo(login.this, "Sorry!!!", "Conection Failed");
		} else {
			if (json != null) {
				int status = -1;
				try {
					status = json.getInt("status");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				if(status == 1){
					try {
						cookies = json.getString("cookies");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					//goto next page
					login.this.startActivity(new Intent(login.this,homepage.class));
				}else{
					try {
						ZaitunUtils.showInfo(login.this, "Credentials Error", ""+json.getString("desc"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			} else {
				ZaitunUtils.showInfo(login.this, "Sorry!!!", "Response Error");
			}
		}
	}
}
