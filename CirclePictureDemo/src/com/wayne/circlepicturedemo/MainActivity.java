package com.wayne.circlepicturedemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView mm1, mm2, mm3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();

		initData();
	}

	private void initView() {
		// TODO Auto-generated method stub
		mm1 = (ImageView) findViewById(R.id.mm1);
		mm2 = (ImageView) findViewById(R.id.mm2);
		mm3 = (ImageView) findViewById(R.id.mm3);

	}

	private void initData() {
		// TODO Auto-generated method stub

//		RoundedBitmapDrawable roundedBitmapDrawable1 = RoundedBitmapDrawableFactory.create(getResources(),BitmapFactory.decodeResources().R.drawable.ic_action_a);
//		RoundedBitmapDrawable roundedBitmapDrawable2 = RoundedBitmapDrawableFactory.create(getResources(),BitmapFactory.decodeResources().R.drawable.ic_action_a);
//		RoundedBitmapDrawable roundedBitmapDrawable3 = RoundedBitmapDrawableFactory.create(getResources(),BitmapFactory.decodeResources().R.drawable.ic_action_a);
		
//		mm1.setImageDrawable(roundedBitmapDrawable1);
//		
//		roundedBitmapDrawable2.setCornerRadius(150);
//		mm2.setImageDrawable(roundedBitmapDrawable2);
//		
//		roundedBitmapDrawable3.setCornerRadius(30);
//		mm2.setImageDrawable(roundedBitmapDrawable3);
	}
}
