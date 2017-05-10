package com.gs.web.presenter;

import com.gs.web.http.RMParams;
import com.gs.web.http.UrlContacts;
import com.gs.web.view.LoginView;
import com.gslibrary.base.BasePresenter;
import com.gslibrary.http.XCallBack;
import com.gslibrary.http.XutilsHttp;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/5/7.
 */

public class LoginPresenter extends BasePresenter<LoginView> {
    @Override
    public void onStart() {

    }

    public void login(final String usercode, String password) {
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
                    if(ret){
                        mView.toMain(usercode);
                    }else{
                        mView.show("登录失败");
                    }
                } catch (JSONException e) {
                    Logger.e(e.toString());
                }
                return super.onResponse(result);
            }
        });
    }

    ;
}
