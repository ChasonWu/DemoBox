package org.chason.demobox.toast.rocket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import org.chason.demobox.toast.customtoast.ToastCustom;

public class RocketService extends Service {

	RocketToast launch;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		launch = new RocketToast(getApplicationContext());
		launch.showRocket();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		launch.hideRocket();
	}

}
