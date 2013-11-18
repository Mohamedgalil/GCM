package com.library.advanced;

import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

public class MainRequest extends SpringAndroidSpiceRequest<String> {

	private PresenterInterface activity;

	public MainRequest(PresenterInterface activity) {
		super(String.class);
		this.activity=activity;
	}

	@Override
	public String loadDataFromNetwork() throws Exception {
		return activity.loadDataFromNetwork();
	}

	public String getCacheKey() {
		return null;
	}

	public long getCacheExpireTime() {
		return DurationInMillis.ONE_SECOND;
	}
}