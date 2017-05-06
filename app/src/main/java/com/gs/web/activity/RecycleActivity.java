package com.gs.web.activity;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.gs.web.R;
import com.gs.web.adapter.DividerItemDecoration;
import com.gs.web.adapter.MyAdapter;
import com.gs.web.presenter.RecyclePresenter;
import com.gs.web.view.RecycleView;
import com.gslibrary.base.BaseMvpActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/*********************************************
 ***                                       ***
 ***                                       ***
 ***       Created by HC on 2017/5/5.       ***
 *********************************************/

@ContentView(R.layout.recycle_layout)
public class RecycleActivity extends BaseMvpActivity<RecyclePresenter> implements RecycleView {

    @ViewInject(R.id.vertical_refresh)
    private RecyclerView vertical_refresh;

    private ArrayList<String> mlist = new ArrayList<>();
    private MyAdapter myAdapter = new MyAdapter(mlist);

    StaggeredGridLayoutManager layoutManager;

    @Override
    protected void setContentView() {
        x.view().inject(this);
    }

    @Override
    protected void initView() {
        layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        vertical_refresh.setLayoutManager(layoutManager);//设置列表布局方式
        vertical_refresh.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST));
        vertical_refresh.setAdapter(myAdapter);
    }

    @Override
    protected void initListen() {

    }

    @Override
    protected RecyclePresenter initPresenter() {
        return new RecyclePresenter();
    }

    @Override
    public void refreshList() {
        for (int i = 0; i < 10; i++) {
            mlist.add("item" + i);
        }
    }

    @Override
    public void loadMore() {

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
