package com.ilovelonely.guoshiqi.dribbbleu.home.shot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ilovelonely.guoshiqi.dribbbleu.R;
import com.ilovelonely.guoshiqi.dribbbleu.frameWork.BaseFragment;

/**
 * Created by guoshiqi on 16/5/31.
 */
public class ShotListFragment extends BaseFragment {
    private RecyclerView shotList;
    private ShotListAdapter shotListadapter;
    private ShotListProvider shotListProvider;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_shotlist,null);
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void setView() {
        shotList=(RecyclerView)rootView.findViewById(R.id.widget_shotlist);
        shotListadapter=new ShotListAdapter();
        shotListProvider=new ShotListProvider();
        shotListProvider.getDataFromNetWork();
        shotList.setAdapter(shotListadapter);
    }
}
