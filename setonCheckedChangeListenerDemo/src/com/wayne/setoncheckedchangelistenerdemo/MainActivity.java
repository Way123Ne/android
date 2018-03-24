package com.wayne.setoncheckedchangelistenerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private RadioGroup rg_gender;
	private RadioButton rb_male;
	private RadioButton rb_female;
	private TextView textview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		RadioGroup rg_gender = (RadioGroup) findViewById(R.id.rg_gender);
		final RadioButton rb_male = (RadioButton) findViewById(R.id.rb_male);
		final RadioButton rb_female = (RadioButton) findViewById(R.id.rb_female);
		final TextView textview = (TextView) findViewById(R.id.textview);

		// 在rg_gender中注册setOnCheckedChangeListener事件
		rg_gender.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				String mGender = " ";
				if (rb_male.getId() == checkedId) {
					mGender = rb_male.getText().toString();
					textview.setText(mGender);
				}
				if (rb_female.getId() == checkedId) {
					mGender = rb_female.getText().toString();
					textview.setText(mGender);
				}
				Toast.makeText(getApplicationContext(), "您选择的性别是：" + mGender,
						Toast.LENGTH_LONG).show();
				Log.i(mGender, mGender);

			}
		});
	}

}
