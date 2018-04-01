package com.wayne.fragmentmanagerdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	private Button btn_add, btn_remove, btn_replace, btn_attach, btn_detach,
			btn_show, btn_hide;
	private FrameLayout fl_content;

	TextFragment textFragment;
	ImageFragment imageFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn_add = (Button) findViewById(R.id.btn_add);
		btn_remove = (Button) findViewById(R.id.btn_remove);
		btn_replace = (Button) findViewById(R.id.btn_replace);
		btn_attach = (Button) findViewById(R.id.btn_attach);
		btn_detach = (Button) findViewById(R.id.btn_detach);
		btn_show = (Button) findViewById(R.id.btn_show);
		btn_hide = (Button) findViewById(R.id.btn_hide);
		fl_content = (FrameLayout) findViewById(R.id.fl_content);

		textFragment = new TextFragment();
		imageFragment = new ImageFragment();

		btn_add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				add(fl_content);
			}
		});

		btn_remove.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				remove(fl_content);
			}
		});

		btn_replace.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				replaceTextFragment(fl_content);
				replaceImageFragment(fl_content);
			}
		});

		btn_attach.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				attach(fl_content);
			}
		});

		btn_detach.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				detach(fl_content);
			}
		});

		btn_show.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showTextFragment(fl_content);
				showImageFragment(fl_content);
			}
		});

		btn_hide.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				hideTextFragment(fl_content);
				hideImageFragment(fl_content);
			}
		});
	}

	public void add(View v) {
		// 创建事物
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		// 操作指令
		ft.add(R.id.fl_content, textFragment);
		// 提交事物
		ft.commit();
	}

	public void remove(View v) {
		// 创建事物
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		// 从Activity中移除一个Fragment
		ft.remove(textFragment);
		// 提交事物
		ft.commit();
	}

	public void replaceTextFragment(View v) {
		// 创建事物
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		// 替换Activity中的一个Fragment
		ft.replace(R.id.fl_content, textFragment);
		// 提交事物
		ft.commit();
	}
	
	public void replaceImageFragment(View v) {
		// 创建事物
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		// 替换Activity中的一个Fragment
		ft.replace(R.id.fl_content, imageFragment);
		// 提交事物
		ft.commit();
	}

	public void attach(View v) {
		// 创建事物
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		// 将Fragment附加到Activity上
		ft.attach(textFragment);
		// 提交事物 ==>ft.commit(Api 13)
		ft.commit();
	}

	@SuppressLint("NewApi")
	public void detach(View v) {
		// 创建事物
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		// 会将v从UI中移除，但和remove()不同，此时fragment的状态依然由FragmentManager维护
		ft.detach(textFragment);
		// 提交事物
		ft.commit();
	}

	public void showTextFragment(View v) {
		// 创建事物
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		// 显示之前隐藏的Fragment
		ft.show(textFragment);
		// 提交事物
		ft.commit();
	}
	
	public void showImageFragment(View v) {
		// 创建事物
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		// 显示之前隐藏的Fragment
		ft.show(imageFragment);
		// 提交事物
		ft.commit();
	}

	public void hideTextFragment(View v) {
		// 创建事物
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		// 当Fragment数量固定很少时隐藏当前的Fragment,仅设为不可见，并不会销毁
		ft.hide(textFragment);
		// 提交事物
		ft.commit();
	}

	public void hideImageFragment(View v) {
		// 创建事物
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		// 当Fragment数量固定很少时隐藏当前的Fragment,仅设为不可见，并不会销毁
		ft.hide(imageFragment);
		// 提交事物
		ft.commit();
	}

}
