package com.itheima.mypageradapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MyPagerAdapter extends PagerAdapter {

	private List<ImageView> imageList;

	public MyPagerAdapter(List<ImageView> imageList){
		this.imageList  = imageList;
	}
	
	@Override
	// 返回页面的个数
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	@Override
	// 实例化相应的条目
	// position 该页面对应的位置
	// container 页面view的父view 其实就是viewPager 自身
	public Object instantiateItem(ViewGroup container, int position) {

		System.out.println("instantiateItem::" + position);

		// 1、根据当前position获得对应的view,并把view添加至container

		position = position % imageList.size();

		View view = imageList.get(position);

		container.addView(view);

		// 2、返回一个和view有关系的Object对象
		return view;
	}

	@Override
	// view 是 instantiateItem 方法中 添加至 container 的view对象
	// object 是 instantiateItem方法的返回值
	public boolean isViewFromObject(View view, Object object) {
		// if(view == object){
		// return true;
		// }else{
		// return false;
		// }
		return view == object;
	}

	@Override
	// 销毁某一个页面
	public void destroyItem(ViewGroup container, int position, Object object) {

		System.out.println("destroyItem::" + position);

		// 下面这句，必须注掉，否则会抛异常，为啥？你懂的。
		// super.destroyItem(container, position, object);

		container.removeView((View) object);
	}

}
