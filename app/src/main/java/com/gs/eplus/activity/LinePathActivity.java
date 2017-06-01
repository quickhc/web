package com.gs.eplus.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.systembar.SystemBarHelper;
import com.gs.eplus.R;
import com.gs.eplus.http.UrlContacts;
import com.gs.eplus.presenter.LinePathPresenter;
import com.gs.eplus.view.LineView;
import com.gs.eplus.weight.LinePathView;
import com.gslibrary.base.BaseMvpActivity;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*********************************************
 ***                                       ***
 ***                                       ***
 ***       Created by HC on 2017/5/3.       ***
 *********************************************/

@ContentView(R.layout.linepath_layout)
public class LinePathActivity extends BaseMvpActivity<LinePathPresenter> implements LineView {
    @ViewInject(R.id.line_path)
    private LinePathView line_path;

    @ViewInject(R.id.tv_clear)
    private TextView tv_clear;
    @ViewInject(R.id.tv_save)
    private TextView tv_save;

    private LinePathPresenter linePathPresenter;

    @Override
    public void setContentView() {
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
    public void initView() {
        line_path.setBackColor(Color.WHITE);
        line_path.setPaintWidth(20);
        line_path.setPenColor(Color.BLACK);
        line_path.clear();
    }

    @Override
    public void initListen() {
        tv_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                line_path.clear();
            }
        });

        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitMap = line_path.getBitMap();
                /**
                 * 保存文件
                 * @param bm
                 * @param fileName
                 * @throws IOException
                 */
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/web/qianming.png";
                if (line_path.getTouched()) {
                    try {
                        line_path.save(path, true, 10);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(mContext, "您没有签名~", Toast.LENGTH_SHORT).show();
                }

                Map<String, File> pathmap = new HashMap<String, File>();
                pathmap.put("file", new File(path));

                RequestParams params = new RequestParams(UrlContacts.upload);
                params.addBodyParameter("file", new File(path));

                params.setMultipart(true);
                x.http().post(params, new Callback.CommonCallback<String>() {

                    public void onSuccess(String result) {
                        Logger.e(result, "2");
                        try {
                            JSONObject jSONObject = new JSONObject(result);
                            if (jSONObject.getBoolean("ret")) {
                                String filepath = jSONObject.getString("filepath");
                                Intent imageUrl = getIntent().putExtra("imageUrl", filepath);
                                setResult(RESULT_OK,imageUrl);
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    public void onError(Throwable ex, boolean isOnCallback) {
                    }

                    public void onCancelled(CancelledException cex) {
                    }

                    public void onFinished() {

                    }
                });

//                XutilsHttp.getInstance().upLoadFile(UrlContacts.upload, new RMParams().upload(path), pathmap, new XCallBack() {
//                    @Override
//                    public void onFail(String s) {
//                        Logger.i(s,s);
//                    }
//
//                    @Override
//                    public boolean onResponse(String result) {
//                        Logger.i(result);
//                        return super.onResponse(result);
//                    }
//                });

            }
        });
    }

    @Override
    public LinePathPresenter initPresenter() {
        linePathPresenter = new LinePathPresenter();
        return linePathPresenter;
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
