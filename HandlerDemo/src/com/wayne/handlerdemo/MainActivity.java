package com.wayne.handlerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tv_1;
	private Handler handler = new Handler() {
		// 该方法运行在主线程中
		// 接收到handler发送的消息，对UI进行操作
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == 0x123) {
				tv_1.setText("呵呵。。。");
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tv_1 = (TextView) findViewById(R.id.tv_1);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//在此执行耗时操作，执行完毕后调用handler发送消息
				try {
					//睡眠5秒，模拟执行耗时任务
					Thread.sleep(5000);
					handler.sendEmptyMessage(0x123);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}).start();
	}
}
