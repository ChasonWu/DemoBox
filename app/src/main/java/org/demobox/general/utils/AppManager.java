package org.demobox.general.utils;

import java.util.Stack;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

/**
 * APP的Activity管理类，用于对Activity管理和退出整个APP
 * @author Chason Wu
 */
public class AppManager {
	private static Stack<Activity> activityStack;
	private static AppManager instance;

	private AppManager() {}

	/**
	 * 获取AppManager实例，UI无需考虑多线程同步问题
	 */
	public static AppManager getInstance() {
		if (instance == null) {
			instance = new AppManager();
		}
		return instance;
	}
	
	/**
	 * 获取当前Activity（栈顶Activity）
	 * @return
	 */
	public Activity currentActivity() {
		if (activityStack == null || activityStack.isEmpty())
			return null;
		
		Activity activity = activityStack.lastElement();
		return activity;
	}

	/**
	 * 添加Activity到栈
	 * @param activity
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}
	
	/**
	 * 查找指定Activity，没有找到则返回null
	 * @param clazz
	 * @return
	 */
	public Activity findActivity(Class<?> clazz) {
		Activity activity = null;
		for (Activity activitys : activityStack) {
			if (activitys.getClass().equals(clazz)) {
				activity = activitys;
				break;
			}
		}
		return activity;
	}
	
	/**
	 * 结束当前Activity（栈顶Activity）
	 */
	public void finishActivity() {
		Activity activity = activityStack.lastElement();
		finishActivity(activity);
	}

	/**
	 * 结束指定的Activity
	 * @param activity
	 */
	public void finishActivity(Activity activity) {
		if (activity != null) {
			activityStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}

	/**
	 * 结束指定的Activity
	 * @param clazz
	 */
	public void finishActivity(Class<?> clazz) {
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(clazz)) {
				finishActivity(activity);
			}
		}
	}

	/**
	 * 关闭除了指定Activity以外的全部Activity 如果class不存在于栈中，则栈全部清空
	 * @param clazz
	 */
	public void finishOthersActivity(Class<?> clazz) {
		for (Activity activity : activityStack) {
			if (!(activity.getClass().equals(clazz))) {
				finishActivity(activity);
			}
		}
	}

	/**
	 * 结束所有Activity
	 */
	public void finishAllActivity() {
		for (int index = 0, size = activityStack.size(); index < size; index++) {
			if (null != activityStack.get(index)) {
				activityStack.get(index).finish();
			}
		}
		activityStack.clear();
	}

	/**
	 * 退出整个APP
	 * 使用ActivityManager的killBackgroundProcesses方法,需要添加权限"android.permission.KILL_BACKGROUND_PROCESSES"
	 * @param context
	 */
	public void appExit(Context context) {
		try {
			finishAllActivity();
			ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			manager.killBackgroundProcesses(context.getPackageName());
			System.exit(0);
		} catch (Exception e) {
			System.exit(0);
		}
	}
	
}
