package com.wayne.broadcastdemo;

import android.app.Activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.wayne.broadcastreceiverdemo.Receiver_1;
import com.wayne.broadcastreceiverdemo.Receiver_2;

public class MainActivity extends Activity {
	private Button btn_static, btn_dynamic;
	private Receiver_1 receiver;
	private Receiver_2 receiver2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// new 出来静态注册的接收器
		receiver = new Receiver_1();
		// new 出来动态注册的接收器
		receiver2 = new Receiver_2();

		btn_static = (Button) findViewById(R.id.btn_static);
		btn_dynamic = (Button) findViewById(R.id.btn_dynamic);

		btn_static.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("key", "静态注册");
				intent.setAction("Action_1");
				// 发送广播
				sendBroadcast(intent);

			}
		});

		btn_dynamic.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent2 = new Intent();
				intent2.putExtra("key", "动态注册");
				intent2.setAction("Action_2");
				sendBroadcast(intent2);
			}
		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// 获取一个过滤器
		IntentFilter filter = new IntentFilter();
		filter.addAction("Action_2");
		// 注册
		registerReceiver(receiver, filter);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		// 解除注册
		unregisterReceiver(receiver);
	}

}
