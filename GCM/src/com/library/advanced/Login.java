package com.library.advanced;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.library.BaseActivity;
import com.library.User;
import com.start.Common;
import com.start.R;
import com.start.SendData;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class Login extends BaseActivity implements OnClickListener, PresenterInterface {

	EditText usernameEdittext;
	EditText passwordEdittext;
	Button loginButton;
	Button registerButton;
	static String namestring, passwordstring, emailstring;
	static MainPresenter presenter;
	
	@Override
	public void onStart(){
		super.onStart();
		Common.context=this;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Common.activity=this;
		setContentView(R.layout.login);
		usernameEdittext = (EditText) findViewById(R.id.username_edittext);
		passwordEdittext = (EditText) findViewById(R.id.password_edittext);
		loginButton = (Button) findViewById(R.id.login_button);
		registerButton = (Button) findViewById(R.id.regiter_button);
		usernameEdittext.setText("m");	
		passwordEdittext.setText("m");
		Common.presenter=new MainPresenter(this);
		loginButton.setOnClickListener(this);
		registerButton.setOnClickListener(this);
		/**
		 * if(isLoggedin()){ SharedPreferences prefs = getSharedPreferences(
		 * "Preferences", Context.MODE_PRIVATE); Common.email=
		 * prefs.getString("email","email"); Intent nextScreen = new
		 * Intent(Login.this, DashBoard.class); startActivity(nextScreen); }
		 **/
	}

	@Override
	public void onClick(View v) {
		emailstring = usernameEdittext.getText().toString().trim();
		passwordstring = passwordEdittext.getText().toString().trim();
		if (v == loginButton) {
			if (emailstring.isEmpty() || passwordstring.isEmpty()) {
				Toast.makeText(getApplicationContext(),
						"Please do not leave any Field Empty",
						Toast.LENGTH_SHORT).show();
				return;
			}
			User user = new User();		
	    	user.setEmail(emailstring);
	    	user.setPassword(passwordstring);
	    	Common.user=user;
	    	Common.presenter.startManager();
		}
		if (v == registerButton) {
			Intent nextScreen = new Intent(Login.this, Register.class);
			startActivity(nextScreen);
		}
	}
	public void onSignInSuccess(String response) {
		if (response.contains("true")) {
			Common.isLoggedIn=true;
	    	
			Intent nextScreen = new Intent(Login.this, DashBoard.class);
			startActivity(nextScreen);
			finish();

		} else {
			Log.e("false login", response);
	    	Crouton.makeText(this, "Authentication Failed!  Check your Username or password", Style.ALERT).show();
			Common.isLoggedIn= false;
		}
	}

	@Override
	public void showAlert(String message, Style style) {
    	Crouton.makeText(this, message, style).show();
		
	}

	@Override
	public String loadDataFromNetwork() {

		return SendData.CheckLogin(Common.user.getEmail(), Common.user.getPassword())+"";
	}

	@Override
	public void onRequestSuccess(String responce) {
		onSignInSuccess(responce);
	}
	@Override
	public void onResume() {
		super.onStart();
		Common.presenter=new MainPresenter(this);
	}

}
