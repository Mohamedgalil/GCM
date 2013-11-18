package com.start;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class SendData {
	public static SendData s;
	 static Runnable r;
	public SendData() {
		s = this;
	}

	public static void logout(Context context, final String emailstring) {
		        HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(Common.logout);
				try {
					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
					nameValuePairs.add(new BasicNameValuePair("email", emailstring));
					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					ResponseHandler<String> responseHandler = new BasicResponseHandler();
					String response = httpclient.execute(httppost, responseHandler);
					Log.e("logout responce", response);
				} catch (ClientProtocolException e) {
				} catch (IOException e) {
				}
		
	}

	public static void store(Context context, String namestring,
			String emailstring, final String regid, String passwordstring) {
		final HttpClient httpclient = new DefaultHttpClient();
		final HttpPost httppost = new HttpPost(Common.register);
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("name", namestring));
			nameValuePairs.add(new BasicNameValuePair("email", emailstring));
			nameValuePairs.add(new BasicNameValuePair("regId", regid));
			nameValuePairs.add(new BasicNameValuePair("password",
					passwordstring));
			Toast.makeText(context, "Registeredz", Toast.LENGTH_SHORT).show();
			Log.e("Registeredz", "Registeredz");
			Log.e(regid, regid);
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			final ResponseHandler<String> responseHandler = new BasicResponseHandler();
			        	String response;
							response = httpclient.execute(httppost, responseHandler);
							Log.e(response, response); 
						} catch (ClientProtocolException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
	}

	public static void sendfriendrequest(String emailuser, String friend,
			String category) {

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Common.sendFriendRequest);
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("useremail", emailuser));
			nameValuePairs.add(new BasicNameValuePair("friendemail", friend));
			nameValuePairs.add(new BasicNameValuePair("category", category));
			Log.e(category, category);
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String response = httpclient.execute(httppost, responseHandler);
			Log.e(response, response);
		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		}
	}
	public static void rejectrequest(String friend) {
		Log.e("The request friend is:"+friend,"The request friend is:"+friend);
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(Common.rejectrequest);
				try {
					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
					nameValuePairs.add(new BasicNameValuePair("useremail", Common.user.getEmail()));
					nameValuePairs.add(new BasicNameValuePair("friendemail", friend));
					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					ResponseHandler<String> responseHandler = new BasicResponseHandler();
					String response = httpclient.execute(httppost, responseHandler);
					Log.e("the request" + response, response);
				} catch (ClientProtocolException e) {
					Log.e("error is"+e,"error is"+e);

				} catch (IOException e) {
					Log.e("error is"+e,"error is"+e);

				}
			}
	public static void acceptrequest(String friend) {
Log.e("The request friend is:"+friend,"The request friend is:"+friend);
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Common.acceptrequest);
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("useremail", Common.user.getEmail()));
			nameValuePairs.add(new BasicNameValuePair("friendemail", friend));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String response = httpclient.execute(httppost, responseHandler);
			Log.e("the request" + response, response);
		} catch (ClientProtocolException e) {
			Log.e("error is"+e,"error is"+e);

		} catch (IOException e) {
			Log.e("error is"+e,"error is"+e);

		}
	}

	public static void sendmessage(String email, String friendemail, String message) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Common.sendmessage);
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("useremail", email));
			nameValuePairs.add(new BasicNameValuePair("friendemail", friendemail));
			nameValuePairs.add(new BasicNameValuePair("message", message));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String response = httpclient.execute(httppost, responseHandler);
			Log.e(response, response);
		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		}
	}

	public static String  CheckLogin(final String email, final String password, final  String registrationId) {
		
	        	HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(Common.login);
				try {
					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
					nameValuePairs.add(new BasicNameValuePair("email", email));
					nameValuePairs.add(new BasicNameValuePair("password", password));
					nameValuePairs.add(new BasicNameValuePair("regid", registrationId));

					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					ResponseHandler<String> responseHandler = new BasicResponseHandler();
					String response = handleString(httpclient.execute(httppost, responseHandler));
					Log.e("login senddata", response);
					return  response;
				} catch (ClientProtocolException e) {
				} catch (IOException e) {
			}
			
	    
		return false+"";

	}

	public static void addfriend(String emailuser, String friend,
			String category) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Common.addFriend);
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("useremail", emailuser));
			nameValuePairs.add(new BasicNameValuePair("friendemail", friend));
			nameValuePairs.add(new BasicNameValuePair("category", category));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String response = httpclient.execute(httppost, responseHandler);
			Log.e(response, response);
		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		}
	}
	public static String getUsers(String email) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Common.search);
		String response=null;
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("useremail", Common.user.getEmail()));
			nameValuePairs.add(new BasicNameValuePair("friendemail", email));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			 response = handleString2(httpclient.execute(httppost, responseHandler));
			Log.e(response, response);
			return response;
		} catch (ClientProtocolException e) {
			Log.e(response, response);

		} catch (IOException e) {
			Log.e(response, response);

		}
		return null;
	}
	
	private static String handleString(String response){
		response=response.replaceAll("<!-- Hosting24 Analytics Code -->", "");
		Log.e("REplace a"	,response);
		response=response.replaceAll("<script type=\"text/javascript\" src=\"http://stats.hosting24.com/count.php\"></script>", "");
		response=response.replaceAll("<!-- End Of Analytics Code -->", "");
		Log.e("REplace a"	,response);
		return response;
	}
	private static String handleString2(String response){
		String[]a=response.split("#");
		for(String b:a)
			Log.e("got", b);
		for(String b:a)
			if(!b.isEmpty())
				return b;
		return response;//responce= true/false , false/category
	}

}
