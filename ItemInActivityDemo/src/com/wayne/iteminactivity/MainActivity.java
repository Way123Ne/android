package com.wayne.iteminactivity;

import com.wayne.iteminlistviewdemo.R;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity implements OnScrollListener {
	private ListView list;
	private int scrollState;
	private int count = 40;
	private int lastItem;
	private int visibleItemCount;
	private Button footerButton;
	private LinearLayout footerProgressBarLayout;
	private View view;
	private ListAdapter listAdapter = new ListAdapter();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.other_listview);

		LayoutInflater inflater = LayoutInflater.from(this);
		view = inflater.inflate(R.layout.other_listview_footer_more, null);
		footerButton = (Button) view.findViewById(R.id.button);
		footerProgressBarLayout = (LinearLayout) view
				.findViewById(R.id.linearlayout);

		footerProgressBarLayout.setVisibility(View.GONE);

		footerButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (lastItem == listAdapter.count
						&& scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
					footerButton.setVisibility(View.GONE);
					footerProgressBarLayout.setVisibility(View.VISIBLE);

				}
				if (listAdapter.count <= count) {
					new Handler().postDelayed(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							listAdapter.count += 10;
							listAdapter.notifyDataSetChanged();
							footerButton.setVisibility(View.VISIBLE);
							footerProgressBarLayout.setVisibility(View.GONE);
						}
					}, 2000);
				}
			}
		});
		list = getListView();
		list.addFooterView(view);
		list.setAdapter(listAdapter);
		list.setOnScrollListener(this);
	}

	class ListAdapter extends BaseAdapter {
		private int count = 10;

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return count;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
			View view = inflater.inflate(R.layout.other_listview_item, null);
			TextView tv_good_name = (TextView) view.findViewById(R.id.tv_good_name);
			TextView tv_order_num = (TextView) view.findViewById(R.id.tv_order_num);
			TextView tv_size = (TextView) view.findViewById(R.id.tv_size);
//			tv.setText("ÉÌÆ·Ãû³Æ:");
			return view;
		}

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		this.visibleItemCount = visibleItemCount;
		lastItem = firstVisibleItem + visibleItemCount - 1;
		System.out.println(listAdapter.count);
		if (listAdapter.count >= count) {
			list.removeFooterView(view);
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		this.scrollState = scrollState;
	}
}
