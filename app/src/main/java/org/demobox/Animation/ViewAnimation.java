package org.demobox.Animation;

import org.chason.demobox.R;
import org.demobox.general.base.BaseActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 补间动画(View Animation)
 * @author Chason
 */
public class ViewAnimation extends BaseActivity implements View.OnClickListener {
	private Button mBtnAnim1;
	private Button mBtnAnim2;
	private ImageView mIvImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListener();
	}

	@Override
	protected void initWidget() {
		setContentView(R.layout.activity_view_animation);
		mBtnAnim1 = findViewById(R.id.btn_viewanim1);
		mBtnAnim2 = findViewById(R.id.btn_viewanim2);
		mIvImage = findViewById(R.id.iv_viewanim_icon);
	}
	
	protected void setListener() {
		mBtnAnim1.setOnClickListener(this);
		mBtnAnim2.setOnClickListener(this);
		mIvImage.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_viewanim1: // 位移动画
				TranslateAnimation animation1 = new TranslateAnimation(0, 0, 0, 350);

				// 设置动画的时长
				animation1.setDuration(3000);

				//设置动画插入器
				animation1.setInterpolator(new AnticipateOvershootInterpolator());

				// 停在结果位置
				animation1.setFillAfter(true);

				mIvImage.startAnimation(animation1);
				break;

			case R.id.btn_viewanim2: // 缩放动画
				ScaleAnimation animation2 = new ScaleAnimation(0, 4, 0, 4);
				animation2.setDuration(3000);
				animation2.setInterpolator(new BounceInterpolator());
				animation2.setFillAfter(true);
				mIvImage.startAnimation(animation2);
				break;

			case R.id.iv_viewanim_icon: // 点图片
				Toast.makeText(this, "点击了图片", Toast.LENGTH_SHORT).show();
				break;

			default:break;
		}
	}

}
