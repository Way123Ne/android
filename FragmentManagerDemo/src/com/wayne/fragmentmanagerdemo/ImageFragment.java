package com.wayne.fragmentmanagerdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ImageView imageView = new ImageView(getActivity());
		imageView.setImageResource(R.drawable.ic_launcher);
		return imageView;
	}

	
}
