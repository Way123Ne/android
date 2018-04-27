package com.wayne.eventbusfragment;

import org.greenrobot.eventbus.EventBus;

import android.annotation.SuppressLint;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wayne.eventbusentity.MsgEvent1;
import com.wayne.eventbusentity.MsgEvent2;

@SuppressLint("NewApi")
public class LeftFragment extends ListFragment{

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		String[] strs = new String[]{"主线程消息1","子线程消息1","主线程消息2"};
		setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, strs));
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		switch (position) {
		case 0:
			//主线程
			System.out.println("---------主线程发的消息1"+"threadName:"+Thread.currentThread().getName());
			EventBus.getDefault().post(new MsgEvent1("主线程发的消息1"));
			break;
		case 1:
			//子线程
			new Thread(){
				public void run(){
					System.out.println("---------主线程发的消息1"+"threadName:"+Thread.currentThread().getName());
					EventBus.getDefault().post(new MsgEvent1("子线程发的消息1"));
				};
			}.start();
			break;
		case 2:
			System.out.println("---------主线程发的消息2"+"threadName:"+Thread.currentThread().getName());
			EventBus.getDefault().post(new MsgEvent2("主线程发的消息2"));
			break;
		default:
			break;
		}
	}

	
}
