package com.wayne.webviewinterjsclass;



import android.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class MyObject {

	Context mContext;

	public MyObject(Context c) {
		// TODO Auto-generated constructor stub
		mContext = c;
	}

	@JavascriptInterface
	public void showToast(String name) {
		Toast.makeText(mContext, name + ",您好！", Toast.LENGTH_LONG).show();
	}

	@JavascriptInterface
	public void showList() {
		AlertDialog.Builder b = new AlertDialog.Builder(mContext);
		b.setTitle("图书列表");
		b.setIcon(R.drawable.ic_menu_slideshow);
		b.setItems(
				new String[] { "疯狂java讲义", "疯狂android讲义", "轻量级Java EE企业应用实战" },
				null);
		b.setPositiveButton("确定", null);

		b.create().show();
	}
}
