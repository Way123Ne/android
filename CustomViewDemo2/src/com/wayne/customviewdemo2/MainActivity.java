package com.wayne.customviewdemo2;

import android.app.Activity;
import android.os.Bundle;
/**
 * 
 * @author user
 * 自定义控件实现方法二：自绘控件，即自绘控件的内容都是自己绘制出来的，在View的onDraw()中完成绘制
 * 下面就实现一个简单的计数器，每点击它一次，计数值就加1并显示出来。 
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
}
