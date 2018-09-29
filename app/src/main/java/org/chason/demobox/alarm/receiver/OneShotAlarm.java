package org.chason.demobox.alarm.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * 一次性闹钟
 * @author Chason
 */
public class OneShotAlarm extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "一次性闹钟响了", Toast.LENGTH_SHORT).show();
		Log.d("tag", "一次性闹钟响了");
	}

}
