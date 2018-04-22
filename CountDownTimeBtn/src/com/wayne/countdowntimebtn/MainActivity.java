package com.wayne.countdowntimebtn;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/*
 * Timer、TimerTask实现倒计时效果
 */
public class MainActivity extends Activity {

	private Button btn_countdowntime;
	// 总时间
	private long totalTime;
	// 剩余时间
	private long remainingTime;
	// 定时器
	private Timer timer;
	// 定时器任务
	private TimerTask task;
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (remainingTime <= 0) {
				timer.cancel();
				task.cancel();
				//倒计时结束后重新设置btn_countdowntime控件的文本信息
				btn_countdowntime.setText("获取验证码");
				btn_countdowntime.setClickable(true);
				return;
			}
			btn_countdowntime.setText((remainingTime / 1000) + "秒后可重新发送");
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn_countdowntime = (Button) findViewById(R.id.btn_countdowntime);

		btn_countdowntime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				remainingTime = 10000;
				timer = new Timer();
				// 可单独提取出task类->继承TimerTask类->复写run() ==>下方注释代码
				task = new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						remainingTime = remainingTime - 1000;
						// 发送消息通知主线程更新UI
						handler.sendEmptyMessage(0x789);
					}
				};
				// 执行定时任务
				timer.schedule(task, 0, 1000);
				btn_countdowntime.setClickable(true);
			
			}
		});
	}
	
/*
	class Task extends TimerTask {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			remainingTime = remainingTime - 1000;
			// 发送消息通知主线程更新UI
			handler.sendEmptyMessage(0x789);
		}

	}*/
}
