package com.library.advanced;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gcm.GCMRegistrar;
import com.library.BaseActivity;
import com.start.Common;
import com.start.R;

import de.keyboardsurfer.android.widget.crouton.Style;

public class Register extends BaseActivity implements PresenterInterface {

	EditText name;
	EditText email;
	EditText password;
	Button register;
	static String namestring, passwordstring, emailstring;
	@Override
	public void onStart(){
		super.onStart();
		Common.context=this;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		name = (EditText) findViewById(R.id.name);
		email = (EditText) findViewById(R.id.email);
		password = (EditText) findViewById(R.id.password);
		register = (Button) findViewById(R.id.register);
		Common.presenter=new MainPresenter(this);
		register.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.e("onclick","sssss");
				emailstring = email.getText().toString().trim();
				passwordstring = password.getText().toString().trim();
				namestring = name.getText().toString().trim();
		    	Common.presenter.startManager();
				Common.user.setEmail(emailstring);
				Common.user.setPassword(passwordstring);
				Common.user.setName(emailstring);

			}
		});

	}



	private boolean isRegistered() {
		SharedPreferences prefs = getSharedPreferences("Preferences",
				Context.MODE_PRIVATE);
		return prefs.getBoolean("firstLaunch", false);
	}


	@Override
	public void showAlert(String message, Style style) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String loadDataFromNetwork() {
		GCMRegistrar.checkDevice(this);
		GCMRegistrar.checkManifest(this);
		if (isRegistered() == false) {
			GCMRegistrar.register(this, "866596536732");
		} else {
			SharedPreferences prefs = getSharedPreferences("Preferences",
					Context.MODE_PRIVATE);
			SharedPreferences.Editor edit = prefs.edit();
			edit.putBoolean("firstLaunch", false);
			edit.commit();
			GCMRegistrar.unregister(this);
			GCMRegistrar.register(this, "866596536732");
		}
		return null;
	}
	@Override
	public void onRequestSuccess(String response) {
		Intent nextScreen = new Intent(Register.this, DashBoard.class);
		startActivity(nextScreen);
		finish();		
	}

}
