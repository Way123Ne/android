package com.wayne.webviewinterjsdemo2;

import com.wayne.webviewinterjsclass.MyObject;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
/**
 * 
 * @author user
 *js调用android原生
 */
public class MainActivity extends Activity {

	private WebView webView1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		webView1 = (WebView) findViewById(R.id.webView1);
		webView1.loadUrl("file:///android_asset/test.html");
		
		WebSettings webSettings = webView1.getSettings();
		
		webSettings.setJavaScriptEnabled(true);
		
		webView1.addJavascriptInterface(new MyObject(this), "myObj");
	}
}
