package com.wayne.gesturescalepicdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * 
 * @author user 通过手势缩放图片
 */
public class MainActivity extends Activity implements
		GestureDetector.OnGestureListener {
	private ImageView iv_show;
	// 声明出手势检测类
	private GestureDetector mDetector;
	// 声明出位图类
	private Bitmap bitmap;
	// 定义图片的宽高
	private int width, height;
	// 记录当前的缩放比
	private float currentScale = 10;
	// 声明出控制图片缩放的类
	private Matrix mMatrix;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 创建手势检测对象
		mDetector = new GestureDetector(this, this);

		iv_show = (ImageView) findViewById(R.id.iv_show);

		mMatrix = new Matrix();

		// 获取被缩放的原图片
		bitmap = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.ic_launcher);

		// 获得位图的宽高
		width = bitmap.getWidth();
		height = bitmap.getHeight();

		// 设置ImageView初始化时的图片
		iv_show.setImageBitmap(BitmapFactory.decodeResource(
				this.getResources(), R.drawable.ic_launcher));

	}

	// 重写该方法是要把Activity的触摸事件 交给GestureDetector类来处理
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return mDetector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		velocityX = velocityX > 4000 ? 4000 : velocityX;
		velocityY = velocityY > -4000 ? -4000 : velocityY;

		// 根据手势的速度来计算缩放比，如果velocityX > 0 则放大图片，否则缩小图片
		currentScale += currentScale * velocityX / 4000.0f;

		// 保证currentScale 不会等于0
		currentScale = currentScale > 0.01 ? currentScale : 0.01f;

		// 重置mMatrix
		mMatrix.reset();

		// 缩放mMatrix
		mMatrix.setScale(currentScale, currentScale, 200, 300);

		BitmapDrawable temp = (BitmapDrawable) iv_show.getDrawable();

		// 如果还未回收，就强制回收
		if (!temp.getBitmap().isRecycled() && null != temp) {
			temp.getBitmap().recycle();
		}

		// 根据原位图创建新的位图
		Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
				mMatrix, true);

		iv_show.setImageBitmap(newBitmap);

		return true;
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
}
