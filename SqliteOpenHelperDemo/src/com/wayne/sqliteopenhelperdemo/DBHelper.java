package com.wayne.sqliteopenhelperdemo;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

public class DBHelper extends SQLiteOpenHelper {

	// 数据库版本
	private static final int VERSION = 1;
	// 新建一个 表
	String sql = "create table if not exists TestUsers"
			+ "(id int primary key,name varchar,sex varchar)";

	//四个参数的构造函数
	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	//三个参数的构造函数调用四个参数的构造函数
	public DBHelper(Context context, String name, int version) {
		//调用上一个构造函数
		this(context, name, null, version);
	}

	//两个参数的构造函数调用三个参数的构造函数
	public DBHelper(Context context, String name) {
		this(context, name, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
}
