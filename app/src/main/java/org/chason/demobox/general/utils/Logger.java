package org.chason.demobox.general.utils;

import android.util.Log;

/**
 * Log打印便捷util
 * @author Chason Wu
 */
public class Logger {

	public static final int LEVEL_V = 0;
	public static final int LEVEL_D = 1;
	public static final int LEVEL_I = 2;
	public static final int LEVEL_W = 3;
	public static final int LEVEL_E = 4;
	private static int logLevel = LEVEL_V; // 日志级别
	private static boolean isEnable = true; // 日志是否打印
	
	private Logger() {}

	/**
	 * 打印所有信息
	 * @param tag
	 * @param msg
	 */
	public static void v(String tag, String msg) {
		if (!isEnable)
			return;

		if (logLevel <= LEVEL_V)
			Log.v(tag, msg);
	}

	/**
	 * 打印debug调试信息
	 * @param tag
	 * @param msg
	 */
	public static void d(String tag, String msg) {
		if (!isEnable)
			return;

		if (logLevel <= LEVEL_D)
			Log.d(tag, msg);
	}

	/**
	 * 打印info信息
	 * @param tag
	 * @param msg
	 */
	public static void i(String tag, String msg) {
		if (!isEnable)
			return;

		if (logLevel <= LEVEL_I)
			Log.i(tag, msg);
	}

	/**
	 * 打印warning警告信息
	 * @param tag
	 * @param msg
	 */
	public static void w(String tag, String msg) {
		if (!isEnable)
			return;

		if (logLevel <= LEVEL_W)
			Log.w(tag, msg);
	}
	
	/**
	 * 打印error错误信息
	 * @param tag
	 * @param msg
	 */
	public static void e(String tag, String msg) {
		if (!isEnable)
			return;
		
		if (logLevel <= LEVEL_E)
			Log.e(tag, msg);
	}
	
	/**
	 * 设置打印的日志信息级别，默认等级是LEVEL_V
	 * @param level
	 */
	public void setLogLevel(int level) {
		logLevel = level;
	}
	
	/**
	 * 设置是否打印日志信息，默认打印
	 * @param flag
	 */
	public void isLogPrint(boolean isPrint) {
		isEnable = isPrint;
	}
	
}
