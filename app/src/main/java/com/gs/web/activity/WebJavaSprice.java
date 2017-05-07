package com.gs.web.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2017/5/7.
 */

public class WebJavaSprice {

    private Context mContext;
    private static int REQUEST_CODE = 101;

    public WebJavaSprice(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 去二维码扫描界面
     */
    public void startQX() {
        Intent intent = new Intent(mContext, RXCodeActivity.class);
        ((Activity) mContext).startActivityForResult(intent, REQUEST_CODE);
    }

}
