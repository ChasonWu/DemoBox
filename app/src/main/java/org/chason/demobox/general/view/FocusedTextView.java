package org.chason.demobox.general.view;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 自定义TextView，复写个别方法，用于欺骗系统，让控件觉得自己是有焦点的，而不会停止走马灯效果。用于让控件一直拥有焦点。
 * @author ChasonWu Wu
 */
public class FocusedTextView extends TextView {

	/**
	 * 在代码中new的时候用
	 * @param context 上下文
	 */
	public FocusedTextView(Context context) {
		this(context, null);
	}

	/**
	 * 在xml布局文件中被使用
	 * @param context 上下文
	 * @param attrs 控件的属性值
	 */
	public FocusedTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setSingleLine();
		setEllipsize(TruncateAt.MARQUEE);
		setMarqueeRepeatLimit(-1);
		setFocusable(true);
		setFocusableInTouchMode(true);
	}
	
	/**
	 * 让两个TextView都能有走马灯效果
	 */
	@Override
	public boolean isFocused() {
		return true;
	}
	
	/**
	 * 让走马灯效果不会被EditText强占焦点而停止
	 */
	@Override
	protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
		if (focused)
			super.onFocusChanged(focused, direction, previouslyFocusedRect);
	}
	
	/**
	 * 让Activity窗体变化时，还能够有焦点
	 */
	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		if (hasWindowFocus)
			super.onWindowFocusChanged(hasWindowFocus);
	}

}
