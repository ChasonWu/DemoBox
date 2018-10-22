package org.demobox.menu.customizemenu;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.chason.demobox.R;

/**
 * 自定义菜单
 * @author Chason
 */
public class CustomizeMenu extends Dialog implements OnItemClickListener {

	private GridView mMenuGrid;  
	private Context mContext;
	private boolean mIsMore = true; //menu菜单翻页控制  
	
	//菜单图片  
    int[] menu_image_array1 = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,
    		R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,
    		R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,
    		R.drawable.ic_launcher_background, R.drawable.ic_launcher_background};
	//菜单文字
	String[] menu_name_array1 = { "搜索", "书签", "加入书签", "分享页面", "退出", "夜间模式", "刷新", "更多" };
    //菜单图片2   
    int[] menu_image_array2 = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,
    		R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,
    		R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,
    		R.drawable.ic_launcher_background, R.drawable.ic_launcher_background};
	//菜单文字2
	String[] menu_name_array2 = { "自动横屏", "检查更新", "检查网络", "定时刷新", "设置", "帮助", "关于", "返回" };
	
	public CustomizeMenu(Context context) {
		super(context);
		mContext = context;
	}
	
	public CustomizeMenu(Context context, int dialogStyle) {
		super(context, dialogStyle);
		mContext = context;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		setListener();
		setup();
	}

	/**
	 * 初始化View控件
	 */
	private void initView() {
		setContentView(R.layout.customize_menu);
		mMenuGrid = findViewById(R.id.gv_customize_menu);
	}

	/**
	 * 给控件设置监听器
	 */
	private void setListener() {
		mMenuGrid.setOnItemClickListener(this);
	}
	
	/**
	 * 属性设置
	 */
	private void setup() {
		mMenuGrid.setAdapter(getMenuAdapter(menu_name_array1, menu_image_array1));
		
		Window mWindow = getWindow();
		WindowManager.LayoutParams lp = mWindow.getAttributes();
		mWindow.setWindowAnimations(R.style.dialogAnim);
		mWindow.setAttributes(lp);
	}
	
	private SimpleAdapter getMenuAdapter(String[] menuNameArray, int[] imageResourceArray) {
		ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < menuNameArray.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemImage", imageResourceArray[i]);
			map.put("itemText", menuNameArray[i]);
			data.add(map);
		}

		SimpleAdapter simperAdapter = new SimpleAdapter(mContext, data, R.layout.item_customize_menu,
				new String[] { "itemImage", "itemText" },
				new int[] { R.id.iv_item_menu_icon, R.id.tv_item_menu_text });
		
		return simperAdapter;
	}
	
	/** 获取屏幕分辨率宽 */
	public static int getScreenWidth(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}
	
	/** 获取屏幕分辨率高 */
	public static int getScreenHeight(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (position) {
		case 0: //搜索,自动横屏
			dismiss();
			if (mIsMore)
				Toast.makeText(mContext, "点击了搜索", Toast.LENGTH_SHORT).show();
			else
				Toast.makeText(mContext, "点击了自动横屏", Toast.LENGTH_SHORT).show();
			break;
			
		case 1: //书签,检查更新
			dismiss();
			if (mIsMore)
				Toast.makeText(mContext, "点击了书签", Toast.LENGTH_SHORT).show();
			else
				Toast.makeText(mContext, "点击了检查更新", Toast.LENGTH_SHORT).show();
			break;
			
		case 2: //加入书签,检查网络
			dismiss();
			if (mIsMore)
				Toast.makeText(mContext, "点击了加入书签", Toast.LENGTH_SHORT).show();
			else
				Toast.makeText(mContext, "点击了检查网络", Toast.LENGTH_SHORT).show();
			break;
			
		case 3: //分享页面,定时刷新
			dismiss();
			if (mIsMore)
				Toast.makeText(mContext, "点击了分享页面", Toast.LENGTH_SHORT).show();
			else
				Toast.makeText(mContext, "点击了定时刷新", Toast.LENGTH_SHORT).show();
			break;
			
		case 4: //退出,设置
			dismiss();
			if (mIsMore)
				Toast.makeText(mContext, "点击了退出", Toast.LENGTH_SHORT).show();
			else
				Toast.makeText(mContext, "点击了设置", Toast.LENGTH_SHORT).show();
			break;
			
		case 5: //夜间模式,帮助
			dismiss();
			if (mIsMore)
				Toast.makeText(mContext, "点击了夜间模式", Toast.LENGTH_SHORT).show();
			else
				Toast.makeText(mContext, "点击了帮助", Toast.LENGTH_SHORT).show();
			break;
			
		case 6: //刷新,关于
			dismiss();
			if (mIsMore)
				Toast.makeText(mContext, "点击了刷新", Toast.LENGTH_SHORT).show();
			else
				Toast.makeText(mContext, "点击了关于", Toast.LENGTH_SHORT).show();
			break;

		case 7: //更多
			if (mIsMore) {
				mMenuGrid.setAdapter(getMenuAdapter(menu_name_array2, menu_image_array2));
				mIsMore = false;
			} else { //首页
				mMenuGrid.setAdapter(getMenuAdapter(menu_name_array1, menu_image_array1));
				mIsMore = true;
			}
			mMenuGrid.invalidate(); //更新menu
			mMenuGrid.setSelection(7);
			break;
		}
	}
	
}
