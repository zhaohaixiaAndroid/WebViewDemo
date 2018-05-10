package com.zhx.app.webviewdemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zhx.app.webviewdemo.R;

/**
 * Created by Administrator on 2018/5/7.
 */

public class LeftFragment extends Fragment implements View.OnClickListener{


    private EditText editMleft;
    private Button btnSend;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_left,null);

        editMleft = view.findViewById(R.id.edit_left);
        btnSend = view.findViewById(R.id.btn_send_left);
          btnSend.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        //实现把左侧的fragment的数据传递到右侧的fragment
        switch (v.getId()){
            case R.id.btn_send_left:
                String sendStr=editMleft.getText().toString();
                //第一种：调用getFragmentManager().findFragmentById() 获取Fragment对象调用方法
//                RightFragment rightFragment= (RightFragment) getFragmentManager().findFragmentById(R.id.right_fragment);
//                rightFragment.setText(sendStr);

                //第二种：getFragmentManager().findFragmentById().getView().findViewById()根据id获取控件的id
//                TextView tvRight=getFragmentManager().findFragmentById(R.id.right_fragment).getView().findViewById(R.id.tv_right);
//                tvRight.setText(sendStr);

                //第三种：.getActivity().findViewById()获取当前所属Activity 根据id获取view对象
                TextView tvRight=getActivity().findViewById(R.id.tv_right);
                tvRight.setText(sendStr);

                break;
        }

    }



}
