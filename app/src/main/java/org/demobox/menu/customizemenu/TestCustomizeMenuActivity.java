package org.demobox.menu.customizemenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import org.chason.demobox.R;

public class TestCustomizeMenuActivity extends Activity implements OnClickListener {
	
	private Button mShowMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		setListener();
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		setContentView(R.layout.test_activity_customize_menu);
		mShowMenu = findViewById(R.id.btn_main_show_menu);
	}

	/**
	 * 给控件设置监听器
	 */
	private void setListener() {
		mShowMenu.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_main_show_menu:
			CustomizeMenu customizeMenu = new CustomizeMenu(this, R.style.DialogStyle);
			customizeMenu.show();
			break;
		}
	}

}
