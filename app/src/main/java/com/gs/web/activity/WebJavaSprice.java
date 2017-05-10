package com.gs.web.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

/**
 * Created by Administrator on 2017/5/7.
 */

public class WebJavaSprice {

    private Context mContext;

    public WebJavaSprice(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 去二维码扫描界面
     */
    @JavascriptInterface
    public void startQX() {
        Intent intent = new Intent(mContext, RXCodeActivity.class);
        ((Activity) mContext).startActivityForResult(intent, 101);
    }

    @JavascriptInterface
    public void startSign() {
        Intent intent = new Intent(mContext, LinePathActivity.class);
        ((Activity) mContext).startActivityForResult(intent, 102);
    }

}
