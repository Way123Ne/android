package com.wayne.splashactivitydemo;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SplashActivity extends Activity {
	private final int SPLASH_DISPLAY_LENGHT = 5000;
	private Handler handler;
	private TextView tv_countdowntime;
	private CountDownTimer timer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		tv_countdowntime = (TextView) findViewById(R.id.tv_countdowntime);

		tv_countdowntime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				timer.start();
				tv_countdowntime.setClickable(false);
			}
		});
		timer = new CountDownTimer(3000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				tv_countdowntime.setText("跳过" + (millisUntilFinished / 1000)
						+ "s");
			}

			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				tv_countdowntime.setClickable(true);
			}
		};

		handler = new Handler();
		// 延迟SPLASH_DISPLAY_LENGHT时间后跳转到MainActivity
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SplashActivity.this,
						MainActivity.class);
				startActivity(intent);
				SplashActivity.this.finish();
			}
		}, SPLASH_DISPLAY_LENGHT);
	}
}
