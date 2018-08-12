package com.wayne.md5demo;

import java.security.MessageDigest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/*
 * 大体思路:先存sp后根据sp校验
 */
public class MainActivity extends Activity {
	private EditText et_username, et_password;
	private Button btn_save, btn_login;
	private String user, pass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findId();
		setListener();
	}

	public void findId() {
		// TODO Auto-generated method stub
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		btn_save = (Button) findViewById(R.id.btn_save);
		btn_login = (Button) findViewById(R.id.btn_login);
	}

	public void setListener() {
		// TODO Auto-generated method stub
		btn_save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences pre = getSharedPreferences("loginvalue",
						MODE_WORLD_WRITEABLE);
				user = et_username.getText().toString();
				pass = MD5(et_password.getText().toString());

				System.out.println("user>>>>>>>>>>"+user);
				System.out.println("pass>>>>>>>>>>"+pass);
				
				if (!user.equals("") && !pass.equals("")) {
					pre.edit()
							.putString("username",
									et_username.getText().toString())
							.putString("password", encryptmd5(pass)).commit();
					Toast.makeText(getApplicationContext(), "保存成功",
							Toast.LENGTH_SHORT).show();
					
					System.out.println("user>>>>>>>>>>"+user);
					System.out.println("pass>>>>>>>>>>"+pass);
				} else {
					Toast.makeText(getApplicationContext(), "密码不能为空",
							Toast.LENGTH_LONG).show();
				}
			}
		});

		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences sp = getSharedPreferences("loginvalue",
						MODE_WORLD_READABLE);
				String loginuser = sp.getString("username", null);
				String loginpass = sp.getString("password", null);

				user = et_username.getText().toString();
//				pass = et_password.getText().toString();

//				String passmd5 = MD5(pass);
				String encryptmd5 = encryptmd5(loginpass);

				System.out.println("username:" + loginuser
						+ "---------------password:" + loginpass);
				System.out.println("user:" + user
						+ "---------------encryptmd5:" + encryptmd5);

				if (!user.equals("") && !pass.equals("")) {
					if (user.equals(loginuser) && encryptmd5.equals(encryptmd5)) {
						Intent it = new Intent();
						it.setClass(getApplication(), LoginActivity.class);
						startActivity(it);
						finish();
					} else {
						Toast.makeText(getApplicationContext(), "密码是错误的!",
								Toast.LENGTH_LONG).show();
					}
				} else {
					Toast.makeText(getApplicationContext(), "密码不能为空",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	// MD5加密,32位
	public static String MD5(String str) {
		MessageDigest md5 = null;

		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "";
		}

		char[] charArray = str.toCharArray();
		byte[] byteArray = new byte[charArray.length];
		for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}

		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	// 可逆的加密算法
	//encrypt:加密
	public static String encryptmd5(String str) {
		char[] a = str.toCharArray();

		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 'l');
		}

		String s = new String(a);
		return s;
	}

}
