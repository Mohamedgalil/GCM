package com.library;

import com.octo.android.robospice.JacksonSpringAndroidSpiceService;
import com.octo.android.robospice.SpiceManager;

public class BasePresenter {
	
	private SpiceManager requestManager = new SpiceManager(JacksonSpringAndroidSpiceService.class);

	protected SpiceManager getRequestManager() {
		return requestManager;
	}

}
