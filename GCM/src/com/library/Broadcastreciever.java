package com.library;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;


public class Broadcastreciever extends BroadcastReceiver {
	public static Broadcastreciever r = new Broadcastreciever();
	public static boolean isConnected = false;
	public static final NetworkChangeReceiver reciever = new NetworkChangeReceiver();
	static Thread thread;
	static Runnable runnable;

	@Override
	public void onReceive(final Context context, final Intent intent) {
		isNetworkAvailable(context);

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
