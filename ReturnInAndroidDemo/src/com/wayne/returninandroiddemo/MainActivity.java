package com.wayne.returninandroiddemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private TextView textview1;
	private EditText edittext1;
	private Button button1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textview1 = (TextView) findViewById(R.id.textview1);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		button1 = (Button) findViewById(R.id.button1);
		
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(necessary()){
					Intent it = new Intent(MainActivity.this,SecondActivity.class);
					startActivity(it);
				}
			}

			private boolean necessary() {
				// TODO Auto-generated method stub
				if("".equals(edittext1.getText().toString())){
					Toast.makeText(getApplication(), "«Îœ» ‰»Î–’√˚", Toast.LENGTH_SHORT).show();
					return false;
				}
				return true;
			}
		});
	}
}
