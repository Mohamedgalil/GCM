package com.library.advanced;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.library.BaseActivity;
import com.start.Common;
import com.start.R;
import com.start.SendData;
import com.start.SendMessage;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class DashBoard extends BaseActivity implements PresenterInterface {
	Button button1;
	Button button2;
	Button logout;
	Button requests;
	int buttonPressed; // is a button
	final int LOGOUT = 0, SEND_MESSAGE = 1, SEARCH_USER = 2, REQUESTS = 3;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Common.presenter = new MainPresenter(this);

		setContentView(R.layout.dashboard);
		button1 = (Button) findViewById(R.id.sends);
		button2 = (Button) findViewById(R.id.Search);
		requests = (Button) findViewById(R.id.Requests);
		logout = (Button) findViewById(R.id.logout);
		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent nextScreen = new Intent(DashBoard.this,
						SendMessage.class);
				startActivity(nextScreen);
			}
		});
		button2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent nextScreen = new Intent(DashBoard.this, Search.class);
				startActivity(nextScreen);
			}
		});
		requests.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				buttonPressed = REQUESTS;
				Intent nextScreen = new Intent(DashBoard.this, Requests.class);
				startActivity(nextScreen);
			}
		});
		logout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				buttonPressed = LOGOUT;
				Common.presenter.startManager();
			}
		});
		Crouton.makeText(this, "Authentication success", Style.CONFIRM).show();

	}

	@Override
	public void showAlert(String message, Style style) {
		// TODO Auto-generated method stub

	}

	@Override
	public String loadDataFromNetwork() {
		switch (buttonPressed) {
		case LOGOUT:
			SendData.logout(this, Common.user.getEmail());
			SharedPreferences prefs = getSharedPreferences("Preferences",
					Context.MODE_PRIVATE);
			SharedPreferences.Editor edit = prefs.edit();
			edit.putBoolean("loggedin", false);
			edit.commit();
			break;
		case SEND_MESSAGE:
			Intent nextScreen = new Intent(DashBoard.this, SendMessage.class);
			startActivity(nextScreen);
			break;
		case SEARCH_USER:
			nextScreen = new Intent(DashBoard.this, Search.class);
			startActivity(nextScreen);
			break;
		case REQUESTS:
			nextScreen = new Intent(DashBoard.this, Requests.class);
			startActivity(nextScreen);
			break;
		}
		return null;
	}

	@Override
	public void onRequestSuccess(String response) {
		switch (buttonPressed) {
		case LOGOUT:
			Intent nextScreen = new Intent(DashBoard.this, Login.class);
			startActivity(nextScreen);
			finish();
			break;
		}

	}
	@Override
	public void onStart(){
		super.onStart();
		Common.context=this;
	}
	@Override
	public void onResume() {
		super.onStart();
		Common.presenter=new MainPresenter(this);
	}

}
