package com.cph.lib.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Created by CPH on 2019/7/6
 */
public class DatabaseManager  {

    public DaoSession mDaoSession = null;

    private UserProfileDao mDao = null;

    private static final class Holder{
        private static final DatabaseManager INSTANCE =new DatabaseManager();

    }
    public static DatabaseManager getInstance(){
        return Holder.INSTANCE;
    }
    public   DatabaseManager init(Context context){
        initDao(context);
        return this;
    }
    private DatabaseManager(){

    }
    private void initDao(Context context){
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context,"cph_ec.db");
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserProfileDao();
    }
    public final UserProfileDao getDao(){
        return mDao;
    }
}
