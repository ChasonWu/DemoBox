package org.chason.demobox.alarm.activity;

import java.util.Calendar;

import org.chason.demobox.R;
import org.chason.demobox.alarm.receiver.OneShotAlarm;
import org.chason.demobox.alarm.receiver.RepeatingAlarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AlarmControllerActivity extends Activity implements OnClickListener {
	private Button mBtnOneShot;
	private Button mBtnRepeating;
	private Button mBtnCancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		setListener();
	}

	private void initView() {
		setContentView(R.layout.activity_alarmcontroller);
		mBtnOneShot = findViewById(R.id.btn_alarmcontroler_oneshot);
		mBtnRepeating = findViewById(R.id.btn_alarmcontroler_repeating);
		mBtnCancel = findViewById(R.id.btn_alarmcontroler_cancel);
	}
	
	private void setListener() {
		mBtnOneShot.setOnClickListener(this);
		mBtnRepeating.setOnClickListener(this);
		mBtnCancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		//一次性闹钟
		case R.id.btn_alarmcontroler_oneshot:
			// When the alarm goes off, we want to broadcast an Intent to our BroadcastReceiver. 
			// Here we make an Intent with an explicit class name to have our own receiver (which has been published in AndroidManifest.xml)
			// instantiated and called, and then create an IntentSender to have the intent executed as a broadcast.
			//当警报响起时，我们想向广播接收器广播一个意图。
			//在这里，我们使用一个显式的类名来创建自己的接收器(已在AndroidManifest.xml中发布)
			//实例化并调用，然后创建IntentSender以将意图作为广播执行。
			Intent intent1 = new Intent(AlarmControllerActivity.this, OneShotAlarm.class);
			PendingIntent sender1 = PendingIntent.getBroadcast(AlarmControllerActivity.this, 0, intent1, PendingIntent.FLAG_ONE_SHOT);
			
			// We want the alarm to go off 10 seconds from now.
			//我们希望10秒后警报器能响起来。
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTimeInMillis(System.currentTimeMillis());
			calendar1.add(Calendar.SECOND, 4);
			
			// Schedule the alarm!
			//安排闹钟!
			AlarmManager am1 = (AlarmManager) getSystemService(ALARM_SERVICE);
			am1.set(AlarmManager.RTC_WAKEUP, calendar1.getTimeInMillis(), sender1);
			break;
			
		//重复闹钟
		case R.id.btn_alarmcontroler_repeating:
			// When the alarm goes off, we want to broadcast an Intent to our BroadcastReceiver. 
			// Here we make an Intent with an explicit class name to have our own receiver (which has been published in AndroidManifest.xml)
			// instantiated and called, and then create an IntentSender to have the intent executed as a broadcast.
            // Note that unlike above, this IntentSender is configured to allow itself to be sent multiple times.
			//当警报响起时，我们想向广播接收器广播一个意图。
			//在这里，我们使用一个显式的类名来创建自己的接收器(已在AndroidManifest.xml中发布)
			//实例化并调用，然后创建IntentSender以将意图作为广播执行。
			//注意，与上面不同，IntentSender被配置为允许自己被多次发送。
            Intent intent2 = new Intent(AlarmControllerActivity.this, RepeatingAlarm.class);
            PendingIntent sender2 = PendingIntent.getBroadcast(AlarmControllerActivity.this, 1, intent2, PendingIntent.FLAG_UPDATE_CURRENT);

            // We want the alarm to go off 10 seconds from now.
			//我们希望10秒后警报器能响起来。
//            Calendar calendar2 = Calendar.getInstance();
//            calendar2.setTimeInMillis(System.currentTimeMillis());
//            calendar2.add(Calendar.SECOND, 4);
            long firstTime = SystemClock.elapsedRealtime();  
            firstTime += 4000;
            
            // Schedule the alarm!
			//安排闹钟!
            AlarmManager am2 = (AlarmManager) getSystemService(ALARM_SERVICE);
            am2.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, 3 * 1000, sender2);
//            am2.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, calendar2.getTimeInMillis(), 4 * 1000, sender2);
			break;
			
		//取消闹钟
		case R.id.btn_alarmcontroler_cancel:
			// Create the same intent, and thus a matching IntentSender, for
            // the one that was scheduled.
			//创建相同的意图，从而匹配IntentSender
			//预定的那个。
            Intent intent3 = new Intent(AlarmControllerActivity.this, RepeatingAlarm.class);
            PendingIntent sender3 = PendingIntent.getBroadcast(AlarmControllerActivity.this, 1, intent3, PendingIntent.FLAG_UPDATE_CURRENT);

            // And cancel the alarm.
			//取消闹钟。
            AlarmManager am3 = (AlarmManager) getSystemService(ALARM_SERVICE);
            am3.cancel(sender3);
			break;
		}
	}

}
