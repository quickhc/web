package com.gs.eplus.view;

import com.gslibrary.base.BaseView;

/**
 * Created by Administrator on 2017/5/7.
 */

public interface LoginView extends BaseView {

    void checkUserName();

    void show(String message);

    void toMain(String username);

}
