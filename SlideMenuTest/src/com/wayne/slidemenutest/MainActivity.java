package com.wayne.slidemenutest;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//new出SlidingMenu对象
		SlidingMenu menu = new SlidingMenu(this);
		//设置侧滑的方向-左侧
		menu.setMode(SlidingMenu.LEFT);
		//设置触摸屏幕的方式-全屏
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		
		//设置滑动完剩余的宽度
		menu.setBehindOffset(1100);
		//设置渐入渐出效果的值
		menu.setFadeDegree(0.35f);
		//绑定-将SlidingMenu绑定到Activity
		menu.attachToActivity(this, SlidingMenu.LEFT);
		//为左侧滑菜单设置布局
		menu.setMenu(R.layout.left_function_fragment);
	}
}
