package com.library;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.start.Common;
import com.start.GetData;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;


public class Broadcastreciever extends BroadcastReceiver {
	private NetworkChangeReceiver receiver;
	public static Broadcastreciever r = new Broadcastreciever();
	public static boolean isConnected = false;
	public static final NetworkChangeReceiver reciever = new NetworkChangeReceiver();
	static Thread thread;
	static Runnable runnable;

	@Override
	public void onReceive(final Context context, final Intent intent) {
		isNetworkAvailable(context);

	}
	public static void intiatethread(){
		runnable=new Runnable() {
			
			@Override
			public void run() {
				while(true){
					
					try {
						if(GetData.GetMessages()==null){
							Thread.sleep(1000);
							continue;
						}
						
						GetData.GetMessages();
						if(!Common.messages.isEmpty())
							for(int i=0;i<Common.messages.size();i++){
								Crouton.showText((Activity) Common.context, Common.messages.get(i), Style.CONFIRM);
								Thread.sleep(2000);
							}
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		thread = new Thread(runnable);
		thread.run();
	}

	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						if (!isConnected) {
							isConnected = true;
							Log.d("" + isConnected, "connected!");
							Toast.makeText(context,
									"Your internet is connected!",
									Toast.LENGTH_SHORT).show();
							
						}
						return true;
					}
				}
			}
		}
		Toast.makeText(context, "Your internet is not connected!",
				Toast.LENGTH_SHORT).show();
		isConnected = false;
		return false;
	}

	public static class NetworkChangeReceiver extends BroadcastReceiver {
		private NetworkChangeReceiver receiver;
		public static boolean isConnected = false;
		public static final NetworkChangeReceiver reciever = new NetworkChangeReceiver();

		@Override
		public void onReceive(final Context context, final Intent intent) {
			isNetworkAvailable(context);

		}

		public boolean isNetworkAvailable(Context context) {
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				NetworkInfo[] info = connectivity.getAllNetworkInfo();
				if (info != null) {
					for (int i = 0; i < info.length; i++) {
						if (info[i].getState() == NetworkInfo.State.CONNECTED) {
							if (!isConnected) {
								isConnected = true;
								Log.d("" + isConnected, "connected!");
								// do your processing here ---
								// if you need to post any data to the server or
								// get status
								// update from the server
							}
							return true;
						}
					}
				}
			}
			isConnected = false;
			return false;
		}
	}
}
