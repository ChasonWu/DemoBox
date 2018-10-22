package org.demobox.general.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

import org.demobox.general.bean.AppInfo;

/**
 * 能获取Package所有信息的
 * @author Chason Wu
 */
public class PackageUtils {
	
	private static PackageManager manager;
	
	private static PackageManager getPackageManager(Context context) {
		if (manager == null)
			manager = context.getPackageManager();
		
		return manager;
	}
	
	/**
	 * 获取手机上所有的App的信息，并添加进一个List集合里返回
	 * @return
	 */
	public static List<AppInfo> getAllAppInfo(Context context) {
		getPackageManager(context);
		
		List<AppInfo> list = new ArrayList<AppInfo>();
		
		List<PackageInfo> installedPackages = manager.getInstalledPackages(0);
		for (PackageInfo packageInfo : installedPackages) {
			AppInfo appInfo = new AppInfo();
			
			appInfo.setPackageName(packageInfo.packageName);
			
			//获取清单文件application节点里的信息
			ApplicationInfo applicationInfo = packageInfo.applicationInfo;
			appInfo.setIcon(applicationInfo.loadIcon(manager));
			appInfo.setName(applicationInfo.loadLabel(manager).toString());
			
			String sourceDir = applicationInfo.sourceDir;
			appInfo.setSize(new File(sourceDir).length());
			
			int flags = applicationInfo.flags;
			
			//判断是否是系统应用
			if ((flags & ApplicationInfo.FLAG_SYSTEM) == ApplicationInfo.FLAG_SYSTEM) {
				appInfo.setSystem(true);
			} else {
				appInfo.setSystem(false);
			}
			
			//判断应用安装的位置
			if ((flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) == ApplicationInfo.FLAG_EXTERNAL_STORAGE) {
				appInfo.setInstallSD(true);
			} else {
				appInfo.setInstallSD(false);
			}
			
			list.add(appInfo);
		}
		
		return list;
	}

	/**
	 * 获得版本号信息
	 * @param context
	 * @return
	 */
	public static String getVersionName(Context context) {
		getPackageManager(context);

		try {
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
			return info.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 版本的代码
	 * @param context
	 * @return
	 */
	public static int getVersionCode(Context context) {
		getPackageManager(context);

		try {
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
			return info.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		return -1;
	}
}
