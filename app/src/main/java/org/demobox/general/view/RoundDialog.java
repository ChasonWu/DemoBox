package org.demobox.general.view;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import org.chason.demobox.R;


/**
 * 圆角边框的对话框
 * @author Chason
 *
 */
public abstract class RoundDialog extends Activity {
	protected ImageView mGoBack;
	
	@Override
	public void onAttachedToWindow() {
		//获取屏幕高宽
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //获取窗体的DecorView，然后再获取LayoutParams
        View view = getWindow().getDecorView();
        //DecorView的LayoutParams为WindowManager.LayoutParams
        WindowManager.LayoutParams lp = (WindowManager.LayoutParams)view.getLayoutParams();
        lp.gravity = Gravity.CENTER;
        lp.width = (dm.widthPixels * 4) / 5;
        lp.height = (dm.widthPixels * 3) / 5;
        getWindowManager().updateViewLayout(view,lp);
        //直接调用PhoneWindow的setBackgroundDrawable接口设置成透明
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //DecorView设置背景
        view.setBackgroundResource(R.drawable.rounded_border_shape);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		setListeners();
		start();
		
		//设置点击空白区域时，反馈对话框不会消失
		setFinishOnTouchOutside(false);
	}

	/**
	 * 初始化所有控件
	 */
	protected abstract void initView();

	/**
	 * 给控件设置监听器
	 */
	protected abstract void setListeners();
	
	/**
	 * 添加Activity一初始化就要处理的数据
	 */
	protected void start() {}

}
