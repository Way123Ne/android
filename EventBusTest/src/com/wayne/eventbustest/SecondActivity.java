package com.wayne.eventbustest;

import com.wayne.event.FirstEvent;

import de.greenrobot.event.EventBus;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondActivity extends Activity{
	private Button btn_first_event;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		initView();
		initEvent();
	}
	public void initEvent() {
		// TODO Auto-generated method stub
		btn_first_event.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//通过EventBus的post方法将消息发送给MainActivity
				EventBus.getDefault().post(new FirstEvent("FirstEvent btn clicked"));
			}
		});
	}
	public void initView() {
		// TODO Auto-generated method stub
		btn_first_event= (Button) findViewById(R.id.btn_first_event);
	}
	

}
