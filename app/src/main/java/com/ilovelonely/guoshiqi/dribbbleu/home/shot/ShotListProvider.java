package com.ilovelonely.guoshiqi.dribbbleu.home.shot;

import android.content.Context;
import android.util.Log;

import com.ilovelonely.guoshiqi.dribbbleu.Helper.OkHttpHelper;
import com.ilovelonely.guoshiqi.dribbbleu.Helper.RequestParams;
import com.ilovelonely.guoshiqi.dribbbleu.api.ApiConstants;
import com.ilovelonely.guoshiqi.dribbbleu.frameWork.AppConstants;
import com.ilovelonely.guoshiqi.dribbbleu.utils.TimeUtils;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by guoshiqi on 16/5/31.
 */
public class ShotListProvider {
    private OkHttpHelper okHttpHelper;
    private ArrayList shotData;

    public ShotListProvider(){
        okHttpHelper=OkHttpHelper.getInstance();
    }

    public void getDataFromNetWork(){
        RequestParams params=new RequestParams();
        params.put("date", TimeUtils.getNowDate("yyyy-MM-dd"));
        params.put("list","");
        params.put("timeframe","");
        okHttpHelper.get(ApiConstants.SHOUT_LIST_URL, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("s",response.body().string());
            }
        });
    }
}
