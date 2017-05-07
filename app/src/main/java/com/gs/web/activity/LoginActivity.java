package com.gs.web.activity;

import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.gs.web.R;
import com.gs.web.presenter.LoginPresenter;
import com.gs.web.view.LoginView;
import com.gslibrary.base.BaseMvpActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Administrator on 2017/5/7.
 */

@ContentView(R.layout.login_activity)
public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginView {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;

    @ViewInject(R.id.et_username)
    private EditText et_username;
    @ViewInject(R.id.et_password)
    private EditText et_password;

    @ViewInject(R.id.btn_login)
    private TextView btn_login;
    @ViewInject(R.id.tv_fot)
    private TextView tv_fot;

    @Override
    protected void setContentView() {
        x.view().inject(this);
    }

    @Override
    protected void initView() {
        et_username.setHintTextColor(getResources().getColor(R.color.text_color));
        et_password.setHintTextColor(getResources().getColor(R.color.text_color));
    }

    @Override
    protected void initListen() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(MainActivity.class);
                finish();
            }
        });

        tv_fot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(WebActivity.class);
            }
        });

    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
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
    public void LoginButton() {

    }

    @Override
    public void checkUserName() {

    }
}
