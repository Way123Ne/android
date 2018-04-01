package com.wayne.propertyanimationdemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
/*
 * 属性动画和渐变、补帧动画的区别：
 * 1>补帧动画特性： 用于生成连续的gif效果图
 * 2>渐变动画特性：只是显示的位置变动，View的实际位置未改变，表现为View移动到其他地方，点击事件仍在远处才能相应
 * 3>属性动画特性：api11以后出现的功能，更改的事View实际的属性，所以不会影响其在动画执行后所在位置的正常使用
 */
public class MainActivity extends Activity {
	private ImageView iv_animation;
	private Button btn_tween;
	private Button btn_property;
	private Button btn_reset;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		iv_animation = (ImageView) findViewById(R.id.iv_animation);
		btn_tween = (Button) findViewById(R.id.btn_tween);
		btn_property = (Button) findViewById(R.id.btn_property);
		btn_reset = (Button) findViewById(R.id.btn_reset);

		iv_animation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "点击了图片", Toast.LENGTH_SHORT)
						.show();
			}
		});

		btn_tween.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				testTweenAnimation(iv_animation);
			}
		});

		btn_property.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				testPropertyAnimation(iv_animation);
			}
		});

		btn_reset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				testResetTweenAnimation(iv_animation);
			}
		});

	}

	// 测试补间动画
	public void testTweenAnimation(View v) {
		TranslateAnimation animation = new TranslateAnimation(0,
				iv_animation.getWidth(), 0, iv_animation.getHeight());
		animation.setDuration(5000);
		animation.setFillAfter(true);
		v.startAnimation(animation);
	}

	// 测试属性动画
	private AnimatorSet animatorSet;

	public void testPropertyAnimation(View v) {
		// x轴上移动
		ObjectAnimator animator3 = ObjectAnimator.ofFloat(iv_animation,
				"translationX", 0, iv_animation.getWidth());
		// y轴上移动
		ObjectAnimator animator4 = ObjectAnimator.ofFloat(iv_animation,
				"translationY", 0, iv_animation.getHeight());
		AnimatorSet set = new AnimatorSet();
		set.playTogether(animator3, animator4);
		set.start();
	}

	// 重置补间动画
	public void testResetTweenAnimation(View v) {
		v.clearAnimation();
	}

}
