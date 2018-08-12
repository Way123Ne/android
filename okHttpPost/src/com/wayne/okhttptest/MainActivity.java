package com.wayne.okhttptest;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

*
 *异步和同步区别：1>异步:不在UI线程中执行  2>同步:在UI线程中执行
 */
/*

/*
 * okHttp:异步POST请求方式步骤
 * 1>get请求和post请求没什么太大区别
 * 2>post中RequestBody是必须构建的
 */
public class MainActivity extends Activity {
	private TextView tv_text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findId();

		setListener();
	}

	public void findId() {
		// TODO Auto-generated method stub
		tv_text = (TextView) findViewById(R.id.tv_text);
	}

	public void setListener() {
		// TODO Auto-generated method stub
		tv_text.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//Form表单形式
				OkHttpClient mOkHttpClient = new OkHttpClient();
				RequestBody body = new FormBody.Builder().add("username", "xiaoyi").build();
				Request request = new Request.Builder().post(body).url("https://www.baidu.com").build();
				mOkHttpClient.newCall(request).enqueue(new Callback() {
					
					@Override
					public void onResponse(Call arg0, Response arg1) throws IOException {
						// TODO Auto-generated method stub
						
//						1.1利用http Post请求，获取一个网页的内容 -获得返回的字符串
//						String htmlStr = arg1.body().string();
//						System.out.println("songweiqi>>>>>>>>>"+htmlStr);
						
//						1.2利用http Post请求，获取一个网页的内容 -获得返回的二进制字节数组
//						byte[] htmlByte = arg1.body().bytes();
//						System.out.println("songweiqi2>>>>>>>>"+htmlByte);
						
//						1.3利用http post请求，获取一个网页的内容 -获得返回的inputStream,此处意识到一点,该处支持大文件下载,有inputStream我们可通过IO方式写文件
//						InputStream inputStr = arg1.body().byteStream();
//						System.out.println("songweiqi3>>>>>>>>>"+inputStr);
						
//						2.操作控件,仍需使用handler
						final String res = arg1.body().string();
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								tv_text.setText(res);
								
							}
						});
					}
					
					@Override
					public void onFailure(Call arg0, IOException arg1) {
						// TODO Auto-generated method stub
						
					}
				});
			}
		});
	}
}
