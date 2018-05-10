package com.zhx.app.webviewdemo;

import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * Created by Administrator on 2018/5/6.
 */

public class JSInterface {
    private String TAG = "JSJSInterface";
    private JSBride jsBride;
    public JSInterface(JSBride jsBride){
        this.jsBride=jsBride;
    }

    /**
     *      @JavascriptInterface 必须加 为js代码识别的标志
     * @param valus
     *
     * 这个方法不在主线程中执行
     */
    @JavascriptInterface
    public void setValus(String valus) {
        Log.d(TAG, valus);
        jsBride.setValue(valus);
    }
}
