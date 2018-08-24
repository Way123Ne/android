package com.wayne.eventbustest;

import com.wayne.event.FirstEvent;

import de.greenrobot.event.EventBus;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button btn_try;
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//注册EventBus
		EventBus.getDefault().register(this);
		
		initView();
		initEvent();
	}

	public void initEvent() {
		// TODO Auto-generated method stub
		btn_try.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(getApplicationContext(),SecondActivity.class);
				startActivity(it);
			}
		});
	}

	public void initView() {
		// TODO Auto-generated method stub
		btn_try = (Button) findViewById(R.id.btn_try);
		tv = (TextView) findViewById(R.id.tv);
	}
	
	//接收SecondActivity发送过来的消息
	public void onEventMainThread(FirstEvent event) {
		// TODO Auto-generated method stub
		String msg = "onEventMainThread收到了消息:"+event.getMsg();
		System.out.println("songweiqi>>>>>>>>>"+msg);
		tv.setText(msg);
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}
	
	
}
