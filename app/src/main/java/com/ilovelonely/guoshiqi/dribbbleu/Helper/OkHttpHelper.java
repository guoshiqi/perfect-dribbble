package com.ilovelonely.guoshiqi.dribbbleu.Helper;







import com.google.gson.Gson;
import com.ilovelonely.guoshiqi.dribbbleu.api.ApiConstants;
import com.ilovelonely.guoshiqi.dribbbleu.frameWork.AppConstants;
import com.ilovelonely.guoshiqi.dribbbleu.frameWork.OwApplication;

import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by guoshiqi on 16/5/30.
 */
public class OkHttpHelper {
    private BackGroundWorker worker;
    private OkHttpClient client;
    private static OkHttpHelper mInstance;
    private Gson mGson;

    public static OkHttpHelper getInstance()
    {
        if (mInstance == null)
        {
            synchronized (OkHttpHelper.class)
            {
                if (mInstance == null)
                {
                    mInstance = new OkHttpHelper();
                }
            }
        }
        return mInstance;
    }

    private OkHttpHelper(){
        client = new OkHttpClient();
        worker = BackGroundWorker.getinstance();
        mGson = new Gson();
    }

    public void get(String url,RequestParams params ,Callback callback){
        if(params!=null){
            int i=0;
            StringBuffer appendUrl=new StringBuffer(url);
            for(Map.Entry<String, String> entry : params.getMap().entrySet()){
                if(i==0){
                    appendUrl.append("?");
                }else{
                    appendUrl.append("&&");
                }
                i++;
                appendUrl.append(entry.getKey()+"="+ entry.getValue());
            }
            url=appendUrl.toString();
        }

        Request request = new Request.Builder()
                .header("Authorization","Bearer "+ OwApplication.accessToken)
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void post(String url,RequestParams params ,Callback callback){
        RequestBody formBody=null;
        if(params!=null){
            FormBody.Builder body=new FormBody.Builder();
            for(Map.Entry<String, String> entry : params.getMap().entrySet()){
               body.add(entry.getKey(),entry.getValue());

            }
           formBody=body.build();
        }
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        client.newCall(request).enqueue(callback);
    }



}