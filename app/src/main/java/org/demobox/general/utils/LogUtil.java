package org.demobox.general.utils;

import android.util.Log;

/**
 * Log打印便捷封装
 * @author Chason Wu
 */
public class LogUtil {

	public static final int LEVEL_V = 0;
	public static final int LEVEL_D = 1;
	public static final int LEVEL_I = 2;
	public static final int LEVEL_W = 3;
	public static final int LEVEL_E = 4;
	private static boolean isEnable = true; // 是否开启日志打印
	public static String className; //Log输出所在类
	public static String methodName; //Log输出所在方法
	public static int lineNumber; //Log输出所行号

	private LogUtil() {}

	/**
	 * 取得log输出所在位置的信息
	 * 在方法调用的地方得到该方法的调用栈(StackTraceElement)，然后就可以得出调用此方法所在位置的类、方法、行号了。
	 * @param sElements
	 */
	public static void getMethodPlace(StackTraceElement[] sElements) {
		className = sElements[1].getFileName().split("\\.")[0];
		methodName = sElements[1].getMethodName();
		lineNumber = sElements[1].getLineNumber();
	}

	/**
	 * 创建Log输出的基本信息
	 * @param log
	 * @return
	 */
	private static String createLog(String log) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		buffer.append(methodName);
		buffer.append("()");
		buffer.append(" line:");
		buffer.append(lineNumber);
		buffer.append("] ");
		buffer.append(log);

		return buffer.toString();
	}

	/**
	 * 打印日志
	 * @param level
	 * @param msg
	 */
	public static void print(int level, String msg) {
		if (!isEnable)
			return;

		getMethodPlace(new Throwable().getStackTrace());

		switch (level) {
			case LEVEL_V: //打印所有信息
				Log.v(className, createLog(msg));
				break;

			case LEVEL_D: //打印debug调试信息
				Log.d(className, createLog(msg));
				break;

			case LEVEL_I: //打印info信息
				Log.i(className, createLog(msg));
				break;

			case LEVEL_W: //打印warning警告信息
				Log.w(className, createLog(msg));
				break;

			case LEVEL_E: //打印error错误信息
				Log.e(className, createLog(msg));
				break;

			default:
				Log.v(className, createLog(msg));
				break;
		}
	}

	/**
	 * 设置是否开启打印日志，默认开启
	 * @param isEnable
	 */
	public void isLogEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}
	
}
