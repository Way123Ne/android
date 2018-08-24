package com.wayne.picassotest;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private Button button;
	private ImageView imageView;
	private String url = "http://a.hiphotos.baidu.com/image/h%3D200/sign=623f372f0b24ab18ff16e63705fbe69a/267f9e2f070828382f690e1dba99a9014c08f157.jpg";
	private static final String ANDROID_RESOURCE="android.resource://";
	private static final String ROREWARD_SLASH="/";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
		initEvent();

		// https://www.cnblogs.com/simadi/p/6707369.html
	}

	private void initEvent() {
		// TODO Auto-generated method stub
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// 1.获取网络图片
				// Picasso.with(MainActivity.this).load(url).into(imageView);

				// 2.加载Android Resource本地图片
				// int resourceId = R.drawable.ic_launcher;
				// Picasso.with(getApplication()).load(resourceId).into(imageView);

				// 3.加载本地文件中的图片
				//String path = "/storage/emulated/0/me/movie.jpg";
				//String realpath = "file://"+path;
				//Picasso.with(getApplicationContext()).load(realpath).into(imageView);
				
				// 4.从URI地址中加载图片
				Uri uri = resourceIdToUri(MainActivity.this,R.drawable.ic_action_a);
				Picasso.with(MainActivity.this).load(uri).into(imageView);
			}

			private Uri resourceIdToUri(Context context, int resourceId) {
				// TODO Auto-generated method stub
				return Uri.parse(ANDROID_RESOURCE+context.getPackageName()+ROREWARD_SLASH+resourceId);
			}
		});

	}

	private void initView() {
		// TODO Auto-generated method stub
		button = (Button) findViewById(R.id.button);
		imageView = (ImageView) findViewById(R.id.imageView);

	}

}
