package com.ilovelonely.guoshiqi.dribbbleu.frameWork;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ilovelonely.guoshiqi.dribbbleu.Helper.THDevOpenHelper;
import com.squareup.leakcanary.LeakCanary;

import de.greenrobot.daoexample.DaoMaster;
import de.greenrobot.daoexample.DaoSession;

/**
 * Created by guoshiqi on 16/5/31.
 */
public class OwApplication extends Application {
    public static String accessToken;
    private static OwApplication mInstance;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    public static OwApplication getmInstance(){
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        if(mInstance == null)
            mInstance = this;
    }


    /**
     * 取得DaoMaster
     *
     * @param context        上下文
     * @return               DaoMaster
     */
    public static DaoMaster getDaoMaster(Context context) {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new THDevOpenHelper(context,"myDb",null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 取得DaoSession
     *
     * @param context        上下文
     * @return               DaoSession
     */
    public static DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

}
