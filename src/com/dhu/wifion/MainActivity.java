package com.dhu.wifion;

import android.app.Activity;
import android.net.wifi.WifiConfiguration;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;

import com.dhu.wifion.util.WifiApManager;

public class MainActivity extends Activity {
	Button btnOFF;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		try {
			WifiApManager w = new WifiApManager(getApplicationContext());

			WifiConfiguration wc = w.getWifiApConfiguration();
			w.setWifiApState(wc, true);
		} catch (Exception ex) {
			Log.e("MainActivity->onCreate",
					"some problem in the construction of WifiApManager");
		}
		finish();
		// btnOFF = (Button) findViewById(R.id.button1);
		// btnOFF.setOnClickListener(new Button.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// try {
		// WifiApManager w = new WifiApManager(getApplicationContext());
		//
		// WifiConfiguration wc = w.getWifiApConfiguration();
		// w.setWifiApState(wc, true);
		// } catch (Exception e) {
		// Log.e("MainActivity->onCreate",
		// "some problem in the construction of WifiApManager");
		// }
		// // System.exit(0);
		// }
		// });

	}
}
