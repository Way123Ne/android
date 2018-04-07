package com.wayne.sqliteopenhelpertest;

import com.wayne.sqliteopenhelperdemo.DBHelper;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 
 * @author user 启动AVD(已root)找到data目录下data文件夹对应的应用程序包名下的ooooo.db 真机需root查看
 */

public class MyDBActivity extends Activity {

	private Button textBtn;
	private Button btnInsert = null;
	private Button btnUpdate = null;
	private Button btnDelete = null;
	private DBHelper dbHelper = null;
	SQLiteDatabase db = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlitetest);

		openDb();

		textBtn = (Button) findViewById(R.id.textBtn);
		textBtn.setFocusable(true);

		btnInsert = (Button) findViewById(R.id.btnInsert);

		btnUpdate = (Button) findViewById(R.id.btnUpdate);

		btnDelete = (Button) findViewById(R.id.btnDelete);

		btnInsert.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				InsertTb();
			}
		});

		btnUpdate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				UpdateTb();
			}
		});

		btnDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DeleteTb();
			}
		});
	}

	/**
	 * 新建一个表
	 */
	public void CreateTable() {
		db = dbHelper.getWritableDatabase();
		String sql = "create table if not exists TestUsers"
				+ "(id int primary key,name varchar,sex varchar)";
		try {
			db.execSQL(sql);
		} catch (Exception e) {
			// TODO: handle exception
			Log.i("err", "");
		}
	}

	/**
	 * 插入数据
	 */
	public void InsertTb() {
		db = dbHelper.getWritableDatabase();
		String sql = "insert into TestUsers (id,name,sex) values (2,'hongguang','men')";
		try {
			db.execSQL(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 更新数据
	 */
	public void UpdateTb() {
		db = dbHelper.getWritableDatabase();
		String sql = "update into TestUsers set name ='anhong',sex = 'men' where id = 2";
		try {
			db.execSQL(sql);
		} catch (Exception e) {
			// TODO: handle exception
			Log.i("err", "update failed");
		}
	}

	/**
	 * 删除数据
	 * 
	 */
	public void DeleteTb() {
		db = dbHelper.getWritableDatabase();
		String sql = "delete from TestUsers where id = 2";
		try {
			db.execSQL(sql);
		} catch (Exception e) {
			// TODO: handle exception
			Log.i("err", "delete failed");
		}
	}

	/**
	 * 打开数据库
	 */
	public void openDb() {
		dbHelper = new DBHelper(MyDBActivity.this, "ooooo.db");
		db = dbHelper.getWritableDatabase();
	}

	/**
	 * 关闭数据库
	 */
	public void closeDb() {
		dbHelper.close();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (db != null) {
			db.close();
		}
		if (dbHelper != null) {
			dbHelper.close();
		}
	}

}
