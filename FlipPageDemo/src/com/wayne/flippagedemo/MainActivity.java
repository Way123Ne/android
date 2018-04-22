package com.wayne.flippagedemo;

import android.R.anim;
import android.app.Activity;
import android.content.Loader;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends Activity implements
		GestureDetector.OnGestureListener {
	private GestureDetector detector;
	private ViewFlipper flipper;
	private Animation animation[];
	final int MINIMUM_DISTANCE = 50;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		detector = new GestureDetector(this, this);

		flipper = (ViewFlipper) findViewById(R.id.flipper);

		//向flipper控件中添加图片
		flipper.addView(addImageView(R.drawable.ic_launcher));
		flipper.addView(addImageView(R.drawable.star_icon));
		flipper.addView(addImageView(R.drawable.scrubber_disabled));
		flipper.addView(addImageView(R.drawable.movie));

		loadAnimation();
	}

	//将Activity触摸返回的事件交给GestureDetector类进行处理
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return detector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * 单词标注：
	 * fling 伸缩
	 * motion 请求
	 * velocity 速率，速度
	 */
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		if(e2.getX() - e1.getX() > MINIMUM_DISTANCE){
			flipper.setInAnimation(animation[0]);
			flipper.setOutAnimation(animation[1]);
			flipper.showPrevious();
			return true;
		} else if(e1.getX() - e2.getX() > MINIMUM_DISTANCE){
			flipper.setInAnimation(animation[2]);
			flipper.setOutAnimation(animation[3]);
			flipper.showNext();
			return false;
		}
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	private ImageView addImageView(int resId) {
		ImageView imageView = new ImageView(this);
		imageView.setImageResource(resId);
		imageView.setScaleType(ImageView.ScaleType.CENTER);
		return imageView;

	}

	private Animation[] loadAnimation() {
		animation = new Animation[4];
		animation[0] = AnimationUtils.loadAnimation(this, R.anim.left_in);
		animation[1] = AnimationUtils.loadAnimation(this, R.anim.right_out);
		animation[2] = AnimationUtils.loadAnimation(this, R.anim.right_in);
		animation[3] = AnimationUtils.loadAnimation(this, R.anim.left_out);
		return animation;

	}
}
