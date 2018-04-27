package com.wayne.eventbusfragment;

import org.greenrobot.eventbus.EventBus;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wayne.eventbusdemo.R;
import com.wayne.eventbusentity.MsgEvent1;
import com.wayne.eventbusentity.MsgEvent2;

public class RightFragment extends Fragment{

	private TextView tv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//界面创建时，接收消息
		EventBus.getDefault().register(this);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//界面销毁时，不再接收消息
		EventBus.getDefault().unregister(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	
		View view = inflater.inflate(R.layout.fragment_right,null);
		tv = (TextView) view.findViewById(R.id.tv);
		return view;
	}
	
	/*
	 * 与发布者在同一个线程
	 * @MsgEvent1
	 */
	public void onEvent(MsgEvent1 msg){
		String content = msg.getMsg()+"\nThreadName:"+Thread.currentThread().getName()+"\nThreadId:"+Thread.currentThread().getId();
		System.out.println("onEvent(MsgEvent1 msg)收到"+content);
	}
	
	/*
	 * 执行在主线程
	 * 非常实用，可以在这里将子线程加载到的数据直接设置到UI界面中
	 * @MsgEvent1
	 */
	public void onEventMainThread(MsgEvent1 msg){
		String content = msg.getMsg()+"\nThreadName:"+Thread.currentThread().getName()+"\nThreadId:"+Thread.currentThread().getId();
		System.out.println("onEventMainThread(MsgEvent1 msg)收到"+content);
		tv.setText(content);
	}
	
	/*
	 * 执行在子线程
	 * 如果发布者是子线程则直接执行，如果发布者不是子线程，则创建一个再执行
	 * 此处可能有线程阻塞问题
	 * @MsgEvent1
	 */
	public void onEventBackgroundThread(MsgEvent1 msg){
		String content = msg.getMsg()+"\nThreadName:"+Thread.currentThread().getName()+"\nThreadId:"+Thread.currentThread().getId();
		System.out.println("onEventBackgroundThread(MsgEvent1 msg)收到"+content);
	}
	
	/*
	 * 执行在一个新的子线程
	 * 适用于多个线程任务处理，内部有线程池管理
	 * @MsgEvent1
	 */
	public void onEventAsync(MsgEvent1 msg){
		String content = msg.getMsg()+"\nThreadName:"+Thread.currentThread().getName()+"\nThreadId:"+Thread.currentThread().getId();
		System.out.println("onEventAsync(MsgEvent1 msg)收到"+content);
	}
	
	/*
	 * 与发布者在同一个线程
	 * @MsgEvent2
	 */
	public void onEvent(MsgEvent2 msg){
		String content = msg.getMsg()+"\nThreadName:"+Thread.currentThread().getName()+"\nThreadId:"+Thread.currentThread().getId();
		System.out.println("onEvent(MsgEvent2 msg)收到"+content);
		tv.setText(content);
	}
}
