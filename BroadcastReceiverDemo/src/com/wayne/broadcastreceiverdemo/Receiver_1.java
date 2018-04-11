package com.wayne.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
/**
 * @author user
 *静态接收
 */
public class Receiver_1 extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String key = intent.getStringExtra("key");
		Toast.makeText(context, key+"成功", Toast.LENGTH_SHORT).show();
	}

}
