package com.wayne.ninelayout;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.wayne.entity.PicEntity;

public class MainActivity extends Activity {
	private GridView gv;

	private String[] title = { "第一格", "第二格", "第三格", "第四格", "第五格", "第六格", "第七格",
			"第八格", "第九格" };
	private int[] image = { R.drawable.ic_launcher, R.drawable.ic_launcher,
			R.drawable.ic_launcher, R.drawable.ic_launcher,
			R.drawable.ic_launcher, R.drawable.ic_launcher,
			R.drawable.ic_launcher, R.drawable.ic_launcher,
			R.drawable.ic_launcher };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		gv = (GridView) findViewById(R.id.gv);
		MyAdapter adapter = new MyAdapter(this);
		gv.setAdapter(adapter);
	}

	class MyAdapter extends BaseAdapter {
		private List<PicEntity> list = new ArrayList<>();
		private Context context;

		public MyAdapter(Context context) {
			this.context = context;
			for (int i = 0; i < image.length; i++) {
				PicEntity picEntity = new PicEntity(title[i], image[i]);
				list.add(picEntity);
			}
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list != null ? list.size() : 0;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			Viewholder vh;
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(
						R.layout.gridview_item, null);
				vh = new Viewholder();
				vh.iv_pic = (ImageView) convertView.findViewById(R.id.iv_image);
				vh.tv_title = (TextView) convertView
						.findViewById(R.id.tv_title);
				convertView.setTag(vh);
			} else {
				vh = (Viewholder) convertView.getTag();
			}
			vh.iv_pic.setImageResource(list.get(position).imageId);
			vh.tv_title.setText(list.get(position).title);

			return convertView;
		}

	}

	class Viewholder {
		TextView tv_title;
		ImageView iv_pic;
	}
}
