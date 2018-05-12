package com.wayne.flagdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
 * boolean类型数据默认值为
 */
public class MainActivity extends Activity {
	private boolean flag;
	private EditText et_1;
	private Button btn_1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findId();
		setListener();

	}

	private void findId() {
		// TODO Auto-generated method stub
		et_1 = (EditText) findViewById(R.id.et_1);
		btn_1 = (Button) findViewById(R.id.btn_1);
	}

	private void setListener() {
		btn_1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if ("".equals(et_1.getText().toString())) {
					flag = false;
				} else {
					flag = true;
				}

				if (!flag) {
					Intent it = new Intent(MainActivity.this,
							SecondActivity.class);
					startActivity(it);
				} else {
					Toast.makeText(getApplication(), "无法跳转到下一页",
							Toast.LENGTH_LONG).show();
				}
			}
		});

	}
}
