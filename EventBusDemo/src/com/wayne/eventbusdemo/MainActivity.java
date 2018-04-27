package com.wayne.eventbusdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * 
 * @author user
 * 两个fragment互相通信：
 * 左侧fragment为发送者，右侧fragment为接收者
 */
/*
 * 注意：EventBus.jar 包版本
 */
public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

}
