package com.wayne.ontouchlistenerdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.Toast;
/*
 * 两种方法实现手势监听：
 * 1>class implements OnTouchListener interface -> override onTouch()
 * 2>view.setOnTouchListener() -> override onTouch()
 */

/*
 * 手势监听的种类：
 * 1>override onTouchEvent() -> 实现全局的手势监听
 * 2>implements onTouchListener interface -> override onTouch()-> 针对某一控件的手势监听
 */

public class MainActivity extends Activity {

	private Button btn_time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btn_time = (Button) findViewById(R.id.btn_time);

		btn_time.setOnTouchListener(new OnTouchListener() {

			/*
			 * (non-Javadoc)
			 * @see android.view.View.OnTouchListener#onTouch(android.view.View, android.view.MotionEvent)
			 * onTouch()参数说明：
			 * v:表示要触摸的对象
			 * event:表示触摸的状态
			 */
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					
					System.out.println("手指按下");
				}
				if (event.getAction() == MotionEvent.ACTION_MOVE) {
					
					System.out.println("手指移动");
				}
				if (event.getAction() == MotionEvent.ACTION_UP) {
					
					System.out.println("手指松开");
				}
				return true;
			}
		});
	}

}
