package com.ilovelonely.guoshiqi.dribbbleu.utils;

import android.app.FragmentTransaction;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;

import com.ilovelonely.guoshiqi.dribbbleu.R;
import com.ilovelonely.guoshiqi.dribbbleu.frameWork.BaseFragment;

/**
 * Created by guoshiqi on 16/5/31.
 */
public class FragmentUtils {

    public static void startFragment(BaseFragment fragment, Intent aIntent, int style) {
        if (fragment == null) {
            return;
        }
        fragment.getFragmentManager().beginTransaction();

    }

    public static void startFragment(AppCompatActivity act, BaseFragment frag,
                                     // public static void startFragment(FragmentActivity act, BaseFragment frag,
                                     boolean backable) {
        // startFragment(act, frag, backable, null);
        if (act == null) {
            return;
        }
        // FragmentManager fm = act.getSupportFragmentManager();
        android.app.FragmentManager fm = act.getFragmentManager();
        if (fm != null) {
            FragmentTransaction ft = fm.beginTransaction();
            if (backable) {
                ft.add(R.id.homecontainer, frag);
            } else {
                ft.replace(R.id.homecontainer, frag);
            }
            ft.commitAllowingStateLoss();
        }
    }

}
