package com.start;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;

import com.library.User;
import com.library.advanced.MainPresenter;
import com.library.advanced.PresenterInterface;

public final class Common {
	public static PresenterInterface activity ;
	public static MainPresenter presenter;
public static ArrayList<String> requests = new ArrayList();
public static ArrayList<String> messages = new ArrayList();

	public static final String register="http://gelly.comze.com/register.php",
			store="", sendFriendRequest="http://gelly.comze.com/sendfriendrequest.php",
			sendmessage="http://gelly.comze.com/sendmessage.php",
			login="http://gelly.comze.com/login.php",
			addFriend="http://gelly.comze.com/addfriend.php",
			logout="http://gelly.comze.com/logout.php",getrequests="http://gelly.comze.com/getrequests.php",
			search="http://gelly.comze.com/searchusers.php",acceptrequest="http://gelly.comze.com/acceptrequest.php",getmessages="http://gelly.comze.com/getmessages.php",rejectrequest="http://gelly.comze.com/rejectrequest.php";
	
	public static boolean isLoggedIn=false;
	public static User user=new User();
    // Google project id
	//
    static final String SENDER_ID = "866596536732"; 
    static String id = ""; 
	static String email = "";
	public static Boolean isFriend = false;
	public static Context context;
	static final String DISPLAY_MESSAGE_ACTION = "com.androidhive.pushnotifications.DISPLAY_MESSAGE";

    static final String EXTRA_MESSAGE = "message";

    /**
     * Notifies UI to display a message.
     * <p>
     * This method is defined in the common helper because it's used both by
     * the UI and the background service.
     *
     * @param context application's context.
     * @param message message to be displayed.
     */
    static void displayMessage(Context context, String message) {
        Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
        intent.putExtra(EXTRA_MESSAGE, message);
        context.sendBroadcast(intent);
    }
}
