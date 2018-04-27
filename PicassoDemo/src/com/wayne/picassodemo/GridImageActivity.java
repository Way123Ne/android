package com.wayne.picassodemo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.wayne.picassoadapter.ImageAdapter;
/**
 * 
 * @author user
 *通过GridImageActivity来显示网格图片
 */
public class GridImageActivity extends Activity {

	private ImageAdapter adapter;
	private GridView gridView;

	// 用来存储我们需要用到的18个图片的url地址
	private ArrayList<String> urls = new ArrayList<>();

	private final String baseUrl = "http://www.jycoder.com/json/Image/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gridview);

		gridView = (GridView) findViewById(R.id.grid_view);

		// 图片url地址
		for (int i = 0; i <= 18; i++) {
			urls.add(baseUrl + i + ".jpg");
		}

		adapter = new ImageAdapter(GridImageActivity.this, urls);
		gridView.setAdapter(adapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {
			//为每个网格添加单击事件监听，单击后跳转到图片展示页面MainActivity
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(GridImageActivity.this,
						MainActivity.class);
				// 需要传递这张图片呢的Url地址给MainActivity
				intent.putExtra("imageUrl", urls.get(arg2));
				startActivity(intent);
			}

		});
	}

}
