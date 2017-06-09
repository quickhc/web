package com.gs.eplus.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.flyco.systembar.SystemBarHelper;
import com.gs.eplus.R;
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
    @ViewInject(R.id.webview)
    private WebView webview;

    private LoadingProgress loadingProgress;
    @Override
    protected void setContentView() {
        x.view().inject(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (SystemBarHelper.isMIUI6Later() || SystemBarHelper.isFlyme4Later() || Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                SystemBarHelper.setStatusBarDarkMode(this);
                SystemBarHelper.tintStatusBar(this, getResources().getColor(R.color.states_color), 0);
            } else {
                SystemBarHelper.tintStatusBar(this, getResources().getColor(R.color.states_color), 0);
            }
        }
    }

    @Override
    protected void initView() {

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        webview.loadUrl("http://122.114.146.13/phone/appindex.action?user.usercode=" + getIntent().getStringExtra("user") + "&token=" + getIntent().getStringExtra("token"));

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
                showLoading();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dismissLoading();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //激活WebView为活跃状态，能正常执行网页的响应
        webview.onResume();
        webview.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webview.getSettings().setJavaScriptEnabled(false);
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
        loadingProgress=new LoadingProgress(this);
        loadingProgress.showDialog("Loading...");
    }

    @Override
    public void dismissLoading() {
        if(loadingProgress.isShowing()){
            loadingProgress.dismissDialog();
        }
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

    private long exitTime = 0;

    /**
     * 双击退出函数
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) < 500) {
//                Toast toast = Toast.makeText(mContext, "再按一次退出程序", Toast.LENGTH_SHORT);
//                toast.cancel();
                finishAll();
            } else {
                webview.goBack(); //goBack()表示返回WebView的上一页面
                exitTime = System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
