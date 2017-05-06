package com.gs.web.activity;

import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.SDCardUtils;
import com.gs.web.R;
import com.gs.web.presenter.MainPresenter;
import com.gs.web.view.MainView;
import com.gslibrary.base.BaseMvpActivity;
import com.orhanobut.logger.Logger;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.models.BarModel;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainView {

    @ViewInject(R.id.tv)
    private TextView tv;

    @ViewInject(R.id.barchart)
    private BarChart mBarChart;

    private MainPresenter mainPresenter;

    @Override
    public void setContentView() {
        x.view().inject(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListen() {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                toActivity(LinePathActivity.class);
                toActivity(LinePathActivity.class);
            }
        });
    }


    @Override
    public MainPresenter initPresenter() {
        mainPresenter = new MainPresenter();
        return mainPresenter;
    }

    @Override
    public void loadWeb(String message) {
        tv.setText(message);

        String sdCardPath = SDCardUtils.getSDCardPath();

        Logger.i(sdCardPath);//Log打印
//        Logger.d("hello %s %d", "world", 5);
//
//        Logger.t("1").d("hello");

//        BigDecimal bigDecimal=new BigDecimal(8);
//        double v = bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
//        DecimalFormat df = new DecimalFormat("#.00");
//        String format = df.format(v);
//        Logger.t("1").d(format);
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
        mBarChart.addBar(new BarModel(2.3f, 0xFF123456));
        mBarChart.addBar(new BarModel(2.f,  0xFF343456));
        mBarChart.addBar(new BarModel(3.3f, 0xFF563456));
        mBarChart.addBar(new BarModel(1.1f, 0xFF873F56));
        mBarChart.addBar(new BarModel(2.7f, 0xFF56B7F1));
        mBarChart.addBar(new BarModel(2.f,  0xFF343456));
        mBarChart.addBar(new BarModel(0.4f, 0xFF1FF4AC));
        mBarChart.addBar(new BarModel(4.f,  0xFF1BA4E6));

        mBarChart.startAnimation();
    }
}
