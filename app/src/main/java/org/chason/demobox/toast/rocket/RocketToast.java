package org.chason.demobox.toast.rocket;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.AnimationDrawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.ImageView;

import org.chason.demobox.R;
import org.chason.demobox.general.utils.SystemServiceUtils;

/**
 * 自定义Toast
 * @author Chason Wu
 */
public class RocketToast implements OnTouchListener {
	
	private Context mContext;
	private ImageView mIvRocket;
	private ImageView mIvTip;
	
	private WindowManager mWn;
	private WindowManager.LayoutParams mRocketParams;
	private WindowManager.LayoutParams mTipParams;
	
	private boolean isReady = false;
	
	public RocketToast(Context context) {
		mContext = context;
		mWn = SystemServiceUtils.getWindowManager(mContext);
		
		//火箭参数
		mRocketParams = new WindowManager.LayoutParams();
		mRocketParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
		mRocketParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
		mRocketParams.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
				| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
		mRocketParams.gravity = Gravity.LEFT | Gravity.TOP;
		mRocketParams.format = PixelFormat.TRANSLUCENT;
		mRocketParams.type = WindowManager.LayoutParams.TYPE_TOAST;
		
		//提示参数
		mTipParams = new WindowManager.LayoutParams();
		mTipParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
		mTipParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
		mTipParams.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
				| WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
				| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
		mTipParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
		mTipParams.format = PixelFormat.TRANSLUCENT;
		mTipParams.type = WindowManager.LayoutParams.TYPE_TOAST;
	}
	
	public void showRocket() {
		mIvRocket = new ImageView(mContext);
		mIvRocket.setOnTouchListener(this);
		
		AnimationDrawable bg = new AnimationDrawable();
		bg.addFrame(mContext.getResources().getDrawable(R.drawable.desktop_rocket_launch_1), 50);
		bg.addFrame(mContext.getResources().getDrawable(R.drawable.desktop_rocket_launch_2), 50);
		bg.setOneShot(false);
		
		mIvRocket.setImageDrawable(bg);
		bg.start();
		
		mWn.addView(mIvRocket, mRocketParams);
	}
	
	private void showTip() {
		hideTip();
		
		mIvTip = new ImageView(mContext);
		setTipState(false);
		mWn.addView(mIvTip, mTipParams);
	}
	
	private void setTipState(boolean isReady) {
		if (isReady) {
			mIvTip.setImageResource(R.drawable.desktop_bg_tips_3);
		} else {
			AnimationDrawable bg = new AnimationDrawable();
			bg.addFrame(mContext.getResources().getDrawable(R.drawable.desktop_bg_tips_1), 50);
			bg.addFrame(mContext.getResources().getDrawable(R.drawable.desktop_bg_tips_2), 50);
			bg.setOneShot(false);
			
			mIvTip.setImageDrawable(bg);
			bg.start();
		}
	}
	
	public void hideRocket() {
		if (mIvRocket != null) {
			if (mIvRocket.getParent() != null) {
				mWn.removeView(mIvRocket);
			}
			
			mIvRocket = null;
		}
	}
	
	private void hideTip() {
		if (mIvTip != null) {
			if (mIvTip.getParent() != null) {
				mWn.removeView(mIvTip);
			}
			
			mIvTip = null;
		}
	}
	
	private void offsetRocket(float diffX, float diffY) {
		mRocketParams.x += diffX + 0.5f;
		mRocketParams.y += diffY + 0.5f;
		mWn.updateViewLayout(mIvRocket, mRocketParams);
	}

	private boolean checkReady() {
		//获取火箭在屏幕中的位置
		int[] rocketLocation = new int[2];
		mIvRocket.getLocationOnScreen(rocketLocation);
		int rocketX = rocketLocation[0];
		int rocketY = rocketLocation[1];

		//获取提示在屏幕中的位置
		int[] tipLocation = new int[2];
		mIvTip.getLocationOnScreen(tipLocation);
		int tipX = tipLocation[0];
		int tipY = tipLocation[1];

		//判断火箭是否有一半进入提示框
		int rocketWidth = mIvRocket.getWidth();
		int rocketHeight = mIvRocket.getHeight();
		boolean isLeftX = (rocketWidth / 2f + rocketX) > tipX;
		boolean isRightX = (rocketWidth / 2f + rocketX) < (tipX + mIvTip.getWidth());
		boolean isY = (rocketHeight / 2f + rocketY) > tipY;

		if (isLeftX && isRightX && isY)
			return true;

		return false;
	}

	private float startX;
	private float startY;
	@Override
	public boolean onTouch(View v, MotionEvent event) {

		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				showTip();

				startX = event.getRawX();
				startY = event.getRawY();
				break;

			case MotionEvent.ACTION_MOVE:
				float newX = event.getRawX();
				float newY = event.getRawY();

				float diffX = newX - startX;
				float diffY = newY - startY;

				if (checkReady()) {
					setTipState(true);
					isReady = true;
				} else {
					setTipState(false);
					isReady = false;
				}

				offsetRocket(diffX, diffY);

				startX = newX;
				startY = newY;
				break;

			case MotionEvent.ACTION_UP:
				if (isReady) {
					//火箭发射的位置
					DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
					int screenWidth = metrics.widthPixels;
					int screenHeight = metrics.heightPixels;

					int rocketWidth = mIvRocket.getWidth();
					int rocketHeight = mIvRocket.getHeight();

					float rocketX = screenWidth / 2f - rocketWidth / 2f;
					float rocketY = screenHeight - rocketHeight;
					mRocketParams.x = (int) (rocketX + 0.5f);
					mRocketParams.y = (int) (rocketY + 0.5f);
					mWn.updateViewLayout(mIvRocket, mRocketParams);

					//发射动画
					ValueAnimator animator = ValueAnimator.ofInt(mRocketParams.y, 0);
					animator.addUpdateListener(new AnimatorUpdateListener() {
						@Override
						public void onAnimationUpdate(ValueAnimator animation) {
							int value = (Integer) animation.getAnimatedValue();
							mRocketParams.y = value;
							mWn.updateViewLayout(mIvRocket, mRocketParams);
						}
					});
					animator.setDuration(1000);
					animator.start();

					//烟雾动画
					Intent intent = new Intent(mContext, SmokeActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					mContext.startActivity(intent);
				}

				hideTip();
				break;

			default:break;
		}

		return true;
	}

}
