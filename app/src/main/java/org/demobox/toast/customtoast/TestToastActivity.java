package org.demobox.toast.customtoast;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import org.chason.demobox.R;

public class TestToastActivity extends Activity {
	
	private Button mBtnShow;
	private Button mBtnHide;
	private ToastCustom toast;
	
	private int count;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_activity_toast);
		
		toast = new ToastCustom(getApplicationContext());
		
		mBtnShow = findViewById(R.id.btn_main_toast_show);
		mBtnShow.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				toast.show("this is my toast!!  " + count++);
			}
		});
		
		mBtnHide = findViewById(R.id.btn_main_toast_hide);
		mBtnHide.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				toast.hide();
				count = 0;
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		toast.hide();
	}

}
