package com.gs.eplus.app;

import com.gslibrary.app.SAppLication;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/*********************************************
 ***                                       ***
 ***                                       ***
 ***       Created by HC on 2017/5/3.       ***
 *********************************************/

public class EplusAppLication extends SAppLication {

    @Override
    public void onCreate() {
        super.onCreate();
        setXutilsDebug(true);

//        Bugsee.launch(this, "d58d8ee7-23b7-48a0-b7b3-adb293670acb");

        ZXingLibrary.initDisplayOpinion(this);
    }
}
