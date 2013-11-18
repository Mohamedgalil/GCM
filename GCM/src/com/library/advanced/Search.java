package com.library.advanced;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.library.BaseActivity;
import com.start.Common;
import com.start.R;
import com.start.SendData;
import com.start.WakeLocker;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class Search extends BaseActivity implements OnClickListener,
		PresenterInterface {
	EditText searchname;
	AlertDialog.Builder alertDialogBuilder;
	Button search_btn, add;
	TextView name;
	private RadioGroup radioGroup1;
	private RadioButton radio0;
	private RadioButton radio1;
	private RadioButton radio2;
	int buttonPressed;
	String searchedname;
	String searcheduser, categorystring = "";
	@Override
	public void onStart(){
		super.onStart();
		Common.context=this;
	}
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		Common.activity = this;
		searchname = (EditText) findViewById(R.id.searchtext);
		search_btn = (Button) findViewById(R.id.searchbtn);
		name = (TextView) findViewById(R.id.name);
		add = (Button) findViewById(R.id.add);
		radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
		radio0 = (RadioButton) findViewById(R.id.radio0);
		radio1 = (RadioButton) findViewById(R.id.radio1);
		radio2 = (RadioButton) findViewById(R.id.radio2);
		radio0.setOnClickListener(this);
		radio1.setOnClickListener(this);
		radio2.setOnClickListener(this);
		registerReceiver(mHandleMessageReceiver, new IntentFilter(
				" com.start.DISPLAY_MESSAGE"));
		Common.presenter = new MainPresenter(Search.this);

		add.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				if (searchname.getText().toString().trim() == null) {
					showAlert("Enter fuknig name", Style.ALERT);
					return;
				}
				buttonPressed = SEND_REQUEST;
				Common.presenter.startManager();
			}
		});
		search_btn.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				searchedname = searchname.getText().toString().trim();
				if (searchedname.isEmpty()) {
					Toast.makeText(Search.this,
							"Please do not leave any field empty!",
							Toast.LENGTH_SHORT).show();
					return;
				} else {
					if (searchname.getText().toString().trim()
							.equalsIgnoreCase(Common.user.getEmail())) {
						showAlert("Are you looking for yourself! Found you",
								Style.ALERT);
						return;
					}
					buttonPressed = SEARCH_USER;
					Common.presenter.startManager();
				}
			}
		});

		Common.context = this;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == radio0) {
			categorystring = radio0.getText().toString();
			// Handle clicks for radio0
		} else if (v == radio1) {
			categorystring = radio1.getText().toString();
		} else if (v == radio2) {
			categorystring = radio2.getText().toString();
		}
	}

	final BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			String newMessage = intent.getStringExtra("message");
			if (newMessage == null) {
				Log.e("null", "null");

			} else {
				Log.e("message is:" + newMessage, "message is:" + newMessage);

			}
			// Waking up mobile if it is sleeping
			WakeLocker.acquire(getApplicationContext());

			/**
			 * Take appropriate action on this message depending upon your app
			 * requirement For now i am just displaying it on the screen
			 * */

			Toast.makeText(Search.this, "New Message: " + newMessage,
					Toast.LENGTH_SHORT).show();

			// Releasing wake lock
			WakeLocker.release();
		}
	};

	@Override
	public void showAlert(String message, Style style) {
		Crouton.makeText(this, message, style).show();
	}

	final int SEND_REQUEST = 1, SEARCH_USER = 2;

	@Override
	public String loadDataFromNetwork() {

		switch (buttonPressed) {
		case SEND_REQUEST:
			showAlert("Sending request..", Style.CONFIRM);

			Log.e("r", Common.user.getEmail()+":"+searchedname+":"+"pending");
			SendData.sendfriendrequest(Common.user.getEmail(), searchedname,
					"pending");
		case SEARCH_USER:

			return SendData.getUsers(searchedname);
		}
		return null;
	}

	@Override
	public void onRequestSuccess(final String response) {
		// showAlert("Friend request sent. Pending reply..",Style.CONFIRM);
		if (response.contains("false")) {
			Log.e("repso", response);
			showAlert("No User Is Registerd With This E-Mail!", Style.ALERT);
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					name.setText(searchedname);
					name.setVisibility(View.GONE);
					add.setVisibility(View.GONE);
					radioGroup1.setVisibility(View.GONE);
				}
			});
			
		} else {
			if (!response.contains("null")) {
				Log.e("repsonce if friend", response);
				String[] a = response.split(",");
				if (a.length > 1)
					Toast.makeText(Search.this, "Friendship :" + a[1],
							Toast.LENGTH_SHORT).show();
			}
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					name.setText(searchedname);
					name.setVisibility(View.VISIBLE);
					add.setVisibility(View.VISIBLE);
					radioGroup1.setVisibility(View.VISIBLE);
				}
			});
		}

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mHandleMessageReceiver);
	}

}
