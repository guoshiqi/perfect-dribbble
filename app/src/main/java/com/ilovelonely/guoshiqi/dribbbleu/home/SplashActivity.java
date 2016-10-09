package com.ilovelonely.guoshiqi.dribbbleu.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.ilovelonely.guoshiqi.dribbbleu.Helper.DbService;
import com.ilovelonely.guoshiqi.dribbbleu.R;
import com.ilovelonely.guoshiqi.dribbbleu.frameWork.OwApplication;
import com.ilovelonely.guoshiqi.dribbbleu.home.login.LoginActivity;
import com.ilovelonely.guoshiqi.dribbbleu.utils.L;
import com.ilovelonely.guoshiqi.dribbbleu.utils.SPUtils;

import de.greenrobot.daoexample.Customer;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by guoshiqi on 16/6/2.
 */
public class SplashActivity extends AppCompatActivity {
    private Long id=0l;
    private final Object lock=new Object();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView splashView = (ImageView) findViewById(R.id.splash_image);
        Observable.create(new Observable.OnSubscribe<Class>() {
            @Override
            public void call(Subscriber<? super Class> subscriber) {
                subscriber.onNext(setClass());
                subscriber.onCompleted();
            }
        })
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<Class>() {
            @Override
            public void onNext(Class s) {
                synchronized (lock){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                gotoOtherActivity(s);
                L.d( "Item: " + s);
            }

            @Override
            public void onCompleted() {
                L.d( "Completed!");
            }

            @Override
            public void onError(Throwable e) {
                L.d( "Error!");
            }
        });

        splashView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                synchronized (lock){
                    lock.notifyAll();
                }
            }
        });
    }

    private Class setClass(){
        Class aClass;
        DbService db=DbService.getInstance(this);

        id= (Long) SPUtils.get(OwApplication.getmInstance(),"id",id);
        if(id!=null) {
            Customer customer = db.loadNote(id);

            if (TextUtils.isEmpty(customer.getName())) {
                aClass = LoginActivity.class;
            } else if (TextUtils.isEmpty(customer.getAccesstoken())) {

                aClass = MyWebActivity.class;
            } else {
                OwApplication.accessToken = customer.getAccesstoken();
                aClass = HomeActivity.class;
            }
        }else{
            aClass = LoginActivity.class;
        }

        return aClass;
    }

    private void gotoOtherActivity(Class aClass){

        if(aClass!=null){
            Intent intent=new Intent(this,aClass);
            startActivity(intent);
        }

        SplashActivity.this.finish();
    }



}
