package org.demobox.general.utils;

import java.util.List;

import android.app.ActivityManager.RunningServiceInfo;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;

/**
 * Service状态判断工具类
 * @author Chason Wu
 */
public class ServiceStateUtils {

	/**
	 * 判断服务是否运行
	 * @param context
	 * @param clazz
	 * @return
	 */
	public static boolean isRunging(Context context, Class<? extends Service> clazz) {
		// 获取所有运行的Service
		List<RunningServiceInfo> services = SystemServiceUtils.getActivityManager(context).getRunningServices(Integer.MAX_VALUE);
		
		for (RunningServiceInfo serviceInfo : services) {
			ComponentName service = serviceInfo.service;
			String className = service.getClassName();
			
			// 如果运行的Service中有跟传递进来的Service匹配的，表示该Service已经在运行
			if (className.equals(clazz.getName()))
				return true;
		}
		
		return false;
	}
	
}
