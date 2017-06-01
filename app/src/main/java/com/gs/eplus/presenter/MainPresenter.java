package com.gs.eplus.presenter;

import com.gs.eplus.view.MainView;
import com.gslibrary.base.BasePresenter;

/*********************************************
 ***                                       ***
 ***                                       ***
 ***       Created by HC on 2017/5/3.       ***
 *********************************************/

public class MainPresenter extends BasePresenter<MainView> {

    @Override
    public void onStart() {
        mView.loadWeb("123");
        mView.loadData();
    }
}
