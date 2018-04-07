package com.itheima.viewpagertest;

import java.util.ArrayList;

import java.util.List;

import com.itheima.mypageradapter.MyPagerAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ViewPager viewPager;
	private TextView tvDesc;
	private LinearLayout pointGroup;
	// 图片资源ID
	private final int[] imageIds = { R.drawable.a, R.drawable.b, R.drawable.c,
			R.drawable.d, R.drawable.e };
	// 图片标题集合
	private final String[] imageDescriptions = { "巩俐不低俗，我就不能低俗",
			"扑树又回来啦！再唱经典老歌引万人大合唱", "揭秘北京电影如何升级", "乐视网TV版大派送", "热血屌丝的反杀" };
	// 页面切换后的上一个位置
	private int lastPosition;
	private List<ImageView> imageList;
	private boolean isRunning = false;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			System.out.println("handleMessage(android.os.Message msg====="+msg);

			viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);

			if (isRunning) {
				// Message ms = Message.obtain();
				// ms.what=22;
				// ms.obj = "xxxxxx";
				// handler.sendMessage(ms);
				handler.sendEmptyMessageDelayed(99, 5000);
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		viewPager = (ViewPager) findViewById(R.id.viewpager);
		tvDesc = (TextView) findViewById(R.id.tv_desc);
		tvDesc.setText(imageDescriptions[0]);
		pointGroup = (LinearLayout) findViewById(R.id.ll_point_group);

		// 准备工作
		imageList = new ArrayList<ImageView>();

		for (int i = 0; i < imageIds.length; i++) {
			ImageView image = new ImageView(this);
			image.setBackgroundResource(imageIds[i]);
			imageList.add(image);

			// 添加指示点
			ImageView point = new ImageView(this);

			// LayoutParams 的类型和要该view的父view的类型一致
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT, -2);
			params.leftMargin = 15;
			point.setLayoutParams(params);
			point.setBackgroundResource(R.drawable.point_bg);
			if (i == 0) {
				point.setEnabled(true);
			} else {
				point.setEnabled(false);
			}
			pointGroup.addView(point);

		}

		viewPager.setAdapter(new MyPagerAdapter(imageList));

		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			/**
			 * 当选择的页面发生改变的时候，回调此方法 
			 * position 选择的新的页面
			 */
			public void onPageSelected(int position) {

				position = position % imageList.size();

				tvDesc.setText(imageDescriptions[position]);

				// 改变指示点的状态，
				// 让上一个位置的点，设置 enable 为 false
				pointGroup.getChildAt(lastPosition).setEnabled(false);
				// 让当前位置的点，设置enable 为true
				pointGroup.getChildAt(position).setEnabled(true);

				// 更新上一个位置的值
				lastPosition = position;

			}

			@Override
			// 当页面在滑动上，不断的调用
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
			}

			@Override
			// 当页面的滑动状发生改变的时候，回调 ， 状态有：按下，滑动，抬起，
			public void onPageScrollStateChanged(int state) {
			}
		});

		// 设置viewPager当前页面的页面,item 是页面的位置

		int item = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2
				% imageList.size();
		viewPager.setCurrentItem(item);

		isRunning = true;
		handler.sendEmptyMessageDelayed(99, 5000);

	}

	protected void onDestroy() {
		super.onDestroy();
		isRunning = false;

	}

}
