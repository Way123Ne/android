package com.wayne.canvasdemo;

import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button btn_again, btn_save;
	private ImageView iv_canvas;
	private Bitmap baseBitmap;
	private Canvas canvas;
	private Paint paint;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn_again = (Button) findViewById(R.id.btn_again);
		btn_save = (Button) findViewById(R.id.btn_save);
		iv_canvas = (ImageView) findViewById(R.id.iv_canvas);

		// 初始化一个画笔，笔触宽度为5，颜色为红色
		paint = new Paint();
		paint.setStrokeWidth(5);
		paint.setColor(Color.RED);

		iv_canvas.setOnTouchListener(new OnTouchListener() {
			float startX;
			float startY;
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				// 定义手指开始触摸的坐标
				switch (event.getAction()) {
				// 用户按下动作
				case MotionEvent.ACTION_DOWN:
					// 第一次绘图初始内存图片，指定背景为白色
					if (baseBitmap == null) {
						baseBitmap = Bitmap.createBitmap(iv_canvas.getWidth(),
								iv_canvas.getHeight(), Bitmap.Config.ARGB_8888);
						canvas = new Canvas(baseBitmap);
						canvas.drawColor(Color.WHITE);
					}
					// 记录开始触摸的点的坐标
					startX = event.getX();
					startY = event.getY();
					break;
				case MotionEvent.ACTION_MOVE:
					// 记录移动位置的点的坐标
					float stopX = event.getX();
					float stopY = event.getY();

					// 根据两点坐标,绘制连线
					canvas.drawLine(startX, startY, stopX, stopY, paint);

					// 更新开始点的位置
					startX = event.getX();
					startY = event.getY();

					// 把图片展示到ImageView中
					iv_canvas.setImageBitmap(baseBitmap);
					break;
				case MotionEvent.ACTION_UP:
					break;
				default:
					break;
			
			}
				return true;
			}
		});
		
		btn_again.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				againCanvas();
			}
		});
		
		btn_save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				saveBitmap();
			}
		});
	}

	protected void againCanvas() {
			// TODO Auto-generated method stub
			// 手动清除画板的绘图，重新创建一个画板
			if (baseBitmap != null) {
				baseBitmap = Bitmap.createBitmap(iv_canvas.getWidth(),
						iv_canvas.getHeight(), Bitmap.Config.ARGB_8888);
				canvas = new Canvas(baseBitmap);
				canvas.drawColor(Color.WHITE);
				iv_canvas.setImageBitmap(baseBitmap);
				Toast.makeText(MainActivity.this, "清除画板成功，可以重新开始画图", 0).show();
			}
		}
	
	protected void saveBitmap() {
		// TODO Auto-generated method stub
		try {
			// 保存图片到SD卡上
			File file = new File(Environment.getExternalStorageDirectory(),
					System.currentTimeMillis() + ".png");
			FileOutputStream os = new FileOutputStream(file);
			baseBitmap.compress(CompressFormat.PNG, 100, os);

			// android设备Gallary应用只会在启动的时候扫描系统文件夹
			// 这里模拟一个媒体装载的广播，用于使保存的图片可以在Gallary中查看
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
			intent.setData(Uri.fromFile(Environment
					.getExternalStorageDirectory()));
			sendBroadcast(intent);
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(MainActivity.this, "保存图片失败", 0).show();
			e.printStackTrace();
		}
	}
}
