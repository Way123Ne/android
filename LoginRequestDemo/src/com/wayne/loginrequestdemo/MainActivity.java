package com.wayne.loginrequestdemo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wayne.utils.Utils;

public class MainActivity extends Activity {
    private EditText et_name;
    private EditText et_password;
    private Button btn_submit;
    private TextView tv_result;
	
    private WebView webView;
    
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            tv_result.setText(msg.obj + "");
            System.out.println("tv_result>>>>>>>>>" + tv_result.getText().toString());
            Toast.makeText(MainActivity.this, (String) msg.obj, 0).show();
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name = (EditText) findViewById(R.id.et_name);
        et_password = (EditText) findViewById(R.id.et_password);

        tv_result = (TextView) findViewById(R.id.tv_result);

        webView = (WebView) findViewById(R.id.wv_html);
        loadWeb();
        
        btn_submit = (Button) findViewById(R.id.btn_submit);
        // 用get的方式请求，把请求内容拼接到url后面。
        final String s = "http://106.128.30.96:8080/testServlet/LoginServlet?username=aaaa&password=1234";

        //Log.i("MainActivity", path);
        btn_submit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                final String username = et_name.getText().toString().trim();
                final String password = et_password.getText().toString().trim();
                Thread t = new Thread() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        try {
                            final String path = "http://106.128.30.96:8080/testServlet/LoginServlet?username="
                                    + URLEncoder.encode(username) // 对提交的数据进行url编码
                                    + "&password="+password;
                            URL url = new URL(path);
                            HttpURLConnection conn = (HttpURLConnection) url
                                    .openConnection();
                            conn.setRequestMethod("GET");
                            conn.setReadTimeout(5000);
                            if (conn.getResponseCode() == 200) {
                                InputStream is = conn.getInputStream();
                                String text = Utils.getTextFromStream(is);
                                Log.i("MainActivity", text);
                                // 发送消息，把服务器返回的本文弹出吐司显示
                                Message msg = handler.obtainMessage();
                                msg.obj = text;
                                handler.sendMessage(msg);
                            }
                        } catch (MalformedURLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        super.run();
                    }
                };
                t.start();
            }

        });

    }

	private void loadWeb() {
		// TODO Auto-generated method stub
		String url = "http://106.128.30.96:8080/testServlet/login.jsp";
		webView.setWebViewClient(new WebViewClient());
		webView.loadUrl(url);
	}
}
