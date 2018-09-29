package org.chason.demobox.alarm.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * 重复闹钟
 * @author Chason
 */
public class RepeatingAlarm extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "重复闹钟响了", Toast.LENGTH_SHORT).show();
		Log.d("tag", "重复闹钟响了");
	}

}
