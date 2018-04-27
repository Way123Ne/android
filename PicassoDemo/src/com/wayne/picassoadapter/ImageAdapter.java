package com.wayne.picassoadapter;

import java.util.ArrayList;

import com.squareup.picasso.Picasso;

import android.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	private Context context;

	// 图片url地址(多张图片)
	private ArrayList<String> urls;

	public ImageAdapter(Context context, ArrayList<String> urls) {
		this.context = context;
		this.urls = urls;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return urls.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return urls.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	//通过getView()来控制具体View的显示
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView imageView = new ImageView(context);
		
		//设置placeholder和error image
		Picasso.with(context).load(urls.get(position)).placeholder(R.drawable.btn_star).error(R.drawable.bottom_bar).into(imageView);
		
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageView.setLayoutParams(new GridView.LayoutParams(280,280));
	
		return imageView;
	}

}
