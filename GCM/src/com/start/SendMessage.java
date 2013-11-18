package com.start;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


import com.google.android.gcm.GCMRegistrar;
import com.library.advanced.Requests;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class SendMessage extends Activity implements OnClickListener {


	private Button button1;
	static String message,email="";
	private EditText editText1,editText2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sendmessage);
	
		button1 = (Button)findViewById( R.id.Requests );
		editText1 = (EditText)findViewById( R.id.editText1 );
		editText2 = (EditText)findViewById( R.id.editText2 );

	
		button1.setOnClickListener( this );

	
}
	@Override
	public void onClick(View v) {
	message = editText1.getText().toString().trim();
		 email =editText2.getText().toString().trim();
		if(message.isEmpty()  || email.isEmpty()){
	
			return;
		}else{
			
			LongOperation longoperation = new LongOperation();
			longoperation.execute(null);
	
		}
		
}
	public class LongOperation extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			SendData.sendmessage(Common.email,email, message);

			return null;
		}
		 @Override
	        protected void onPostExecute(Void result) {
			 editText1.setText("");
			 
	        }
	}
	

	


}
