package com.wayne.notifytest;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class NotifyTestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);

	}

	/**
	 * 启动该activity后就应该清除通知标记
	 */

//	@Override
//	protected void onStart() {
//		// TODO Auto-generated method stub
//		super.onStart();
//
//		NotificationManager myNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//		myNotificationManager.cancel(0);
//	}
}
