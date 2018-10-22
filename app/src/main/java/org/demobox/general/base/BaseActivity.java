package org.demobox.general.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

import org.demobox.general.utils.AppManager;

/**
 * Activity的基类
 * @author Chason Wu
 */
public abstract class BaseActivity extends Activity {

    /**
     * 是否全屏，默认值是true全屏
     */
    private boolean isFullScreen = true;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//        LogUtil.getMethodPlace(new Throwable().getStackTrace());
//        LogUtil.print(LogUtil.LEVEL_E, "BaseActivity onCreate的调用位置："+LogUtil.className+"--"+LogUtil.methodName+"--"+LogUtil.lineNumber);

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
