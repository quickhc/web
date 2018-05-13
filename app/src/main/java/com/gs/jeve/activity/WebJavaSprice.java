package com.gs.jeve.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

import com.gslibrary.utils.SPUtils;

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

    @JavascriptInterface
    public void popBack() {
        //清除缓存
        SPUtils.clear(mContext,"present_user");
        //跳转页面
        Intent intent = new Intent(mContext, LoginActivity2.class);
        ((Activity) mContext).startActivity(intent);
        ((Activity) mContext).finish();
    }

}
