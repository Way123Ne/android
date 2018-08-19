package application.wayne.com.greendaotest;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import gen.wayne.com.greendaotest.DaoMaster;
import gen.wayne.com.greendaotest.DaoSession;

/**
 * Created by Wayne on 2018/8/18.
 */

public class MyApplication extends Application {
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private static MyApplication instances;

    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
        setDatabase();
    }

    public static MyApplication getInstances() {
        return instances;
    }

    /**
     * 设置greendao
     */
    private void setDatabase() {
        //通过DaoMaster的内部类DevOpenHelper,可得到一个便利的SQLiteOpenHelper对象
        //大家已经注意到，并不需要自己去编写[CREATE TABLE]这样的SQL语句，因为greenDAO已经帮我们做了
        //注意：默认的DaoMaster.DevOpenHelper会再数据库升级时，删除所有的表，意味着这将导致数据的丢失
        //所以，再正式的项目中，个人还应该左一层封装，来实现数据库的安全升级

        mHelper = new DaoMaster.DevOpenHelper(this,"notes-db",null);
        db = mHelper.getWritableDatabase();
        //注意：该数据库连接属于DaoMaster,所以多个Session指的是相同的数据库连接
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }
    public DaoSession getDaoSession(){
        return mDaoSession;
    }
    public SQLiteDatabase getDb(){
        return  db;
    }
}
