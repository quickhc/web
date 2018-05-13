package com.gs.jeve.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gs.eplus.R;
import com.gs.jeve.presenter.MainPresenter;
import com.gs.jeve.view.MainView;
import com.gslibrary.base.BaseMvpActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainView {

    @ViewInject(R.id.tv_erweima)
    private TextView tv_erweima;

    @ViewInject(R.id.tv_qianming)
    private TextView tv_qianming;

    private MainPresenter mainPresenter;
    public static int REQUEST_CODE = 101;

    @Override
    public void setContentView() {
        x.view().inject(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListen() {
        tv_qianming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                toActivity(LinePathActivity.class);
                toActivity(LinePathActivity.class);
            }
        });

        tv_erweima.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RXCodeActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public MainPresenter initPresenter() {
        mainPresenter = new MainPresenter();
        return mainPresenter;
    }

    @Override
    public void loadWeb(String message) {
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
}
