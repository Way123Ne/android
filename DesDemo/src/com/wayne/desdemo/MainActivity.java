package com.wayne.desdemo;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


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
 * DES特点:
 * 1>对称算法加密
 * 2>加密&解密密匙一样
 * 3>重要的几个步骤:
 * -创建Cipher --> Cipher cipher = .getInstance();
 * -初始化Cipher --> cipher.init();
 * -加密解密
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

	private void findId() {
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
				pass = encryptDES(et_password.getText().toString());

				System.out.println("user>>>>>>>>>>"+user);
				System.out.println("pass>>>>>>>>>>"+pass);
				
				if (!user.equals("") && !pass.equals("")) {
					pre.edit()
							.putString("username",
									et_username.getText().toString())
							.putString("password", encryptDES(pass)).commit();
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
				
				String decryptDES = decryptDES(loginpass);

				System.out.println("username:" + loginuser
						+ "---------------password:" + loginpass);
				System.out.println("user:" + user
						+ "---------------decryptDES:" +decryptDES );

				if (!user.equals("") && !pass.equals("")) {
					if (user.equals(loginuser) && decryptDES.equals(decryptDES)) {
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
	

	/**
	 * 加密
	 * 
	 * @param cleartext
	 *            需要加密的字符串
	 * @return
	 * @throws Exception
	 */
	public String encryptDES(String encryptStr) {
		byte[] result = null;
		try {
			if (encryptStr == null) {
				return null;
			} else {
				if (encryptStr.length() > 0) {
					byte[] rawKey = getRawKey("nwdloan".getBytes());// 加密密码
					result = encrypt(rawKey, encryptStr.getBytes());
				} else {
					return encryptStr;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			// LogUtil.getInstance(con).error("加密数据异常" + e.getMessage());
			System.out.println("加密数据异常" + e.getMessage());
		}
		return toHex(result);
	}

	/**
	 * 解密
	 * 
	 * @param encrypted
	 *            解密字符串
	 * @return
	 * @throws Exception
	 */
	public String decryptDES(String decryptStr) {
		byte[] result = null;
		try {
			if (decryptStr == null) {
				return null;
			} else {
				if (decryptStr.length() > 0) {
					byte[] rawKey = getRawKey("nwdloan".getBytes());// 解密密码
					byte[] enc = toByte(decryptStr);
					result = decrypt(rawKey, enc);
				} else {
					return decryptStr;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			// LogUtil.getInstance(con).error("解密数据异常" + e.getMessage());
			System.out.println("解密数据异常" + e.getMessage());
		}
		return new String(result);
	}

	private byte[] getRawKey(byte[] seed) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
		sr.setSeed(seed);
		kgen.init(256, sr); // 192 and 256 bits may not be available
		SecretKey skey = kgen.generateKey();
		byte[] raw = skey.getEncoded();
		return raw;
	}

	private byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(clear);
		return encrypted;
	}

	private byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		byte[] decrypted = cipher.doFinal(encrypted);
		return decrypted;
	}

	public String toHex(String txt) {
		return toHex(txt.getBytes());
	}

	public String fromHex(String hex) {
		return new String(toByte(hex));
	}

	public byte[] toByte(String hexString) {
		int len = hexString.length() / 2;
		byte[] result = new byte[len];
		for (int i = 0; i < len; i++)
			result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2),
					16).byteValue();
		return result;
	}

	public String toHex(byte[] buf) {
		if (buf == null)
			return "";
		StringBuffer result = new StringBuffer(2 * buf.length);
		for (int i = 0; i < buf.length; i++) {
			appendHex(result, buf[i]);
		}
		return result.toString();
	}

	private final String HEX = "0123456789ABCDEF";

	private void appendHex(StringBuffer sb, byte b) {
		sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
	}

}
