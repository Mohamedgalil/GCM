package com.library.advanced;

import com.octo.android.robospice.SpiceManager;

import de.keyboardsurfer.android.widget.crouton.Style;

public interface PresenterInterface  {
	/**
	 * For Crouton only
	 * @param message
	 * @param style Crouton style
	 */
	public void showAlert(String message, Style style);
	public String loadDataFromNetwork();
	public SpiceManager  getRequestManager();
	public void onRequestSuccess(String response);
	public void onResume();
}
