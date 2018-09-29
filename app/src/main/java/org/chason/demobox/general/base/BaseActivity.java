package org.chason.demobox.general.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

import org.chason.demobox.general.utils.AppManager;

/**
 * Activity的基类
 * @author Chason Wu
 */
public abstract class BaseActivity extends Activity {
	private static final String TAG = "BaseActivity";
	
    /**
     * 是否全屏，默认值是true全屏
     */
    private boolean isFullScreen = true;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		Logger.d(TAG, this.getClass() + "-----onCreate ");

		// 竖屏锁定
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// 全屏设置
		if (isFullScreen)
			requestWindowFeature(Window.FEATURE_NO_TITLE); // 取消标题

		AppManager.getInstance().addActivity(this);

		initWidget();
		initAdapter();
		initData();
	}
 
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Logger.d(TAG, this.getClass() + "-----onDestroy ");
        AppManager.getInstance().finishActivity(this);
    }
    
    /**
     * initialize view widget
     */
    protected abstract void initWidget();
    
    /**
     * initialize widget adapter
     */
    protected void initAdapter() {}
    
    /**
     * initialize widget data
     */
    protected void initData() {}
    
    /**
     * 全屏设置
     * @param isFullScreen
     */
    public void setFullScreen(boolean isFullScreen) {
    	this.isFullScreen = isFullScreen;
    }

}
