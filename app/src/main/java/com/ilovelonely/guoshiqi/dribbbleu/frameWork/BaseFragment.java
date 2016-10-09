package com.ilovelonely.guoshiqi.dribbbleu.frameWork;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by guoshiqi on 16/5/31.
 */
public abstract class BaseFragment extends Fragment {

    public View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setView();
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    public abstract void setView();
}
