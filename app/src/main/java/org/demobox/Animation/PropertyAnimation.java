package org.demobox.Animation;

import org.chason.demobox.R;
import org.demobox.general.base.BaseActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 属性动画(Property Animation)
 * @author Chason
 */
public class PropertyAnimation extends BaseActivity implements View.OnClickListener {
	private Button mBtnAnim1;
	private Button mBtnAnim2;
	private Button mBtnAnim3;
	private Button mBtnAnim4;
	private Button mBtnAnim5;
	private ImageView mIvImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListener();
	}

	@Override
	protected void initWidget() {
		setContentView(R.layout.activity_property_animation);
		mBtnAnim1 = findViewById(R.id.btn_propertyanim1);
		mBtnAnim2 = findViewById(R.id.btn_propertyanim2);
		mBtnAnim3 = findViewById(R.id.btn_propertyanim3);
		mBtnAnim4 = findViewById(R.id.btn_propertyanim4);
		mBtnAnim5 = findViewById(R.id.btn_propertyanim5);
		mIvImage = findViewById(R.id.iv_propertyanim_icon);
	}
	
	private void setListener() {
		mBtnAnim1.setOnClickListener(this);
		mBtnAnim2.setOnClickListener(this);
		mBtnAnim3.setOnClickListener(this);
		mBtnAnim4.setOnClickListener(this);
		mBtnAnim5.setOnClickListener(this);
		mIvImage.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_propertyanim1: // 位移动画
				// target:做动画的对象
				// propertyName:View中的setXxx名
				ObjectAnimator animation1 = ObjectAnimator.ofFloat(mIvImage, "translationY", 0, 250, 100, 50, 350);

				// 设置动画的时长
				animation1.setDuration(3000);

				// 设置动画插入器
				animation1.setInterpolator(new AnticipateOvershootInterpolator());

				animation1.start();
				break;

			case R.id.btn_propertyanim2: // 缩放动画
				ObjectAnimator animation2 = ObjectAnimator.ofFloat(mIvImage, "scaleX", 4);
				animation2.setDuration(3000);
				animation2.setInterpolator(new BounceInterpolator());
				animation2.start();
				break;

			case R.id.btn_propertyanim3: // 属性动画类型计算器：TypeEvaluator
				ObjectAnimator animation3 = ObjectAnimator.ofObject(mBtnAnim3, "backgroundColor", new ArgbEvaluator(), Color.RED, Color.YELLOW, Color.BLUE);
				animation3.setDuration(3000);
				animation3.setRepeatCount(ObjectAnimator.INFINITE); //播放次数无限
				animation3.setRepeatMode(ObjectAnimator.REVERSE); //播放模式：反转：start-->end-->end-->start
				animation3.start();
				break;

			case R.id.btn_propertyanim4: // 属性动画集合
				ObjectAnimator alpha = ObjectAnimator.ofFloat(mIvImage, "alpha", 0, 1, 0.5f, 1);
				ObjectAnimator translation = ObjectAnimator.ofFloat(mIvImage, "translationY", 0, 250, 100, 50, 350);
				ObjectAnimator scale = ObjectAnimator.ofFloat(mIvImage, "scaleX", 4);

				ObjectAnimator backgroundColor = ObjectAnimator.ofObject(mBtnAnim4, "backgroundColor", new ArgbEvaluator(), Color.RED, Color.YELLOW, Color.BLUE);
				backgroundColor.setRepeatCount(ObjectAnimator.INFINITE); //播放次数无限
				backgroundColor.setRepeatMode(ObjectAnimator.REVERSE); //播放模式：反转：start-->end-->end-->start

				AnimatorSet set = new AnimatorSet();
//				set.playSequentially(translation, scale, backgroundColor); //左边参数开始按参数顺序播放

//				set.playTogether(translation, scale, backgroundColor); //所有参数同步播放

				//播放位移动画前和播放后执行的动画
				set.play(translation).before(scale);
				set.play(translation).after(alpha);
				set.play(translation).before(backgroundColor);

				set.setDuration(3000);
				set.start();
				break;

			case R.id.btn_propertyanim5: // 属性动画xml调用
				Animator loadAnimator = AnimatorInflater.loadAnimator(this, R.animator.animator_set);
				loadAnimator.setTarget(mIvImage);
				loadAnimator.start();
				break;

			case R.id.iv_propertyanim_icon: // 点图片
				Toast.makeText(this, "点击了图片", Toast.LENGTH_SHORT).show();
				break;

			default:break;
		}
	}

}
