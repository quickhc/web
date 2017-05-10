package com.gs.web.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.gs.web.R;
import com.gslibrary.base.BaseMvpActivity;
import com.gslibrary.base.BasePresenter;
import com.gslibrary.base.BaseView;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Administrator on 2017/5/7.
 */
@ContentView(R.layout.webb_activity)
public class WebActivity extends BaseMvpActivity<BasePresenter> implements BaseView {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.webview)
    private WebView webview;

    @Override
    protected void setContentView() {
        x.view().inject(this);
    }

    @Override
    protected void initView() {
        tv_title.setText("Eplus");
        tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        webview.loadUrl("http://122.114.146.13/phone/appindex.action?user.usercode=" + getIntent().getStringExtra("user"));

//        webview.loadUrl("file:///android_asset/Main.html");

        webview.addJavascriptInterface(new WebJavaSprice(mContext), "android");

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //设定加载开始的操作
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //激活WebView为活跃状态，能正常执行网页的响应
        webview.onResume();
    }

    @Override
    protected void initListen() {

    }

    @Override
    protected BasePresenter initPresenter() {
        return new BasePresenter() {
            @Override
            public void onStart() {
            }
        };
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showMessage() {

    }

    @Override
    public void loadData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 101) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    webview.loadUrl("javascript:tobackbarcode('" + result + "')");
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(mContext, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
        if (resultCode == RESULT_OK && requestCode == 102) {
            if (data == null) {
                return;
            }
            String imageUrl = data.getStringExtra("imageUrl");
            webview.loadUrl("javascript:tobacksignpath('" + imageUrl + "')");
        }
    }
}
