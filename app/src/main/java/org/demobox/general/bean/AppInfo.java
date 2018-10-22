package org.demobox.general.bean;

import android.graphics.drawable.Drawable;

/**
 * App的各种信息
 * @author Chason Wu
 */
public class AppInfo {
	
	private String packageName; //应用的报名
	private String name; //应用的名称
	private Drawable icon; //应用的图标
	private long size; //应用的大小
	private boolean isInstallSD; //是否安装在SD卡
	private boolean isSystem; //是否是系统程序

	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Drawable getIcon() {
		return icon;
	}
	public void setIcon(Drawable icon) {
		this.icon = icon;
	}
	
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	
	public boolean isInstallSD() {
		return isInstallSD;
	}
	public void setInstallSD(boolean isInstallSD) {
		this.isInstallSD = isInstallSD;
	}
	
	public boolean isSystem() {
		return isSystem;
	}
	public void setSystem(boolean isSystem) {
		this.isSystem = isSystem;
	}
	
}
