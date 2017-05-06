package com.gs.web.activity;

import com.gs.web.R;
import com.gs.web.presenter.LinePathPresenter;
import com.gs.web.view.LineView;
import com.gs.web.weight.LinePathView;
import com.gslibrary.base.BaseMvpActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/*********************************************
 ***                                       ***
 ***                                       ***
 ***       Created by HC on 2017/5/3.       ***
 *********************************************/

@ContentView(R.layout.linepath_layout)
public class LinePathActivity extends BaseMvpActivity<LinePathPresenter> implements LineView {
    @ViewInject(R.id.line_path)
    private LinePathView line_path;
    private LinePathPresenter linePathPresenter;

    @Override
    public void setContentView() {
        x.view().inject(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListen() {

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
