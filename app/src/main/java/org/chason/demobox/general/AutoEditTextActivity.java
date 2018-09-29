package org.chason.demobox.general;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.chason.demobox.R;

/**
 * 监听EditText内容变化的实现Demo
 * @author Chason
 */
public class AutoEditTextActivity extends Activity implements TextWatcher {
	private static final String TAG = "tag";
	
	private EditText mInputContent;
	private TextView mShowContent;
	private TextView mCountTip1;
	private TextView mCountTip2;
	
	private int mMaxLength = 255;
	private int mCount = 0;

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
		setContentView(R.layout.activity_autoedittext);
		mInputContent = findViewById(R.id.et_main_auto);
		mShowContent = findViewById(R.id.tv_main_show_content);
		mCountTip1 = findViewById(R.id.tv_main_count_tip1);
		mCountTip2 = findViewById(R.id.tv_main_count_tip2);
	}

	/**
	 * 给控件设置监听器
	 */
	private void setListener() {
		// 给EditText添加Listener:addTextChangedListener
		mInputContent.addTextChangedListener(this);
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		// EditText改变之前的文字内容
		// start:添加内容的开始的位置
		// count:被改变的旧内容数(其实就是删除的内容数量)
		// after:新增内容数
		// 这里的s表示改变之前的内容,通常start和count组合,可以在s中读取本次改变字段中被改变的内容,而after表示改变后新的内容的数量。
		Log.d(TAG, "beforeTextChanged:s---"+s.toString());
		Log.d(TAG, "beforeTextChanged:start---"+start);
		Log.d(TAG, "beforeTextChanged:count---"+count);
		Log.d(TAG, "beforeTextChanged:after---"+after);
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// EditText改变时的文字内容
		// start:添加内容的开始的位置
		// before:更改了之前输入的所有内容的数量(其实就是删除的内容数量)
		// count:新增内容数
		// 这里的s表示改变之后的内容,通常start和count组合,可以在s中读取本次改变字段中新的内容,而before表示被改变的内容的数量。
		Log.d(TAG, "onTextChanged:s---"+s.toString());
		Log.d(TAG, "onTextChanged:start---"+start);
		Log.d(TAG, "onTextChanged:before---"+before);
		Log.d(TAG, "onTextChanged:count---"+count);
		
		mCount = mCount - before + count;
		mCountTip1.setText("已经输入了" + (mCount) + "个字");
		mCountTip2.setText("还可以输入" + (mMaxLength - mCount) + "个字");
		mShowContent.setText(s);
		if (mCount == mMaxLength) {
			mShowContent.setTextColor(Color.RED);
		}
	}

	@Override
	public void afterTextChanged(Editable s) {
		// EditText改变之后的文字内容,表示最终内容
		Log.d(TAG, "afterTextChanged:s---"+s.toString());
	}

}
