package com.gs.web.presenter;

import com.gs.web.view.RecycleView;
import com.gslibrary.base.BasePresenter;

/*********************************************
 ***                                       ***
 ***                                       ***
 ***       Created by HC on 2017/5/5.       ***
 *********************************************/

public class RecyclePresenter extends BasePresenter<RecycleView> {
    @Override
    public void onStart() {
        mView.refreshList();
    }
}
