package com.demo.customviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.demo.customviewclass.TitleView;
/**
 * 
 * @author user
 *自定义控件实现方法一：组合控件
 *组合控件即将一些小的控件组合起来形成一个新的控件，这些小的控件多是系统自带的控件，比如很多应用中普遍使用的标题栏控件
 */
public class MainActivity extends Activity{
	
	private TitleView title_bar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		title_bar = (TitleView) findViewById(R.id.title_bar);
		title_bar.setLeftButtonListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "点击了返回按钮", Toast.LENGTH_SHORT).show();
				finish();
			}
		});
	}

	
}
