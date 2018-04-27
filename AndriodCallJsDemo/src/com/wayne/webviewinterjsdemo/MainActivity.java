package com.wayne.webviewinterjsdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
/**
 * 
 * @author user
 *android原生吊JS
 */
public class MainActivity extends Activity {

	private WebView webView1;
	private Button button1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		webView1 = (WebView) findViewById(R.id.webView1);
		WebSettings webSettings = webView1.getSettings();
		// 设置与JS交互的权限
		webSettings.setJavaScriptEnabled(true);
		// 设置允许JS弹窗
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

		// 先载入JS代码
		// 格式规定为:file:///android_asset/文件名.html
		webView1.loadUrl("file:///android_asset/js.html");
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 通过Handler发送消息
				webView1.post(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						// 注意调用的JS方法名要对应上
						// 调用javascript的callJS()
						webView1.loadUrl("javascript:callJs()");
					}
				});
			}
		});

		// 由于设置了弹窗检验调用结果，所以需要支持js对话框
		// WebView只是载体，内容的渲染需要使用webviewChromClient类去实现
		// 通过设置WebChromeClient对象处理javascript的对话框
		// 设置响应js的Alert()函数
		webView1.setWebChromeClient(new WebChromeClient() {

			@Override
			public boolean onJsAlert(WebView view, String url, String message,
					final JsResult result) {
				// TODO Auto-generated method stub
				AlertDialog.Builder b = new AlertDialog.Builder(
						MainActivity.this);
				b.setTitle("Alert");
				b.setMessage(message);
				b.setPositiveButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								result.confirm();
							}
						});

				b.setCancelable(false);
				b.create().show();

				return true;
			}

		});
	}
}
