package com.wayne.downloadmanagerdemo;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;

/*
 * 指定下载位置及文件名称
 * 方法1：
 * 目录: android -> data -> com.app -> files -> Download ->dxtj.apk
 * 该文件是你应用所专用的，软件卸载后，下载的文件将随着卸载全部被删除
 * request.setDestinationInExternalFilesDir(this,Environment.DIRECTORY_DOWNLOADS,"dxtj.ap);
 * 
 * 方法2:
 * 目录： 下载的文件存放地址 sdcard -> download -> dxtj.apk
 * 软件卸载后，下载的文件会保留
 * 在sdcard上创建一个文件夹
 * request.setDestinationInExternalPublicDir("/epmyp/","dxtj.apk");
 * 
 * 方法三:
 * 如果下载的文件希望被其他应用共享
 * 特别是那些下载下来希望被Media Scanner扫描到的文件(例如 音乐文件)
 * request.setDestinationInExternalPublicDir(Environment.DIRECTORY_MUSIC,"告白气球.mp3");
 */
public class MainActivity extends Activity implements View.OnClickListener {

	private TextView tv_file_name, tv_progress, tv_download,
			tv_cancle_download, tv_continue_download;
	private ProgressBar pb_update;
	private DownloadManager downloadManager;
	private DownloadManager.Request request;
	private static String downloadUrl = "http://ucdl.25pp.com/fs08/2017/01/20/2/2_87a290b5f041a8b512f0bc51595f839a.apk";
	private Timer timer;
	private TimerTask task;
	private long id;
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Bundle bundle = msg.getData();
			int pro = bundle.getInt("pro");
			String name = bundle.getString("name");
			pb_update.setProgress(pro);
			tv_progress.setText(String.valueOf(pro) + "%");
			tv_file_name.setText(name);
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv_file_name = (TextView) findViewById(R.id.tv_file_name);
		pb_update = (ProgressBar) findViewById(R.id.pb_update);
		tv_progress = (TextView) findViewById(R.id.tv_progress);
		tv_download = (TextView) findViewById(R.id.tv_download);
		tv_cancle_download = (TextView) findViewById(R.id.tv_cancle_download);
		tv_continue_download = (TextView) findViewById(R.id.tv_continue_download);

		//为下载、取消、继续控件绑定监听
		tv_download.setOnClickListener(this);
		tv_cancle_download.setOnClickListener(this);
		tv_continue_download.setOnClickListener(this);

		// 获得对象，开始下载
		downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
		request = new DownloadManager.Request(Uri.parse(downloadUrl));
		// 设置Notification的标题
		request.setTitle("大象投教");
		// 指定在WIFI状态下，执行下载操作
		request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
		// 是否允许漫游状态下，执行下载操作
		request.setAllowedOverRoaming(false);
		// 设置下载文件类型，如下为安卓.apk文件的类型
		request.setMimeType("application/vnd.android.package-archive");
		// 设置Notification的显示或隐藏
		request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

		// 创建目录
		Environment.getExternalStoragePublicDirectory(
				Environment.DIRECTORY_DOWNLOADS).mkdir();

		// 设置文件存放路径
		request.setDestinationInExternalPublicDir(
				Environment.DIRECTORY_DOWNLOADS, "app-release.apk");
		// 设置progressbar的最大进程
		pb_update.setMax(100);

		final DownloadManager.Query query = new DownloadManager.Query();

		// new一个计时器对象
		timer = new Timer();
		// new一个计时器任务类
		task = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Cursor cursor = downloadManager.query(query.setFilterById(id));
				if (cursor != null && cursor.moveToFirst()) {
					if (cursor.getInt(cursor
							.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
						pb_update.setProgress(100);
						install(Environment
								.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
								+ "/app-release.apk");
						task.cancel();
					}
					// 设置Notification标题
					String title = cursor.getString(cursor
							.getColumnIndex(DownloadManager.COLUMN_TITLE));
					// 将下载的文件放到本地目录
					String address = cursor.getString(cursor
							.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
					// 已经下载的字节数
					int bytes_downloaded = cursor
							.getInt(cursor
									.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
					// 下载所需总字节数
					int bytes_total = cursor
							.getInt(cursor
									.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
					int pro = (bytes_downloaded * 100) / bytes_total;
					Message msg = Message.obtain();
					Bundle bundle = new Bundle();
					bundle.putInt("pro", pro);
					bundle.putString("name", title);
					msg.setData(bundle);
					handler.sendMessage(msg);
				}
				cursor.close();
			}
		};
		timer.schedule(task, 0, 1000);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// 每下载的一个文件对应一个id,通过此id可以查询数据
		switch (v.getId()) {
		case R.id.tv_download:
			id = downloadManager.enqueue(request);
			task.run();
			tv_download.setClickable(false);
			break;
		case R.id.tv_cancle_download:
			downloadManager.remove(id);
			break;
		case R.id.tv_continue_download:
			id = downloadManager.enqueue(request);
			task.run();
			tv_download.setClickable(false);
			break;
		}
		
	}

	private void install(String path) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.parse("file://" + path),
				"application/vnd.android.package-archive");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}
}
