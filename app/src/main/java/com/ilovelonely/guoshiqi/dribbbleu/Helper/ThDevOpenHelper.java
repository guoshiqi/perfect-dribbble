package com.ilovelonely.guoshiqi.dribbbleu.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import de.greenrobot.daoexample.DaoMaster;

/**
 * Created by guoshiqi on 16/6/3.
 */
public class THDevOpenHelper extends DaoMaster.OpenHelper {

    public THDevOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 2:
                //创建新表，注意createTable()是静态方法


                // 加入新字段
                // db.execSQL("ALTER TABLE 'moments' ADD 'audio_path' TEXT;");

                // TODO
                break;
        }
    }
}
