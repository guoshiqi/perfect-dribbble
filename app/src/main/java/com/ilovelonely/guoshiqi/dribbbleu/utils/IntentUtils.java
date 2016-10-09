package com.ilovelonely.guoshiqi.dribbbleu.utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ilovelonely.guoshiqi.dribbbleu.frameWork.AppConstants;
import com.ilovelonely.guoshiqi.dribbbleu.frameWork.BaseActivity;
import com.ilovelonely.guoshiqi.dribbbleu.home.MyWebActivity;

/**
 * Created by guoshiqi on 16/5/31.
 */
public class IntentUtils {

    public static void startActivity(BaseActivity act, Class<?> clazz, Bundle b) {
        Intent intent = new Intent(act, clazz);
        if (b != null) {
            intent.putExtras(b);
        }
        act.startActivity(intent);
    }

    public static void gotoWebActivity(BaseActivity act,Bundle bundle){
        Intent intent = new Intent(act, MyWebActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        act.startActivity(intent);
    }

    public static void gotoWebActivityForResult(BaseActivity act,Bundle bundle){
        Intent intent = new Intent(act, MyWebActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        act.startActivityForResult(intent, AppConstants.RESULT_CODE);
    }

}
