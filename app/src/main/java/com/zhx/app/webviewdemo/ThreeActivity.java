package com.zhx.app.webviewdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * handler 中子线程向主线程发送消息
 */

public class ThreeActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mSend;
    private TextView mSubSend;

    //主线程
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
           Message message=new Message();
            Toast.makeText(ThreeActivity.this, "main thread", Toast.LENGTH_LONG).show();
            handlerThread.sendMessageDelayed(message,1000);
        }
    };


    private Handler handlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);


        initView();
    }

    private void initView() {
        mSend = (TextView)findViewById(R.id.tv_send);
        mSubSend = (TextView)findViewById(R.id.tv_subsend);
        mSend.setOnClickListener(this);
        mSubSend.setOnClickListener(this);



        HandlerThread thread=new HandlerThread("handler thread");
        thread.start();

        //创建子线程
        handlerThread=new Handler(thread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                Message message=new Message();
                Toast.makeText(ThreeActivity.this, "sub thread", Toast.LENGTH_LONG).show();
                //向主线程发送消息
                mHandler.sendMessageDelayed(message,1000);
            }
        };



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_send:
                mHandler.sendEmptyMessage(1);
                break;
            case R.id.tv_subsend:
                mHandler.removeMessages(1);
                break;
        }
    }
}
