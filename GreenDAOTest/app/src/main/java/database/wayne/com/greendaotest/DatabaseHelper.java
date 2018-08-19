package database.wayne.com.greendaotest;

import android.content.Context;

import gen.wayne.com.greendaotest.DaoMaster;
import gen.wayne.com.greendaotest.DaoSession;

/**
 * Created by Wayne on 2018/8/18.
 */

public class DatabaseHelper {
    private static DatabaseHelper instance;
    private static Context mContext;

    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    /**
     * 取得DaoMaster
     *
     * @param context
     * @return
     */
    public static DaoMaster getDaoMaster(Context context) {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, "notes.db", null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return  daoMaster;
    }

    /**
     * 取得DaoSession
     * @param context
     * @return
     */
    public static DaoSession getDaoSession(Context context){
        if(daoSession==null){
            if(daoMaster==null){
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    private DatabaseHelper(){

    }

    public  static  void init(Context context){
        mContext = context;
        instance = new DatabaseHelper();
        //数据库对象
        DaoSession daoSession = getDaoSession(mContext);
    }

    public static DatabaseHelper getInstance(){
        return instance;
    }
}

