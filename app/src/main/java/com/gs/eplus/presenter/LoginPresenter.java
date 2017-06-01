package com.gs.eplus.presenter;

import android.content.Context;

import com.gs.eplus.http.RMParams;
import com.gs.eplus.http.UrlContacts;
import com.gs.eplus.view.LoginView;
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
        boolean login = isLogin(x.app().getBaseContext());
        if (login) {
            login((String) SPUtils.get(x.app().getBaseContext(), "usercode", ""), (String) SPUtils.get(x.app().getBaseContext(), "password", ""));
        }
    }

    public void login(final String usercode, final String password) {
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

                        mView.toMain(usercode);
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

}
