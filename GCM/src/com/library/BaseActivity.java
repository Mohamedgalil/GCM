package com.library;

import android.support.v4.app.FragmentActivity;

import com.octo.android.robospice.Jackson2SpringAndroidSpiceService;
import com.octo.android.robospice.SpiceManager;

public abstract class BaseActivity extends FragmentActivity {

	private SpiceManager requestManager = new SpiceManager(Jackson2SpringAndroidSpiceService.class);

	@Override
	protected void onStart() {
		super.onStart();
		requestManager.start(this);
	}

	@Override
	protected void onResume() {
		super.onStart();
		requestManager.start(this);
	}
	@Override
	protected void onStop() {
		requestManager.shouldStop();
		super.onStop();
	}

	public SpiceManager getRequestManager() {
		return requestManager;
	}

}
