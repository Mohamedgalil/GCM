package com.library.advanced;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.start.Common;
import com.start.GetData;
import com.start.R;
import com.start.SendData;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class Requests extends Activity {

	ArrayAdapter<String> arrayAdapter;
	String selectedelement;
	LongOperation op;
	ListView listv;
	boolean add;

	@Override
	public void onStart() {
		super.onStart();
		Common.context = this;
	}

	public void onCreate(Bundle saveInstanceState) {
		super.onCreate(saveInstanceState);
		setContentView(R.layout.request);
		listv = (ListView) findViewById(R.id.listview);
		LongOperation2 op2 = new LongOperation2();
		op2.execute();
		arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, Common.requests);
		listv.setAdapter(arrayAdapter);
		listv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View v,
					final int position, long arg3) {
				selectedelement = Common.requests.get(position);
				DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
						case DialogInterface.BUTTON_POSITIVE:
							add = true;

							break;

						case DialogInterface.BUTTON_NEGATIVE:
							add = false;
							break;
						}
						op = new LongOperation();
						op.execute();
					}
				};

				AlertDialog.Builder builder = new AlertDialog.Builder(
						Requests.this);
				builder.setMessage(
						selectedelement + " ,Requests to add you as a friend.")
						.setPositiveButton("Accept", dialogClickListener)
						.setNegativeButton("Reject", dialogClickListener)
						.show();
			}
		});
	}

	public void addElement(String x) {
		Common.requests.add(x);
		refreshList();
	}

	public void removeElement(String x) {
		Common.requests.remove(x);
		refreshList();
	}

	public void refreshList() {

		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				arrayAdapter.notifyDataSetChanged();
			}
		});
	}

	public class LongOperation extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			if (add)
				SendData.acceptrequest(selectedelement);
			else
				SendData.rejectrequest(selectedelement);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (add) {
				Crouton.showText(Requests.this, selectedelement + " added",
						Style.CONFIRM);

			} else {
				Crouton.showText(Requests.this, selectedelement + " Rejected",
						Style.ALERT);

			}
			removeElement(selectedelement);
			selectedelement = null;
		}
	}

	public class LongOperation2 extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			GetData.GetRequests();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			arrayAdapter = new ArrayAdapter<String>(Requests.this,
					android.R.layout.simple_list_item_1, Common.requests);
			listv.setAdapter(arrayAdapter);
		}
	}

}
