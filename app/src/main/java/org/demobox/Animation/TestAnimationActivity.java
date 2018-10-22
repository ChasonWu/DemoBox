package org.demobox.Animation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.chason.demobox.R;
import org.demobox.general.base.BaseActivity;

public class TestAnimationActivity extends BaseActivity implements View.OnClickListener {
	private Button mBtnViewAnim;
	private Button mBtnDrawableAnim;
	private Button mBtnPropertyAnim;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListener();
	}

	@Override
	protected void initWidget() {
		setContentView(R.layout.test_activity_animation);
		mBtnViewAnim = findViewById(R.id.btn_main_view_animation);
		mBtnDrawableAnim = findViewById(R.id.btn_main_drawable_animation);
		mBtnPropertyAnim = findViewById(R.id.btn_main_property_animation);
	}
	
	protected void setListener() {
		mBtnViewAnim.setOnClickListener(this);
		mBtnDrawableAnim.setOnClickListener(this);
		mBtnPropertyAnim.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		Intent intent = new Intent();

		switch (v.getId()) {
			case R.id.btn_main_view_animation:
				intent.setClass(this, ViewAnimation.class);
				break;

			case R.id.btn_main_drawable_animation:
				intent.setClass(this, DrawableAnimation.class);
				break;

			case R.id.btn_main_property_animation:
				intent.setClass(this, PropertyAnimation.class);
				break;

			default:break;
		}

		startActivity(intent);
	}

}
