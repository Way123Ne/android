package com.wayne.picassodemo;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
/**
 * 
 * @author user
 * Picasso框架的简单实用，未涉及图片的缓存
 */
public class MainActivity extends Activity {
	private ImageView full_image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		full_image = (ImageView) findViewById(R.id.full_image);
		
		//获取传递过来的imageUrl,并将image显示出来，注意设置placeholder
		Intent intent = getIntent();
		String imageUrl = intent.getExtras().getString("imageUrl");
		Picasso.with(MainActivity.this).load(imageUrl).placeholder(R.drawable.ic_launcher).error(R.drawable.ic_action_a).into(full_image);
	}
}
