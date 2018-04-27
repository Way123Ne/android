package com.demo.customviewclass;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.customviewdemo.R;

public class TitleView extends RelativeLayout {

	private Button left_btn;
	private TextView title_tv;

	public TitleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub

		// 加载布局
		LayoutInflater.from(context).inflate(R.layout.title_bar, this);

		// 获取控件
		left_btn = (Button) findViewById(R.id.left_btn);
		title_tv = (TextView) findViewById(R.id.title_tv);

	}
	
	//为左侧返回按钮添加自定义点击事件
	public void setLeftButtonListener(OnClickListener listener){
		left_btn.setOnClickListener(listener);
	}

	//设置标题的方法
	public void setTitleText(String title){
		title_tv.setText(title);
	}
}
