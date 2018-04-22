package com.wayne.bottomnavigationbardemo2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.TextView;

public class FirstFragment extends Fragment {

	private String context;
	private TextView txt_content;

	public FirstFragment(String context) {

		this.context = context;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// 为fragment填充布局文件
		View view = inflater.inflate(R.layout.first_fragment, container, false);
		// 找出布局文件中的控件 -> 同activity的findViewById()不同
		txt_content = (TextView) view.findViewById(R.id.txt_content);
		txt_content.setText(context);

		return view;
	}

}
