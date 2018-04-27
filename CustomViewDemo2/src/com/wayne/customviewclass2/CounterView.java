package com.wayne.customviewclass2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;

public class CounterView extends View implements OnClickListener {

	private Paint mPaint;
	// 用于获取文字的宽和高
	private Rect mBounds;
	// 计数器，每点击一次本控件，其值增加1
	private int mCount;

	public CounterView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		//初始化画笔
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		//初始化Rect
		mBounds = new Rect();
		//本控件的点击事件
		setOnClickListener(this);
	}

	
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		mPaint.setColor(Color.YELLOW);
		mPaint.setTextSize(40);
		String text = String.valueOf(mCount);
		//获取文字的宽和高 
		mPaint.getTextBounds(text, 0, text.length(), mBounds);
		float textWidth = mBounds.width();
		float textHeight = mBounds.height();
		
		//绘制字符串
		canvas.drawText(text, getWidth() / 2 - textWidth / 2, getHeight() / 2 - textHeight / 2, mPaint);
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		mCount++;
		
		//重新绘制
		invalidate();
	}

}
