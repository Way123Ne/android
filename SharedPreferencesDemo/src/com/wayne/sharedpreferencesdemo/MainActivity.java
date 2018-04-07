package com.wayne.sharedpreferencesdemo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * 
 * @author user
 *启动AVD(已root)找到data目录下data文件夹下对应的应用程序包名，找到savedata.xml文件
 *真机需root查看
 */
public class MainActivity extends Activity {

	private Button saveData, getSaveData, clearData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		saveData = (Button) findViewById(R.id.saveData);
		getSaveData = (Button) findViewById(R.id.getSaveData);
		clearData = (Button) findViewById(R.id.clearData);
		
		saveData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//创建名为savedata的文件，加入键值对，保存数据
				SharedPreferences.Editor editor = getSharedPreferences("savedata", MODE_PRIVATE).edit();
				editor.putString("name", "I am a boy");
				//必须commit
				editor.commit();
			}
		});
		
		getSaveData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//取出savedata文件中的键值对，并提示出来
				SharedPreferences preferences = getSharedPreferences("savedata", MODE_PRIVATE);
				String name = preferences.getString("name", "");
				Toast.makeText(getApplication(), name, Toast.LENGTH_SHORT).show();
			}
		});
		
		clearData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//清除savedata文件中的数据
				SharedPreferences.Editor editor = getSharedPreferences("savedata", MODE_PRIVATE).edit();
				editor.clear();
				editor.commit();
			}
		});
	}
}
