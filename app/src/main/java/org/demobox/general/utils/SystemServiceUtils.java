package org.demobox.general.utils;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.view.WindowManager;

/**
 * 拿system service的便捷类
 * @author Chason Wu
 */
public class SystemServiceUtils {
	
	private static ActivityManager am;
	private static ConnectivityManager cm;
	private static DevicePolicyManager dpm;
	private static KeyguardManager km;
	private static LocationManager lm;
	private static TelephonyManager tm;
	private static WifiManager wifi;
	private static WindowManager wm;
	
	private SystemServiceUtils() {}
	
	/**
	 * 相当于window里面的任务管理器
	 * @param context
	 * @return
	 */
	public static ActivityManager getActivityManager(Context context) {
		if (am == null)
			am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

		return am;
	}
	
	public static ConnectivityManager getConnectivityManager(Context context) {
		if (cm == null)
			cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		
		return cm;
	}
	
	public static DevicePolicyManager getDevicePolicyManager(Context context) {
		if (dpm == null)
			dpm = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
		
		return dpm;
	}
	
	/**
	 * 可以做一些和锁屏相关的操作
	 * @param context
	 * @return
	 */
	public static KeyguardManager getKeyguardManager(Context context) {
		if (km == null)
			km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
		
		return km;
	}
	
	public static LocationManager getLocationManager(Context context) {
		if (lm == null)
			lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		
		return lm;
	}

	public static TelephonyManager getTelephonyManager(Context context) {
		if (tm == null)
			tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

		return tm;
	}

	public static WifiManager getWifiManager(Context context) {
		if (wifi == null)
			wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

		return wifi;
	}

	public static WindowManager getWindowManager(Context context) {
		if (wm == null)
			wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

		return wm;
	}

}
