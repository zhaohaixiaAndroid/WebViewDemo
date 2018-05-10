package com.zhx.app.webviewdemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhx.app.webviewdemo.R;

/**
 * Created by Administrator on 2018/5/7.
 */

public class RightFragment extends Fragment {

    private TextView tvMRight;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View viewRight=inflater.inflate(R.layout.fragment_right,null) ;

        tvMRight = viewRight.findViewById(R.id.tv_right);
        return viewRight;
    }

    public void setText(String str){
        tvMRight.setText(str);
    }
}
