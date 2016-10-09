package com.ilovelonely.guoshiqi.dribbbleu.frameWork;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by guoshiqi on 16/5/31.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b=getIntent().getExtras();
        if(b!=null){
            onParam(b);
        }
        onCreateView();
        setView();
    }

    public abstract void setView();

    public void onParam(Bundle b){

    }

    public abstract void onCreateView();
}
