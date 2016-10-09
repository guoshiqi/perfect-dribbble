package com.ilovelonely.guoshiqi.dribbbleu.Helper;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by guoshiqi on 16/5/30.
 */
public class BackGroundWorker {

    private static ExecutorService cachedThreadPool  ;
    private static BackGroundWorker instatce;


    private BackGroundWorker(){
        if (cachedThreadPool==null){
            cachedThreadPool=Executors.newCachedThreadPool();
        }
    }

    public static BackGroundWorker getinstance(){
        if(instatce==null){
            synchronized (BackGroundWorker.class){
                if(instatce==null){
                    instatce=new BackGroundWorker();
                }
            }
        }
       return instatce;

    }


    public void justDoWork(Runnable runnable){
        cachedThreadPool.execute(runnable);
    }

    public Future doWork(Callable callable){
        return  cachedThreadPool.submit(callable);
    }

}
