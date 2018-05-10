package com.zhx.app.webviewdemo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * android 和webview之间的交互常见的问题
 * 1.在js接口回调的方法中throw exception 的时候
 *    app端不会有问题 但是网页端有有问题
 *  2.web端不进行对象为空的判断
 *  3.传递参数的类型不一致（尤其是数组和对象）
 *  4.字符串类型为空时 传递undefined
 */

public class MainActivity extends AppCompatActivity implements JSBride {

    private WebView mWebView;
    private TextView mtvResult;
    //把子线的内容传递到主线程，使用handler的post的方法放在主线程中
    private Handler mHandler;
    private EditText mEditText;
    private Button mButton;
    private Button btnFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.webview);
        mtvResult = (TextView) findViewById(R.id.tv_result);

        //
        mEditText = (EditText)findViewById(R.id.edit);
        mButton = (Button)findViewById(R.id.btn_sent);
        initData();

        btnFragment = (Button)findViewById(R.id.btn_fragment);
        initListener();
        mHandler = new Handler();

        //允许webview加载JavaScript
        mWebView.getSettings().setJavaScriptEnabled(true);
        //第二步：编写js接口类  JSInterface

        //第三步  给webview添加js接口 第一个参数是js的接口类  第二个参数是name 这个name要和html文件中的事件一致
        /**
         *  btn.addEventListener("click",function () {
         var str=text.value;
         //alert(str)
         if (window.JSInterface){
         JSInterface.setValus(str);
         }else {
         alert("no fount ")
         }
         });
         */
        mWebView.addJavascriptInterface(new JSInterface(this), "JSInterface");
        //加载本地的
        mWebView.loadUrl("file:///android_asset/index.html");


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //打开允许调试的开关
            mWebView.setWebContentsDebuggingEnabled(true);
            //第二步在浏览器中打开以下地址的网址：chrome://inspect/#devices
        }

    }

    private void initListener() {
        btnFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });
    }

    private void initData() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sentStr=mEditText.getText().toString().trim();
                mWebView.loadUrl("javascript:if(window.remote){window.remote('"+sentStr+"')}");
            }
        });
    }

    @Override
    public void setValue(final String value) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mtvResult.setText(value);
            }
        });
    }
}
