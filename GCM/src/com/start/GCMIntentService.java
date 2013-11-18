package com.start;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PowerManager;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {

	private static final String TAG = "GCM Tutorial::Service";

	// Use your PROJECT ID from Google API into SENDER_ID
	public static final String SENDER_ID = "866596536732";

	public GCMIntentService() {
		super(SENDER_ID);
	}

	@Override
	protected void onRegistered(Context context, String registrationId) {
		setRegisteredFlag();
		if(Common.Register)
			SendData.store(context,Common.user.getName()
					, Common.user.getEmail(), 
					registrationId,
					Common.user.getPassword());
		else
			Common.response=SendData.CheckLogin(Common.user.getEmail(), Common.user.getPassword(),registrationId);
	}

	@Override
	protected void onUnregistered(Context context, String registrationId) {

	}

	@Override
	protected void onMessage(Context context, Intent data) {
		String message;
		message = data.getStringExtra("message");
		Log.e("message is:" + message, "message is:" +message);


//		PendingIntent pIntent = PendingIntent.getActivity(this, 0, null,
//				PendingIntent.FLAG_UPDATE_CURRENT);
		// Create the notification with a notification builder
		Notification notification = new Notification(R.drawable.ic_launcher, "my ticker", System.currentTimeMillis());
		notification.flags = Notification.FLAG_ONGOING_EVENT;
	//	Intent i = new Intent(this,GCMMainActivity.class);
		PendingIntent pd = PendingIntent.getActivity(this, 1, null, Intent.FLAG_ACTIVITY_NEW_TASK); 
//

		notification.setLatestEventInfo(context, message,message, pd);
		NotificationManager mN = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		mN.notify(1,notification);
		{
			// Wake Android Device when notification received
			PowerManager pm = (PowerManager) context
					.getSystemService(Context.POWER_SERVICE);
			final PowerManager.WakeLock mWakelock = pm.newWakeLock(
					PowerManager.FULL_WAKE_LOCK
							| PowerManager.ACQUIRE_CAUSES_WAKEUP, "GCM_PUSH");
			mWakelock.acquire();

			// Timer before putting Android Device to sleep mode.
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				public void run() {
					mWakelock.release();
				}
			};
			timer.schedule(task, 5000);
		}
		/**
		 * 		ArrayList <String>x = new ArrayList();

		 	final String[]s;
		s= message.split("&");
  AlertDialog.Builder alertDialog = new AlertDialog.Builder(Common.context);
		 alertDialog.setTitle("New Friend Request!");
  alertDialog.setMessage("Do you want to add" + message);
   alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
            	//SendData.addfriend(Common.email, s[1], s[2]);
  }
        });
 
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
            }
        });
 
        alertDialog.show();


		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);

		mBuilder.setContentTitle("New Friend Request!")
		        .setContentText(message);


		mNotificationManager.notify(0, mBuilder.build());
	

		
			**/

	}

	@Override
	protected void onError(Context arg0, String errorId) {

		Log.e(TAG, "onError: errorId=" + errorId);
	}
	private void setRegisteredFlag() {
		SharedPreferences prefs = getSharedPreferences("Preferences",
				Context.MODE_PRIVATE);
		SharedPreferences.Editor edit = prefs.edit();
		edit.putBoolean("firstLaunch", true);
		edit.commit();
		//Log.e("committed", "committed");
	}
}