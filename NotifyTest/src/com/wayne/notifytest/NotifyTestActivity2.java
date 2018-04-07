package com.wayne.notifytest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class NotifyTestActivity2 extends Activity {

	private Button btn_1, btn_2;
	private TextView textView1;
	// 声明消息管理器对象
	NotificationManager mNotificationManager = null;
	Intent mIntent = null;
	// 声明mPendingIntent对象
	PendingIntent mPendingIntent = null;
	// 声明Notification对象
	Notification mNotification = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 初始化NotificationManager对象
		mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		btn_1 = (Button) findViewById(R.id.btn_1);
		btn_2 = (Button) findViewById(R.id.btn_2);

		// 当点击的时候转移内容
		mIntent = new Intent(NotifyTestActivity2.this, NotifyTestActivity.class);

		// 设置点击时候显示内容的类
		mPendingIntent = PendingIntent.getActivity(NotifyTestActivity2.this, 0,
				mIntent, 0);

		// 构造Notification对象
		mNotification = new Notification();

		btn_1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 设置通知在状态栏显示的图标
				mNotification.icon = R.drawable.ic_launcher;
				// 当我们点击通知时显示的内容
				mNotification.tickerText = "来新的通知了~~~";
				// 通知时发出的声音
				mNotification.defaults = Notification.DEFAULT_SOUND;
				// 设置通知显示的参数
				mNotification.setLatestEventInfo(NotifyTestActivity2.this,
						"btn_1", "btn_1通知进行中", mPendingIntent);

				// 执行这个通知事件的跳转
				mNotificationManager.notify(0, mNotification);

			}
		});
		

		/**
		 * 清除通知，mNotificationManager.cancel(0)的参数0是mNotificationManager.notify(0
		 * ,mNotification)里的第一个参数，也就是notify的id,这个在系统里是唯一的。
		 * 这里是做测试用的，在系统中应该是点击了通知之后该通知图标就消失了
		 * 。可以看NotifytestActivity中的onStart()中的处理方法
		 */
		btn_2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mNotificationManager.cancel(0);
				
			}
		});
	}

}
