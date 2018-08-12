package com.wayne.okhttptest;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
/*
 *异步和同步区别：1>异步:不在UI线程中执行  2>同步:在UI线程中执行
 */
/*
 * okHttp:异步GET请求方式使用步骤
 * 
 *1>首先new一个OkHttpClient对象
 *2>首先构造一个Request对象,参数最起码有个url,当然还可通过Request.Builder设置更多参数比如:header、method等
 *3>然后通过request对象去构造得到一个Call对象,类似于将你的请求封装成了任务，既然是任务,就会有execute()和cancel()等方法
 *4>最后，希望以异步的方式去执行请求，所以调用的是call.enqueue,将call加入调度队列，然后等待任务执行完成,随后在Callback中即可得到结果
 */

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
		tv_text = (TextView)findViewById(R.id.tv_text);
	}

	public void setListener() {
		// TODO Auto-generated method stub
		tv_text.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//1.异步GET使用方法
				
				//创建okHttpClient对象
				OkHttpClient mOkHttpClient = new OkHttpClient();
				//创建一个Request
				final Request request = new Request.Builder().url("https://www.baidu.com").build();
				//new call
				Call call = mOkHttpClient.newCall(request);
				//请求加入调度-以异步的方式去执行请求
				call.enqueue(new Callback() {
					
					//该onResponse执行的线程并不是UI线程
					@Override
					public void onResponse(Call arg0, Response arg1) throws IOException {
						// TODO Auto-generated method stub
//						1.1利用http get请求，获取一个网页的内容 -获得返回的字符串
//						String htmlStr = arg1.body().string();
//						System.out.println("songweiqi>>>>>>>>>"+htmlStr);
						
//						1.2利用http get请求，获取一个网页的内容 -获得返回的二进制字节数组
//						byte[] htmlByte = arg1.body().bytes();
//						System.out.println("songweiqi2>>>>>>>>"+htmlByte);
						
//						1.3利用http get请求，获取一个网页的内容 -获得返回的inputStream,此处意识到一点,该处支持大文件下载,有inputStream我们可通过IO方式写文件
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
				
			//2. 同步GET使用方法
			
		    //		public void getDatasync(){
		    //			  new Thread(new Runnable() {
			//			@Override
			//			public void run() {
			//			try {
			//			OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
			//			Request request = new Request.Builder()
            //           .url("http://www.baidu.com")//请求接口。如果需要传参拼接到接口后面。
            //            .build();//创建Request 对象
			//			Response response = null;
			//			response = client.newCall(request).execute();//得到Response 对象
			//			if (response.isSuccessful()) {
			//			Log.d("kwwl","response.code()=="+response.code());
			//			Log.d("kwwl","response.message()=="+response.message());
			//			Log.d("kwwl","res=="+response.body().string());
						//此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
			//			}
			//		} catch (Exception e) {
			//		e.printStackTrace();
			//	}
			//}
		//}).start();
		//}
		
		}
	   });
	}
}
