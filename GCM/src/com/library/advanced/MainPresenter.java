package com.library.advanced;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import android.util.Log;

import com.octo.android.robospice.exception.RequestCancelledException;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import de.keyboardsurfer.android.widget.crouton.Style;

public class MainPresenter implements RequestListener<String> {

	private PresenterInterface activity;

	public MainPresenter(PresenterInterface activity) {
		super();
		this.activity = activity;
	}
	
	MainRequest request;
	
	public void startManager() {
		request = new MainRequest(activity);
		activity.getRequestManager().execute(
				request,
				request.getCacheKey(),
				request.getCacheExpireTime(), 
				this);
		Log.e("authenticating", "user");

	}
	@Override
	public void onRequestFailure(SpiceException e) {
		Log.e("fail", "user");
		activity.showAlert("Authentication Failed!  Check your Username or password", Style.ALERT);
		if (e.getCause() instanceof HttpClientErrorException) {
			HttpClientErrorException exception = (HttpClientErrorException) e.getCause();
			HttpStatus responseStatus = exception.getStatusCode();
			switch (responseStatus) {
				case UNAUTHORIZED:
					Log.e("app", "401 ERROR");
					break;
				case NOT_FOUND:
					Log.e("app", "404 ERROR");
					break;
				case BAD_REQUEST:
					Log.e("app", "400 ERROR");
				default:
					Log.e("app", responseStatus.getReasonPhrase());
			}
		} 
		else if (e.getCause() instanceof HttpServerErrorException) {
			HttpServerErrorException exception = (HttpServerErrorException) e.getCause();
			HttpStatus responseStatus = exception.getStatusCode();
			switch (responseStatus) {
				case INTERNAL_SERVER_ERROR:
					Log.e("app", "500 ERROR");
					break;
				default:
					Log.e("app", responseStatus.getReasonPhrase());
			}
		} 
		else if (e.getCause() instanceof ResourceAccessException) {
			Log.e("app", "Server is down!"); 
		} 
		else if (e instanceof RequestCancelledException) {
			Log.e("app", "Cancelled");
		} 
		else {
			Log.e("app", "Unknow exception");
		}
	}

	/**
	 * After successful login the user is saved to the application global state and the next activity will be called
	 */
	@Override
	public void onRequestSuccess(String response) {
		Log.e("success", response+" bola");
		activity.onRequestSuccess(response);
	}

}
