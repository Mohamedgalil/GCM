package com.start;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

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
