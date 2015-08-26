package com.dhu.wifion.util;

import java.lang.reflect.Method;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.util.Log;

public final class WifiApManager {
	private static final int WIFI_AP_STATE_FAILED = 4;
	private WifiManager mWifiManager;
	private final String TAG = "Wifi Access Manager";
	private Method wifiControlMethod;
	private Method wifiApConfigurationMethod;
	private Method wifiApState;

	public WifiApManager(Context context) throws SecurityException,
			NoSuchMethodException {
		if (context == null) {
			Log.d("WifiApManager->WifiApManager", "context is null");
			return;
		}
		mWifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		wifiControlMethod = mWifiManager.getClass().getMethod(
				"setWifiApEnabled", WifiConfiguration.class, boolean.class);
		wifiApConfigurationMethod = mWifiManager.getClass().getMethod(
				"getWifiApConfiguration");
		wifiApState = mWifiManager.getClass().getMethod("getWifiApState");
	}

	public boolean setWifiApState(WifiConfiguration config, boolean enabled) {
		if (config == null) {
			Log.d("WifiApManager->setWifiApState", "config is null");
			return false;
		}
		try {
			if (enabled) {
				mWifiManager.setWifiEnabled(!enabled);
			}
			return (Boolean) wifiControlMethod.invoke(mWifiManager, config,
					enabled);
		} catch (Exception e) {
			Log.e(TAG, "In setWifiApState function", e);
			return false;
		}
	}

	public WifiConfiguration getWifiApConfiguration() {
		try {
			return (WifiConfiguration) wifiApConfigurationMethod
					.invoke(mWifiManager);
		} catch (Exception e) {
			return null;
		}
	}

	public int getWifiApState() {
		try {
			return (Integer) wifiApState.invoke(mWifiManager);
		} catch (Exception e) {
			Log.e(TAG, "", e);
			return WIFI_AP_STATE_FAILED;
		}
	}
}