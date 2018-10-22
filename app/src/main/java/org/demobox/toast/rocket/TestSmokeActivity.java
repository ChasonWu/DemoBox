package org.demobox.toast.rocket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import org.chason.demobox.R;

public class TestSmokeActivity extends Activity implements OnClickListener {
	
	private Button mBtnRun;
	private Button mBtnStop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initWidget();
		setListener();
	}

	private void initWidget() {
		setContentView(R.layout.test_activity_rocket);
		mBtnRun = findViewById(R.id.btn_main_run);
		mBtnStop = findViewById(R.id.btn_main_stop);
	}
	
	private void setListener() {
		mBtnRun.setOnClickListener(this);
		mBtnStop.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_main_run:
			startService(new Intent(this, RocketService.class));
			break;
			
		case R.id.btn_main_stop:
			stopService(new Intent(this, RocketService.class));
			break;
		}
	}
	
}
