package com.gs.jeve.presenter;

import android.content.Context;
import android.os.Handler;

import com.gs.jeve.http.RMParams;
import com.gs.jeve.http.UrlContacts;
import com.gs.jeve.view.LoginView;
import com.gslibrary.base.BasePresenter;
import com.gslibrary.http.XCallBack;
import com.gslibrary.http.XutilsHttp;
import com.gslibrary.utils.SPUtils;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;

/**
 * Created by Administrator on 2017/5/7.
 */

public class LoginPresenter extends BasePresenter<LoginView> {
    @Override
    public void onStart() {
        loginAgain();
    }

    public void login(final String usercode, final String password) {
        mView.showLoading();
        XutilsHttp.getInstance().post(UrlContacts.login, new RMParams().login(usercode, password), new XCallBack() {
            @Override
            public void onFail(String s) {
                mView.show(s);
            }

            @Override
            public boolean onResponse(String result) {
                Logger.i(result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    boolean ret = jsonObject.getBoolean("ret");
                    if (ret) {
                        SPUtils.put(x.app().getBaseContext(), "isLogin", true);
                        SPUtils.put(x.app().getBaseContext(), "usercode", usercode);
                        SPUtils.put(x.app().getBaseContext(), "password", password);
                        String token = jsonObject.getString("token");
                        mView.toMain(usercode, token);
                    } else {
                        mView.show("登录失败");
                    }
                } catch (JSONException e) {
                    Logger.e(e.toString());
                }
                return super.onResponse(result);
            }
        });
    }

    public boolean isLogin(Context mContext) {
        return (Boolean) SPUtils.get(mContext, "isLogin", false);
    }

    public void loginAgain() {
        boolean login = isLogin(x.app().getBaseContext());
        if (login) {
            //延时显示登录界面
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    login((String) SPUtils.get(x.app().getBaseContext(), "usercode", ""), (String) SPUtils.get(x.app().getBaseContext(), "password", ""));
                }
            }, 500);
        }
    }

}
