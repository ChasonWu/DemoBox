package org.demobox.toast.customtoast;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.TextView;

import org.chason.demobox.R;
import org.demobox.general.utils.SystemServiceUtils;

/**
 * 自定义Toast
 * @author Chason Wu
 */
public class ToastCustom implements OnTouchListener {
	
	private Context mContext;
	private View mView;
	
	private WindowManager mWn;
	private WindowManager.LayoutParams mParams;
	
	public ToastCustom(Context context) {
		mContext = context;
		mWn = SystemServiceUtils.getWindowManager(mContext);
		
		mParams = new WindowManager.LayoutParams();
		mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
		mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
		mParams.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
//				| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
				| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
		mParams.format = PixelFormat.TRANSLUCENT;
		mParams.type = WindowManager.LayoutParams.TYPE_TOAST;
	}

	public void show(String text) {
		if (mView != null) {
			hide();
		}
		
		mView = View.inflate(mContext, R.layout.toast_custom_layout, null);
		TextView tv = mView.findViewById(R.id.tv_toast_msg);
		tv.setText(text);
		
		mView.setOnTouchListener(this);
		
		mWn.addView(mView, mParams);
	}
	
	public void hide() {
		if (mView != null) {
			if (mView.getParent() != null) {
				mWn.removeView(mView);
			}
			
			mView = null;
		}
	}

	private float startX;
	private float startY;
	@Override
	public boolean onTouch(View v, MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startX = event.getRawX();
			startY = event.getRawY();
			break;

		case MotionEvent.ACTION_MOVE:
			float newX = event.getRawX();
			float newY = event.getRawY();
			
			float diffX = newX - startX;
			float diffY = newY - startY;
			
			mParams.x += (int) (diffX + 0.5f);
			mParams.y += (int) (diffY + 0.5f);
			
			mWn.updateViewLayout(mView, mParams);
			
			startX = newX;
			startY = newY;
			break;

		case MotionEvent.ACTION_UP:
			break;

		default:break;
		}

		return true;
	}
	
}
