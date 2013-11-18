package com.start;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class GetData {

	static InputStream is = null;
	public static JSONObject jObj = null;
	public static String json = "";
	public static String GetMessages() {

		String message = "";
		JSONArray jsonArray= new JSONArray();
		Common.messages.clear();
		try {
			if (getmessages() ==null)
				return null;
			if(getmessages().getJSONArray("messages")!=null){
			jsonArray = getmessages().getJSONArray("messages");

			for (int i = 0; i < jsonArray.length(); i++) {
				try {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					try {
						if (!jsonObject.getString("message").isEmpty()) {
							Log.e(jsonObject.getString("message").toString()
									.trim(), jsonObject.getString("message")
									.toString().trim());
							Common.messages.add(jsonObject.getString("message"));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			}
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return message;
	}
	
	public static String GetRequests() {

		String email = "";
		JSONArray jsonArray;
		try {
			jsonArray = getRequests().getJSONArray("users");
			if (jsonArray == null)
				return null;
			for (int i = 0; i < jsonArray.length(); i++) {
				try {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					try {
						if (!jsonObject.getString("email").isEmpty()) {
							Log.e(jsonObject.getString("email").toString()
									.trim(), jsonObject.getString("email")
									.toString().trim());
							Common.requests.add(jsonObject.getString("email"));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return email;
	}

	public static JSONObject getRequests() {

		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(Common.getrequests);

		try {
			JSONObject jo = new JSONObject();
			try {
				jo.put("useremail", Common.user.getEmail());
				httpPost.setEntity(new StringEntity(jo.toString(), "UTF-8"));

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Set up the header types needed to properly transfer JSON
			httpPost.setHeader("Content-Type", "application/json");
			httpPost.setHeader("Accept-Encoding", "application/json");
			httpPost.setHeader("Accept-Language", "en-US");
			HttpResponse response = client.execute(httpPost);
			Log.e("JSON Parser", "Error parsing data " + response.toString());
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line + "\n");
				}
				content.close();
				json = builder.toString();

			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			jObj = new JSONObject(json);
			return jObj;

		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
			return null;
		}

	}
	
	
	public static JSONObject getmessages() {

		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(Common.getmessages);

		try {
			JSONObject jo = new JSONObject();
			try {
				jo.put("useremail", Common.user.getEmail());
				httpPost.setEntity(new StringEntity(jo.toString(), "UTF-8"));

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Set up the header types needed to properly transfer JSON
			httpPost.setHeader("Content-Type", "application/json");
			httpPost.setHeader("Accept-Encoding", "application/json");
			httpPost.setHeader("Accept-Language", "en-US");
			HttpResponse response = client.execute(httpPost);
			Log.e("JSON Parser", "Error parsing data " + response.toString());
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line + "\n");
				}
				content.close();
				json = builder.toString();

			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			jObj = new JSONObject(json);
			return jObj;

		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
			return null;
		}

	}
}
