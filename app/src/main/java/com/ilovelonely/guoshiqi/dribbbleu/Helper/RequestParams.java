package com.ilovelonely.guoshiqi.dribbbleu.Helper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by guoshiqi on 16/5/30.
 */
public class RequestParams {
    private static HashMap<String,String> map;

    public RequestParams(){
        if (map==null){
            map=new HashMap<String, String>();

        }else {
            map.clear();
        }

    }


    public void put(String key,String value){
       map.put(key,value);
    }

    public HashMap<String, String> getMap() {
        return map;
    }
}
